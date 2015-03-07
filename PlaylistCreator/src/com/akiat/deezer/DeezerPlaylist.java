package com.akiat.deezer;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.akiat.common.Playlist;
import com.akiat.common.Track;

public class DeezerPlaylist extends Playlist {

	public DeezerPlaylist(String playlistJson) {
		super();

		JSONObject obj = new JSONObject(playlistJson);

		if (obj != null)
		{
			m_id 		= String.valueOf(obj.getInt("id"));
			m_title		= obj.getString("title");
			m_link		= obj.getString("link");
			m_picture	= obj.getString("picture");
			m_tracklistLink = obj.getString("tracklist");
			m_duration	= obj.getInt("duration");
			m_nbTracks	= obj.getInt("nb_tracks");

			m_isPublic	= obj.getBoolean("public");
			m_isCollaborative = obj.getBoolean("collaborative");

			m_ownerId 	= String.valueOf(obj.getJSONObject("creator").getInt("id"));
			m_ownerName	= obj.getJSONObject("creator").getString("name");
		}
	}

	protected LinkedList<Track> fillTrackListFromJson(String trackListJson) {
		
		JSONArray tracklistArray = new JSONArray(trackListJson);

		if (tracklistArray != null)
		{
			if (m_tracklist == null)
				m_tracklist = new LinkedList<>();
			
			for (int i = 0; i < tracklistArray.length(); i++) {
				
				Track track = new DeezerTrack(tracklistArray.get(i).toString());
				m_tracklist.add(track);
			}
		}
		
		return m_tracklist;
		
	}

}
