package com.akiat.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONArray;
import org.json.JSONObject;

import com.akiat.exceptions.AuthException;
import com.akiat.exceptions.RequestException;

public abstract class MusicPlatform {
	protected static Logger LOGGER = Logger.getLogger(MusicPlatform.class.getName());

	protected static String CLIENT_ID = null;
	protected static String CLIENT_SECRET = null;
	protected static String REDIRECT_URL = null;
	protected static String AUTH_URL = null;
	protected static String ACCESS_TOKEN_URL = null;
	protected static String USER_INFO_URL = null;
	protected static String PLAYLISTS_URL = null;

	protected OAuthClient m_client = null;

	protected HashMap<String, Playlist> m_playlists = new HashMap<>();

	protected String m_accessToken = null;
	protected String m_configFilePath = null;
	protected MusicPlatformUser m_userInfos = null;

	public MusicPlatform(String configFilePath) {
		m_configFilePath = configFilePath;
		m_client = new OAuthClient(new URLConnectionClient());
	}

	// Abstract methods --------------- 
	public abstract HashMap<String, Playlist> 	loadPlaylistsInfos();
	public abstract LinkedList<Track> 			loadPlaylistsTracks();
	public abstract LinkedList<Track> 			loadPlaylistTracks(String playlistID);

	protected abstract MusicPlatformUser loadUserInfos(String userInfosJson);
	// --------------------------------
	
	/**
	 * Load the platform, (get access token from config file or webservice and fill the user infos)
	 */
	protected void load() {
		load(true);
	}

	/**
	 * Load the platform, (get access token from config file or webservice and fill the user infos)
	 */
	protected void load(boolean loadTokenFromConfig)
	{
		if (loadTokenFromConfig)
			m_accessToken = getProperty("AccessToken");

		// If no access token was stored, load the oAuth2 process.
		if (m_accessToken == null || !loadTokenFromConfig) {

			String authUrl = getAuthUrl();
			System.out.println("Please visit " + authUrl);

			// Get token from user
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter the authorization code: ");
			String authCode = reader.nextLine();
			reader.close();

			m_accessToken = getAccessToken(authCode);
			if (m_accessToken != null) {
				// Store the Access token
				setProperty("AccessToken", m_accessToken);
			}
		}

		// In all case refresh user infos
		m_userInfos = loadUserInfos(doRequest(USER_INFO_URL, true));
	}

	/**
	 * Call a url with the access token appended. No request retry if there is an error.
	 * @param url The URL that you want to call.
	 * @return The body of the response.
	 */
	protected String doRequest(String url) {
		return doRequest(url, false);
	}

	/**
	 * Call a url with the access token appended.
	 * @param url The URL that you want to call.
	 * @param allowRetry give true if you want to retry the request if it fails.
	 * @return The body of the response.
	 */
	protected String doRequest(String url, boolean allowRetry) {
		// NOTE: Should be run on background thread
		if (m_client != null)
		{
			OAuthResourceResponse response;
			
			try {
				OAuthClientRequest bearerRequest = new OAuthBearerClientRequest(url)
				.setAccessToken(m_accessToken)
				.buildQueryMessage();

				response = m_client.resource(bearerRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);

			} catch (OAuthSystemException | OAuthProblemException e) {
				LOGGER.log(Level.SEVERE, "Authentication problem. ", e);
				throw new AuthException("Authentication problem.", e);
			}

			if (response.getResponseCode() != 200) {
				throw new RequestException("Failed : HTTP error code : " + response.getResponseCode() + response.getBody());
			}

			String body = response.getBody();
			JSONObject obj = new JSONObject(body);

			// If there is an error
			if (obj != null && obj.has("error")) {

				JSONObject error = obj.getJSONObject("error");

				// Reload and retry the request only one time
				if (allowRetry) {
					load(false);
					return doRequest(url, false);
				} else {
					String message = error.getString("message");
					String type = error.getString("type");
					int code = error.getInt("code");

					throw new RequestException("Error : " + type + " (" + code + ") : " + message);
				}

			}
			return body;
		}
		return null;
	}

	/**
	 * @return the authorization URL (which is used to authorize the application to have access to your account)
	 */
	private String getAuthUrl() {

		OAuthClientRequest request = null;
		try {
			request = OAuthClientRequest
					.authorizationLocation(AUTH_URL)
					.setClientId(CLIENT_ID)
					.setRedirectURI(REDIRECT_URL)
					.buildQueryMessage();
		}
		catch (OAuthSystemException e) {
			LOGGER.log(Level.SEVERE, "Exception loading oauth ", e);
			throw new AuthException("Authentication problem.", e);
		}

		return request.getLocationUri();
	}


	/**
	 * @param authorizationCode The authorization code obtained when you authorize the application on the music platform website
	 * @return Return the access token
	 */
	private String getAccessToken(final String authorizationCode) {
		
		String accessToken = null;
		// Note: should be run on background thread
		try {
			OAuthClientRequest request = OAuthClientRequest
					.tokenLocation(ACCESS_TOKEN_URL)
					.setClientId(CLIENT_ID)
					.setClientSecret(CLIENT_SECRET)
					.setRedirectURI(REDIRECT_URL)
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setCode(authorizationCode)
					.buildBodyMessage();

			GitHubTokenResponse oAuthResponse = m_client.accessToken(request, GitHubTokenResponse.class);
			accessToken = oAuthResponse.getAccessToken();
		}
		catch (OAuthSystemException | OAuthProblemException e) {
			LOGGER.log(Level.SEVERE, "Exception during get access token ", e);
			throw new AuthException("Exception during get access token.", e);
		}

		return accessToken;
	}


	/**
	 * Set a property for the current user in his config file.
	 * 
	 * @param propertyName The name of the property to set.
	 * @param propertyValue The value of the property to set.
	 */
	protected void setProperty(String propertyName, String propertyValue) {

		Properties properties = new Properties();
		FileOutputStream configFile = null;
		try {
			configFile = new FileOutputStream(m_configFilePath);

			properties.setProperty(propertyName, propertyValue);
			properties.store(configFile, null);

			configFile.close();

			LOGGER.log(Level.INFO, "Add '" + propertyName + "=" + propertyValue +
					"' in property file '" + m_configFilePath + "'");
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Unable to write config file.");
			throw new RuntimeException("Unable to write config file.", e);
		}
	}

	/**
	 * Get a property from the config file for the the current user.
	 * @param propertyName The name of the property to set.
	 * @return the asked property or null if not find.
	 */
	protected String getProperty(String propertyName) {

		Properties properties = new Properties();

		FileInputStream configFile = null;
		try {
			configFile = new FileInputStream(m_configFilePath);
			properties.load(configFile);
			configFile.close();
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Unable to load config file.");
			throw new RuntimeException("Unable to load config file.", e);
		}

		LOGGER.log(Level.INFO, "Read '" + propertyName + "' in property file '" + m_configFilePath + "'");
		return properties.getProperty(propertyName, null);
	}

	public HashMap<String, Playlist> getPlaylists() {
		return m_playlists;
	}

	//	private String addAccessTokenToURL(String url) {
	//
	//		StringBuilder requestUrl = new StringBuilder(url);
	//		LinkedList<NameValuePair> params = new LinkedList<NameValuePair>();
	//		params.add(new BasicNameValuePair("access_token", m_accessToken));
	//
	//		String querystring = URLEncodedUtils.format(params, "utf-8");
	//		requestUrl.append("?");
	//		requestUrl.append(querystring);
	//
	//		return requestUrl.toString();
	//	}

	//	public MusicPlatformUser getUserInfo() {
	//
	//		String res = null;
	//		try {
	//			// create HTTP Client
	//			HttpClient httpClient = HttpClientBuilder.create().build();
	//
	//			// Create new getRequest with below mentioned URL and add the access token 
	//			HttpGet getRequest = new HttpGet(addAccessTokenToURL(USER_INFO_URL));
	//
	//			// Execute your request and catch response
	//			HttpResponse response = httpClient.execute(getRequest);
	//
	//			// Check for HTTP response code: 200 = success
	//			if (response.getStatusLine().getStatusCode() != 200) {
	//				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
	//			}
	//
	//			// Get-Capture Complete application/xml body response
	//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
	//			StringBuilder strBuilder = new StringBuilder();
	//			String output;
	//
	//			// Simply iterate through XML response and show on console.
	//			while ((output = br.readLine()) != null) {
	//				strBuilder.append(output);
	//			}
	//
	//			res = strBuilder.toString();
	//			System.out.println(res);
	//
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//
	//		return null;
	//	}
}