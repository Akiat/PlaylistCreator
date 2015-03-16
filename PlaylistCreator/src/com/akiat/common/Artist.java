package com.akiat.common;

import org.json.JSONObject;

public class Artist {
	
	protected String m_name;
	protected String m_id;
	protected String m_link;
	protected String m_pictureLink;
	protected String m_trackListLink;

	public Artist(String artistJson) {

	}
	
//	@Override
//	public boolean equals(Object arg0) {
//		boolean equal = false;
//		Artist artist = (Artist)arg0;
//		
//		if (arg0 instanceof Artist && m_name.equals(artist.getName()))
//			equal = true;
//		
//		return equal;
//	}

	public String getName() {
		return m_name;
	}

	public String getId() {
		return m_id;
	}

	public String getLink() {
		return m_link;
	}

	public String getPictureLink() {
		return m_pictureLink;
	}

	public String getTrackListLink() {
		return m_trackListLink;
	}

	@Override
	public String toString() {
		return m_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_name == null) ? 0 : m_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Artist))
			return false;
		Artist other = (Artist) obj;
		if (m_name == null) {
			if (other.m_name != null)
				return false;
		} else if (!m_name.equals(other.m_name))
			return false;
		return true;
	}

}
