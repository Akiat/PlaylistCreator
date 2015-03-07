package com.akiat.common;

import org.json.JSONObject;

public class Track {

	protected String 	m_id = null;
	protected String 	m_title = null;
	protected String 	m_link = null;
	protected String 	m_previewLink = null;
	protected int	 	m_duration = 0;
	protected int	 	m_rank = 0;
	protected int		m_timeAdd = 0;
	
	protected boolean 	m_readable = false;
	protected boolean 	m_explicitLyrics = false;
	
	protected Artist	m_artist = null;
	protected Album 	m_album = null;

	
	public Track(String trackJson) {
	
	}



	public String getId() {
		return m_id;
	}



	public String getTitle() {
		return m_title;
	}



	public String getLink() {
		return m_link;
	}



	public String getPreviewLink() {
		return m_previewLink;
	}



	public int getDuration() {
		return m_duration;
	}



	public int getRank() {
		return m_rank;
	}



	public int getTimeAdd() {
		return m_timeAdd;
	}



	public boolean isReadable() {
		return m_readable;
	}



	public boolean isExplicitLyrics() {
		return m_explicitLyrics;
	}



	public Artist getArtist() {
		return m_artist;
	}



	public Album getAlbum() {
		return m_album;
	}



	@Override
	public String toString() {
		String str = "TRACK: " + m_title;
		if (m_artist != null)
			str += " - " + m_artist.getName();
		if (m_album != null)
			str += " - " + m_album.getTitle();
		return str;
	}
}
