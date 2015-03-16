package com.akiat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import com.akiat.common.Album;
import com.akiat.common.Playlist;
import com.akiat.common.Track;

public class Local {

	String[] authorizedExtensions = { "mp3", "mp4", "flac", "ogg", "wma" };

	// Artist / AlbumList
	HashMap<String, ArtistAlbums> m_albumByArtistMap = new HashMap<>();

	public Local(String path) {

		loadLocalMusic(path);
		dumpAllTracks();
	}

	/**
	 * @param playlist the playlist that you want to convert to a local playlist
	 * @param notFoundTracks a hashmap that will be filled by the method, which will contains albums that are not found and tracks that are not found for this album
	 * @return a list of local filename
	 */
	public LinkedList<String> makePlaylist(Playlist playlist, HashMap<Album, LinkedList<Track>> notFoundTracks) {

		LinkedList<String> localPlaylist = new LinkedList<>();
		LinkedList<Track> trackList = playlist.getTracklist();

		for (Track track : trackList) {
			String foundedTrack = getTrack(track.getArtist().getName(), track.getAlbum().getTitle(), track.getTitle());
			if (foundedTrack != null)
				localPlaylist.add(foundedTrack);
			else {
				//TODO: ce get ne marche pas (pas le meme album) faire le equals sur album tracks et artist
				LinkedList<Track> tracks = notFoundTracks.get(track.getAlbum());
				if (tracks == null) {
					tracks = new LinkedList<>();
					notFoundTracks.put(track.getAlbum(), tracks);
				}
				tracks.add(track);
			}
		}

		return localPlaylist;
	}

	public String getTrack(String artistName, String albumTitle, String trackTitle) {

		String path = null;

		ArtistAlbums artistAlbums =  m_albumByArtistMap.get(artistName);
		if (artistAlbums != null) {
			path = artistAlbums.getTrack(albumTitle, trackTitle);
		}

		return path;
	}

	public void dumpAllTracks() {
		System.out.println("----------------- BEGIN DUMP ALL TRACKS -----------------------");
		for (Entry<String, ArtistAlbums> artistAlbumsEntry : m_albumByArtistMap.entrySet()) {
			System.out.println("************ BEGIN ARTIST: " + artistAlbumsEntry.getKey());
			System.out.println(artistAlbumsEntry.getValue().toString());
			System.out.println("************ END ARTIST: " + artistAlbumsEntry.getKey());
		}
		System.out.println("----------------- END DUMP ALL TRACKS -----------------------");
	}

	private void addTrack(String artistName, String albumTitle, String trackTitle, String trackPath) {

		ArtistAlbums artistAlbums = m_albumByArtistMap.get(artistName);
		if (artistAlbums == null)
			artistAlbums = new ArtistAlbums(artistName);

		artistAlbums.addTrack(trackTitle, trackPath, albumTitle);
		m_albumByArtistMap.put(artistName, artistAlbums);
	}

	//	ArrayList allFiles, 
	private void loadLocalMusic(String root) {
		File f = new File(root);
		File[] listFiles = f.listFiles();

		if (listFiles != null) {
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isDirectory()) 
					loadLocalMusic(listFiles[i].toString());
				else {
					File actualFile = listFiles[i];

					String ext = FilenameUtils.getExtension(actualFile.getPath());
					if (Arrays.asList(authorizedExtensions).contains(ext)) {

						//----- JAUDIO Tagger
						AudioFile audioFile;
						try {
							audioFile = AudioFileIO.read(actualFile);
							Tag tag = audioFile.getTag();
							String artistName = tag.getFirst(FieldKey.ARTIST);
							String albumTitle = tag.getFirst(FieldKey.ALBUM);
							String trackTitle = tag.getFirst(FieldKey.TITLE);

							addTrack(artistName, albumTitle, trackTitle, actualFile.getPath());
							//System.out.println("JAudioTagger: " + trackTitle + " " + artistName + " " + albumTitle);
							//---------------

						} catch (CannotReadException | IOException | ReadOnlyFileException | TagException | InvalidAudioFrameException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}  
}
