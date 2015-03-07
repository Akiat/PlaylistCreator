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
			m_id = String.valueOf(track.getInt("id"));
			m_readable = track.getBoolean("readable");
			m_title = track.getString("title");
			m_link = track.getString("link");
			m_duration = track.getInt("duration");			
			m_rank = track.getInt("rank");			
			m_explicitLyrics = track.getBoolean("explicit_lyrics");
			m_previewLink = track.getString("preview");
			m_timeAdd = track.getInt("time_add");			
			m_artist = new DeezerArtist(track.getJSONObject("artist").toString());
			m_album = new DeezerAlbum(track.getJSONObject("album").toString(), m_artist);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
