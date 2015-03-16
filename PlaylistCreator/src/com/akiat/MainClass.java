package com.akiat;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import com.akiat.common.Album;
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

		Local loc = new Local("\\\\NASPI\\Nas\\Downloads\\Music");
		
		Session session = new Session("Akiat");
		Deezer deezer = (Deezer) session.addMusicPlatform(Platform.DEEZER);
		session.loadPlaylistsInfos(Platform.ALL);
		
		HashMap<String, Playlist> deezerPlaylists = deezer.getPlaylists();
		
		for (HashMap.Entry<String, Playlist> entry : deezerPlaylists.entrySet()) {
			
			DeezerPlaylist playlist = (DeezerPlaylist) entry.getValue();
			// Album / List of tracks not found for this album
			HashMap<Album, LinkedList<Track>> notFoundTracks = new HashMap<>();
			LinkedList<String> localPlaylist = loc.makePlaylist(playlist, notFoundTracks);
			
//			StringBuilder str = new StringBuilder();
//			for (String track : localPlaylist) {
//				str.append(track + "\n");
//			}
//			
			try {
				PrintWriter writer;
				writer = new PrintWriter(playlist.getTitle() + ".m3u", "UTF-8");
				
				writer.println("#PLAYLIST GENERATED FROM THIS ONE: " + playlist.getTitle() + " - " + playlist.getLink());
				for (String track : localPlaylist) {
					writer.println(track);
				}

				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (HashMap.Entry<Album, LinkedList<Track>> notFounds : notFoundTracks.entrySet()) {
				System.out.println("------------ ALBUM NOT FOUND : " + notFounds.getKey().getTitle() + " - " + notFounds.getKey().getArtist().getName() + " --------------");
				System.out.println("Tracks from album: ");
				for (Track t : notFounds.getValue()) {
					System.out.println("\t- " + t.getTitle());
				}
			}
			System.out.println("---------------------");
		}
//		
	}
}
