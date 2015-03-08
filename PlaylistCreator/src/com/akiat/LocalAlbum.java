package com.akiat;

import java.util.HashMap;
import java.util.Map.Entry;

public class LocalAlbum {

	private String m_title = null;
	private String m_artist = null;
	private HashMap<String, String> m_trackMap = new HashMap<>();
	
	public LocalAlbum(String artist, String title) {
		m_artist = artist;
		m_title = title;
	}
	
	public void addTrack(String title, String path) {
		// TODO : simplifier le title (virer espaces/tirets/underscores toussa...)
		m_trackMap.put(title, path);
	}
	
	/**
	 * @param title the track title.
	 * @return the path of the track if the track exists, null otherwise.
	 */
	public String getTrackPath(String title) {
		// TODO : conparer en pourcentage
		return m_trackMap.get(title);
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO conparer en pourcentage
		if (arg0 instanceof String){
			String albumTitle = (String) arg0;
			if (albumTitle.compareTo(m_title) == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n+++++++++++++ ALBUM: " + m_title + "\n");
		for (Entry<String, String> entry : m_trackMap.entrySet()) {
			str.append(entry.getKey());
			str.append("\n");
		}
		return str.toString();
	}
}
