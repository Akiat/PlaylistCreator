package com.akiat.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private JPanel m_contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Playlist Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		m_contentPane = new JPanel();
		m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(m_contentPane);
		
		JPanel leftPanel = new JPanel();
		
		JPanel rightPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		JLabel lblDeezer = new JLabel("Deezer");
		leftPanel.add(lblDeezer);
		
		JLabel lblNewLabel = new JLabel("New label");
		leftPanel.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(m_contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
				.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
		);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblPlaylistTitle = new JLabel("Playlist title");
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
							.addComponent(lblPlaylistTitle)
							.addGap(230))))
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPlaylistTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblPoster = new JLabel("Poster");
		panel_2.add(lblPoster);
		
		JLabel lblTrack = new JLabel("Track 1");
		panel_1.add(lblTrack);
		rightPanel.setLayout(gl_rightPanel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximumSize(new Dimension(32767, 23));
		panel.add(progressBar);
		
		JButton btnNewButton = new JButton("Generate M3U");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnNewButton);
		m_contentPane.setLayout(gl_contentPane);
	}
}
