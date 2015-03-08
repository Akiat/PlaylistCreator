package com.akiat.deezer;

import org.json.JSONObject;

import com.akiat.common.Album;
import com.akiat.common.Artist;
import com.akiat.common.Track;

public class DeezerTrack extends Track {

	/*
	 * JSON pour deezer
	{
		"id":69000195,
		"readable":true,
		"title":"Apollo-Gize (Breakbot 'Hypnotoad' Extended Rmx)",
		"link":"http:\/\/www.deezer.com\/track\/69000195",
		"duration":277,
		"rank":282829,
		"explicit_lyrics":false,
		"preview":"http:\/\/cdn-preview-1.deezer.com\/stream\/17266b73c15ca69433f57008fc9ad62e-2.mp3",
		"time_add":0,
		"artist":{"id":2508,"name":"Digitalism","link":"http:\/\/www.deezer.com\/artist\/2508","picture":"http:\/\/api.deezer.com\/artist\/2508\/image","tracklist":"http:\/\/api.deezer.com\/artist\/2508\/top?limit=50","type":"artist"},
		"album":{"id":6760760,"title":"Hands On Idealism","cover":"http:\/\/api.deezer.com\/album\/6760760\/image","tracklist":"http:\/\/api.deezer.com\/album\/6760760\/tracks","type":"album"},
		"type":"track"
	}
	 */

	public DeezerTrack(String trackJson) {
		super(trackJson);

		JSONObject track = new JSONObject(trackJson);

		if (track != null)
		{
			if (track.has("id"))
				m_id = String.valueOf(track.getInt("id"));
			if (track.has("readable"))
				m_readable = track.getBoolean("readable");
			if (track.has("title"))
				m_title = track.getString("title");
			if (track.has("link"))
				m_link = track.getString("link");
			if (track.has("duration"))
				m_duration = track.getInt("duration");
			if (track.has("rank"))
				m_rank = track.getInt("rank");
			if (track.has("explicit_lyrics"))
				m_explicitLyrics = track.getBoolean("explicit_lyrics");
			if (track.has("preview"))
				m_previewLink = track.getString("preview");
			if (track.has("time_add"))
				m_timeAdd = track.getInt("time_add");
			if (track.has("artist"))
				m_artist = new DeezerArtist(track.getJSONObject("artist").toString());
			if (track.has("album"))
				m_album = new DeezerAlbum(track.getJSONObject("album").toString(), m_artist);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
