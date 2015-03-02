package com.akiat.gui;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {

	private JPanel 	m_pan = new JPanel();
	
	private Button 	m_okButton = new Button("Ok");
	private Button 	m_quitButton = new Button("Quit");
	private Button 	m_newProfileButton = new Button("Add profile");
	private Button 	m_deleteProfileButton = new Button("Delete profile");
	
	public SplashScreen() {
		setSize(400, 300);
		// set location to center
		setLocationRelativeTo(null);

		m_pan.setBackground(Color.GRAY);
		
		Box b4 = Box.createVerticalBox();
	    b4.add(m_okButton);
		
		
		m_pan.add(m_newProfileButton);
		m_pan.add(m_deleteProfileButton);
		m_pan.add(m_quitButton);
		m_pan.add(m_okButton);
		
		setContentPane(m_pan);

		setVisible(true);
	}
}
