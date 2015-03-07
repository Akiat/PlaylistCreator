package com.akiat.deezer;

import org.json.JSONObject;

import com.akiat.common.Artist;

public class DeezerArtist extends Artist {
	
	/*
	 * JSON pour deezer
	{
		"id":2508,
		"name":"Digitalism",
		"link":"http:\/\/www.deezer.com\/artist\/2508",
		"picture":"http:\/\/api.deezer.com\/artist\/2508\/image",
		"tracklist":"http:\/\/api.deezer.com\/artist\/2508\/top?limit=50",
		"type":"artist"
	}
	*/

	public DeezerArtist(String artistJson) {
		super(artistJson);
		
		JSONObject artist = new JSONObject(artistJson);

		if (artist != null) {
			m_id = String.valueOf(artist.getInt("id"));
			m_name = artist.getString("name");
			m_link = artist.getString("link");
			m_pictureLink = artist.getString("picture");
			m_trackListLink = artist.getString("tracklist");
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
