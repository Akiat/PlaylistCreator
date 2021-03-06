package com.akiat.deezer;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.akiat.common.MusicPlatform;
import com.akiat.common.MusicPlatformUser;
import com.akiat.common.Playlist;
import com.akiat.common.Track;

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
				
				m_playlists.put(playlist.getId(), playlist);
				// Load tracks for the current playlist
				playlist.setTracklist(loadPlaylistTracks((DeezerPlaylist)playlist));
			}
		}
	}

	private LinkedList<Track> loadPlaylistTracks(String playlistID) {
		DeezerPlaylist playlist = (DeezerPlaylist) m_playlists.get(playlistID);
		return loadPlaylistTracks(playlist);
	}
	
	private LinkedList<Track> loadPlaylistTracks(DeezerPlaylist playlist) {

		LinkedList<Track> tracklist = null;

		String tracklistLink = playlist.getTracklistLink();

		// Deezer gives tracks 25 by 25. There is a "next" parameter in the json if there is more tracks to retreive.
		while (tracklistLink != null) {

			String trackListJson = doRequest(tracklistLink);
			JSONObject trackList = new JSONObject(trackListJson);
			//System.out.println(trackList.toString(4));
			if (trackList != null) {

				// If there is more tracks to retreive, fill tracklistLink with the next link
				tracklistLink = null;
				if (trackList.has("next"))
					tracklistLink = trackList.getString("next");

				JSONArray tracklistArray = trackList.getJSONArray("data");
				tracklist = playlist.fillTrackListFromJson(tracklistArray.toString());
			}
		}
		return tracklist;
	}
}
