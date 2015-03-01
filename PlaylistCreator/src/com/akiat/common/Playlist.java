package com.akiat.common;

import java.util.LinkedList;

import org.json.JSONObject;

public abstract class Playlist {

	/**
	 * True if we have loaded the tracklist.
	 */
	protected boolean	m_isLoaded	= false;
	protected LinkedList<Track> m_tracklist = new LinkedList<>();
	
	protected String	m_id 		= null;
	protected String 	m_title		= null;
	protected String 	m_link		= null;
	protected String 	m_picture	= null;
	protected String 	m_tracklistLink = null;
	protected int		m_duration	= 0;
	protected int		m_nbTracks	= 0;

	protected boolean	m_isPublic	= false;
	protected boolean	m_isCollaborative = false;

	protected String	m_ownerId 	= null;
	protected String	m_ownerName	= null;

	public Playlist() {
 
	}
	
	protected abstract LinkedList<Track> getTracklist();

	@Override
	public String toString() {
		return m_title + " " + m_link + " " + m_nbTracks;
	}

}
