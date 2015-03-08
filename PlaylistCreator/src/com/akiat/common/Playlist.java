package com.akiat.common;

import java.util.LinkedList;

import org.json.JSONObject;

/**
 * @author Akiat
 *
 */
public abstract class Playlist {

	/**
	 * True if we have loaded the tracklist.
	 */
	protected LinkedList<Track> m_tracklist = null;
	
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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("PLAYLIST: " + m_title + " - NB TRACKS: " + m_nbTracks + " " + m_link);
		for (Track track : m_tracklist) {
			str.append(track.toString());
		}
		return str.toString();
	}

	public LinkedList<Track> getTracklist() {
		return m_tracklist;
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

	public String getPicture() {
		return m_picture;
	}

	public String getTracklistLink() {
		return m_tracklistLink;
	}

	public int getDuration() {
		return m_duration;
	}

	public int getNbTracks() {
		return m_nbTracks;
	}

	public boolean isPublic() {
		return m_isPublic;
	}

	public boolean isCollaborative() {
		return m_isCollaborative;
	}

	public String getOwnerId() {
		return m_ownerId;
	}

	public String getOwnerName() {
		return m_ownerName;
	}

	public void setTracklist(LinkedList<Track> tracklist) {
		m_tracklist = tracklist;
	}

}
