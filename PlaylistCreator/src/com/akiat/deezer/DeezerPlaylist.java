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
			if (obj.has("id"))
				m_id 		= String.valueOf(obj.getInt("id"));
			if (obj.has("title"))
				m_title		= obj.getString("title");
			if (obj.has("link"))
				m_link		= obj.getString("link");
			if (obj.has("picture"))
				m_picture	= obj.getString("picture");
			if (obj.has("tracklist"))
				m_tracklistLink = obj.getString("tracklist");
			if (obj.has("duration"))
				m_duration	= obj.getInt("duration");
			if (obj.has("nb_tracks"))
				m_nbTracks	= obj.getInt("nb_tracks");

			if (obj.has("public"))
				m_isPublic	= obj.getBoolean("public");
			if (obj.has("collaborative"))
				m_isCollaborative = obj.getBoolean("collaborative");

			if (obj.has("creator") && obj.getJSONObject("creator").has("id"))
				m_ownerId 	= String.valueOf(obj.getJSONObject("creator").getInt("id"));
			if (obj.has("creator") && obj.getJSONObject("creator").has("name"))
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
