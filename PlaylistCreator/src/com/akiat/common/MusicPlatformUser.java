package com.akiat.common;


public class MusicPlatformUser {

	protected String m_accessToken = null;
	protected String m_id = null;
	protected String m_name = null;
	protected String m_firstname = null;
	protected String m_lastname = null;
	protected String m_birthday = null;
	protected String m_inscriptionDate = null;
	protected String m_gender = null;
	protected String m_link = null; // profile link
	protected String m_picture = null;
	protected String m_country = null;
	protected String m_lang = null;
	protected String m_tracklistLink = null; // tracklist link
	
	
	public MusicPlatformUser() {

	}
	
	public String getId() {
		return m_id;
	}

	public void setId(String id) {
		m_id = id;
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getFirstname() {
		return m_firstname;
	}

	public void setFirstname(String firstname) {
		m_firstname = firstname;
	}

	public String getLastname() {
		return m_lastname;
	}

	public void setLastname(String lastname) {
		m_lastname = lastname;
	}

	public String getBirthday() {
		return m_birthday;
	}

	public void setBirthday(String birthday) {
		m_birthday = birthday;
	}

	public String getInscriptionDate() {
		return m_inscriptionDate;
	}

	public void setInscriptionDate(String inscriptionDate) {
		m_inscriptionDate = inscriptionDate;
	}

	public String getGender() {
		return m_gender;
	}

	public void setGender(String gender) {
		m_gender = gender;
	}

	public String getLink() {
		return m_link;
	}

	public void setLink(String link) {
		m_link = link;
	}

	public String getPicture() {
		return m_picture;
	}

	public void setPicture(String picture) {
		m_picture = picture;
	}

	public String getCountry() {
		return m_country;
	}

	public void setCountry(String country) {
		m_country = country;
	}

	public String getLang() {
		return m_lang;
	}

	public void setLang(String lang) {
		m_lang = lang;
	}

	public String getTracklistLink() {
		return m_tracklistLink;
	}

	public void setTracklistLink(String tracklistLink) {
		m_tracklistLink = tracklistLink;
	}

	public String getAccessToken() {
		return m_accessToken;
	}

	public void setAccessToken(String accessToken) {
		m_accessToken = accessToken;
	}
}
