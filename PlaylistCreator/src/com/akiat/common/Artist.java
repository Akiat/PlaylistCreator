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

}
