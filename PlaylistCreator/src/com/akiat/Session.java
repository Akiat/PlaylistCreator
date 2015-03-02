package com.akiat;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.akiat.common.MusicPlatform;
import com.akiat.deezer.Deezer;
import com.akiat.utils.Constants.Platform;

public class Session {

	private static Logger LOGGER = Logger.getLogger(Session.class.getName());

	private String m_userName = null;
	private String m_configFile = null; // Path

	private HashMap<Platform, MusicPlatform>	m_musicPlatformMap = new HashMap<Platform, MusicPlatform>();

	public Session(String userName) {
		m_userName = userName.trim();
		m_configFile = System.getProperty("user.home") + "/PlaylistCreator/" + m_userName;
		File configFile = new File(m_configFile);
		configFile.mkdirs();
	}

	public MusicPlatform addMusicPlatform(Platform platform)
	{
		MusicPlatform musicPlatform = null; 
		switch (platform)
		{
		case DEEZER:
			musicPlatform = new Deezer(m_configFile);
			break;
		}
		
	    m_musicPlatformMap.put(platform, musicPlatform);
		return musicPlatform;
	}

	public String getUserName() {
		return m_userName;
	}

	public void setUserName(String userName) {
		m_userName = userName;
	}

	public String getConfigFile() {
		return m_configFile;
	}

	public void setConfigFile(String configFile) {
		m_configFile = configFile;
	}

	public void loadPlaylistsInfos(Platform platform) {
		
		if (platform == Platform.ALL) {
			
			for (Entry<Platform, MusicPlatform> entry : m_musicPlatformMap.entrySet()) {
				entry.getValue().loadPlaylistsInfos();
			}
			
		} else if (m_musicPlatformMap.containsKey(platform)) {
			m_musicPlatformMap.get(platform).loadPlaylistsInfos();
		}
		
		
		
	}
}
