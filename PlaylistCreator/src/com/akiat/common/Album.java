package com.akiat.common;

import org.json.JSONObject;

public class Album {

	protected Artist m_artist;
	protected String m_title;
	protected String m_id;
	protected String m_cover;
	protected String m_trackListLink;
	
	public Album(String albumJson, Artist artist) {
		m_artist = artist;
	}

	@Override
	public String toString() {
		String str = "ALBUM: " + m_title;
		if (m_artist != null)
			str += " - " + m_artist.getName();
		return str;
	}

	public Artist getArtist() {
		return m_artist;
	}

	public String getTitle() {
		return m_title;
	}

	public String getId() {
		return m_id;
	}

	public String getCover() {
		return m_cover;
	}

	public String getTrackListLink() {
		return m_trackListLink;
	}
}
