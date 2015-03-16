package com.akiat.common;

import org.json.JSONObject;

public class Album {

	protected Artist m_artist;
	protected String m_title;
	protected String m_id;
	protected String m_coverLink;
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

//	@Override
//	public boolean equals(Object arg0) {
//		boolean equal = false;
//		Album album = (Album)arg0;
//		
//		if (arg0 instanceof Album && m_title.equals(album.getTitle()) &&
//			m_artist.equals(album.getArtist()))
//			equal = true;
//		
//		return equal;
//	}

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
		return m_coverLink;
	}

	public String getTrackListLink() {
		return m_trackListLink;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (!(obj instanceof Album))
			return false;
		Album other = (Album) obj;
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
}
