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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_album == null) ? 0 : m_album.hashCode());
		result = prime * result
				+ ((m_artist == null) ? 0 : m_artist.hashCode());
		result = prime * result + ((m_title == null) ? 0 : m_title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Track))
			return false;
		Track other = (Track) obj;
		if (m_album == null) {
			if (other.m_album != null)
				return false;
		} else if (!m_album.equals(other.m_album))
			return false;
		if (m_artist == null) {
			if (other.m_artist != null)
				return false;
		} else if (!m_artist.equals(other.m_artist))
			return false;
		if (m_title == null) {
			if (other.m_title != null)
				return false;
		} else if (!m_title.equals(other.m_title))
			return false;
		return true;
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
