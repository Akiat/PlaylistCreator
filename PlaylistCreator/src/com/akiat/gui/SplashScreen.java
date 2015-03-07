package com.akiat.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SplashScreen extends JFrame {

	private JPanel m_contentPane;
	private JTextField m_textField;

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setTitle("Playlist Creator - Select a profile");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		m_contentPane = new JPanel();
		m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(m_contentPane);
		m_contentPane.setLayout(new BoxLayout(m_contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		m_contentPane.add(panel);
		
		JList list = new JList();
		panel.add(list);
		
		JPanel panel_1 = new JPanel();
		m_contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		m_textField = new JTextField();
		panel_2.add(m_textField);
		m_textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel_3.add(btnNewButton);
		panel_3.add(btnNewButton_1);
	}

}
