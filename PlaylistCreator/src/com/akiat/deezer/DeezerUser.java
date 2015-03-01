package com.akiat.deezer;

import org.json.JSONObject;

import com.akiat.common.MusicPlatformUser;

public class DeezerUser extends MusicPlatformUser {

	public DeezerUser(String userInfosJson) {
		super();

		JSONObject obj = new JSONObject(userInfosJson);

		if (obj != null)
		{
			m_id = String.valueOf(obj.getInt("id"));
			m_name = obj.getString("name");
			m_firstname = obj.getString("firstname");
			m_lastname = obj.getString("lastname");
			m_birthday = obj.getString("birthday");
			m_inscriptionDate = obj.getString("inscription_date");
			m_gender = obj.getString("gender");
			m_link = obj.getString("link");
			m_picture = obj.getString("picture");
			m_country = obj.getString("country");
			m_lang = obj.getString("lang");
			m_tracklistLink = obj.getString("tracklist");
		}
	}
}
