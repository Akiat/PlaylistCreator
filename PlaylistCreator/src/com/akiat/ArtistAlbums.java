package com.akiat;

import java.util.HashMap;
import java.util.Map.Entry;

public class ArtistAlbums {

	private String m_artistName = null;

	private HashMap<String, LocalAlbum> m_albumMap = new HashMap<>();

	public ArtistAlbums(String artistName) {
		m_artistName = artistName;
	}

	public void addTrack(String trackTitle, String trackPath, String albumTitle) {

		LocalAlbum album = m_albumMap.get(albumTitle);
		if (album == null) {
			album = new LocalAlbum(m_artistName, albumTitle);
			m_albumMap.put(albumTitle, album);
		}
		album.addTrack(trackTitle, trackPath);
	}
	
	/**
	 * @param albumTitle the album title.
	 * @return the album if it exists, null otherwise.
	 */
	private LocalAlbum getAlbum(String albumTitle) {
		
		return m_albumMap.get(albumTitle);
	}
	
	/**
	 * @param albumTitle the album title.
	 * @param trackTitle the track title.
	 * @return the track path if the track exists, null otherwise.
	 */
	public String getTrack(String albumTitle, String trackTitle) {
		String trackPath = null;
		
		LocalAlbum album = getAlbum(albumTitle);
		if (album != null)
			trackPath = album.getTrackPath(trackTitle);

		return trackPath;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for (Entry<String, LocalAlbum> albumEntry : m_albumMap.entrySet()) {
			str.append(albumEntry.getValue().toString());
		}
		
		return str.toString();
	}
}
