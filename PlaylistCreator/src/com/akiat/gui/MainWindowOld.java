package com.akiat.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindowOld extends JFrame {

	public MainWindowOld() {
		setSize(800, 600);
		// set location to center
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Playlist Creator");

		JPanel pan = new JPanel();
		pan.setBackground(Color.ORANGE);
		this.setContentPane(pan);

		setVisible(true);
	}
}
