package com.akiat.deezer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.akiat.common.MusicPlatform;
import com.akiat.common.MusicPlatformUser;
import com.akiat.common.Playlist;

public class Deezer extends MusicPlatform {
	
	public Deezer(String configFilePath) {
		super(configFilePath);
		
		m_configFilePath 	+= "/Deezer";
		
		CLIENT_ID = 		"153011";
		CLIENT_SECRET = 	"3a9d42f9a15a9b01aae500c4ea146405";
		REDIRECT_URL =		"http://localhost";
		AUTH_URL =			"https://connect.deezer.com/oauth/auth.php";
		ACCESS_TOKEN_URL =	"https://connect.deezer.com/oauth/access_token.php";
		USER_INFO_URL =		"http://api.deezer.com/user/me";
		
		PLAYLISTS_URL = 	"http://api.deezer.com/user/me/playlists";
		
		load();
	}

	@Override
	protected MusicPlatformUser loadUserInfos(String userInfosJson) {

		return new DeezerUser(userInfosJson);
	}

	@Override
	public void loadPlaylistsInfos() {
		String playlistsString = doRequest(PLAYLISTS_URL);
		
		JSONObject obj = new JSONObject(playlistsString);

		if (obj != null)
		{
			JSONArray playlists = obj.getJSONArray("data");
			
			for (int i = 0; i < playlists.length(); i++) {
				Playlist playlist = new DeezerPlaylist(playlists.get(i).toString());
				System.out.println(playlist.toString());
				m_playlists.add(playlist);
			}
		}
	}


}
