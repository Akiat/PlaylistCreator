package com.akiat.deezer;

import org.json.JSONObject;

import com.akiat.common.MusicPlatformUser;

public class DeezerUser extends MusicPlatformUser {

	public DeezerUser(String userInfosJson) {
		super();

		JSONObject obj = new JSONObject(userInfosJson);

		if (obj != null)
		{
			if (obj.has("id"))
				m_id = String.valueOf(obj.getInt("id"));
			if (obj.has("name"))
				m_name = obj.getString("name");
			if (obj.has("firstname"))
				m_firstname = obj.getString("firstname");
			if (obj.has("lastname"))
				m_lastname = obj.getString("lastname");
			if (obj.has("birthday"))
				m_birthday = obj.getString("birthday");
			if (obj.has("inscription_date"))
				m_inscriptionDate = obj.getString("inscription_date");
			if (obj.has("gender"))
				m_gender = obj.getString("gender");
			if (obj.has("link"))
				m_link = obj.getString("link");
			if (obj.has("picture"))
				m_picture = obj.getString("picture");
			if (obj.has("country"))
				m_country = obj.getString("country");
			if (obj.has("lang"))
				m_lang = obj.getString("lang");
			if (obj.has("tracklist"))
				m_tracklistLink = obj.getString("tracklist");
		}
	}
}
