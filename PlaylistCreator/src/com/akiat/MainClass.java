package com.akiat;

import java.util.logging.Logger;

import com.akiat.deezer.Deezer;
import com.akiat.gui.MainWindow;
import com.akiat.gui.SplashScreen;
import com.akiat.utils.Constants.Platform;

public class MainClass {

	private static Logger LOGGER = Logger.getLogger(MainClass.class.getName());
	
	public static void main(String[] args) {
		SplashScreen gui = new SplashScreen();
//		MainWindow gui = new MainWindow();
		
//		Session session = new Session("Akiat");
//		session.addMusicPlatform(Platform.DEEZER);
//		session.loadPlaylistsInfos(Platform.ALL);
	}
}
