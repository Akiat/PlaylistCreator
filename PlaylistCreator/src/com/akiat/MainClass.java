package com.akiat;

import java.util.logging.Logger;

import com.akiat.deezer.Deezer;
import com.akiat.utils.Constants;

public class MainClass {

	private static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	
	public static void main(String[] args) {
		Session session = new Session("Akiat");
		Deezer deezer = (Deezer) session.addMusicPlatform(Constants.Platform.DEEZER);
		deezer.loadPlaylistsInfos();
	}
}
