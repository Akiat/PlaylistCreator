package com.akiat.deezer;

import org.json.JSONObject;

import com.akiat.common.Album;
import com.akiat.common.Artist;

public class DeezerAlbum extends Album {
	
	/*
	 * JSON pour deezer
	{
		"id":6760760,
		"title":"Hands On Idealism",
		"cover":"http:\/\/api.deezer.com\/album\/6760760\/image",
		"tracklist":"http:\/\/api.deezer.com\/album\/6760760\/tracks",
		"type":"album"
	}
	*/
	
	public DeezerAlbum(String albumJson, Artist artist) {
		super(albumJson, artist);
		
		JSONObject album = new JSONObject(albumJson);

		if (album != null) {
			m_id = String.valueOf(album.getInt("id"));
			m_title = album.getString("title");
			m_cover = album.getString("cover");
			m_trackListLink = album.getString("tracklist");
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
