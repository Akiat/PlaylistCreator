package com.akiat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import com.akiat.common.Playlist;
import com.akiat.common.Track;
import com.akiat.deezer.Deezer;
import com.akiat.deezer.DeezerPlaylist;
import com.akiat.gui.MainWindow;
import com.akiat.gui.SplashScreenTest;
import com.akiat.utils.Constants.Platform;

public class MainClass {

	private static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	
	public static void main(String[] args) {
		//SplashScreenTest gui = new SplashScreenTest();
//		MainWindow gui = new MainWindow();
		
		Session session = new Session("Akiat");
		Deezer deezer = (Deezer) session.addMusicPlatform(Platform.DEEZER);
		session.loadPlaylistsInfos(Platform.ALL);
		
		HashMap<String, Playlist> deezerPlaylists = deezer.getPlaylists();
		
		for (HashMap.Entry<String, Playlist> entry : deezerPlaylists.entrySet()) {
			DeezerPlaylist play = (DeezerPlaylist) entry.getValue();
			LinkedList<Track> trackList = deezer.loadPlaylistTracks(entry.getKey());
			
			System.out.println("PRINT TRACK OF PLAYLIST: " + entry.getValue());
			
			for (Track track : trackList) {
				System.out.println(track.toString());
			}
		    break;
		}
	}
}
