package com.app.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Utilities;

import com.app.utilities.Utility;
import com.sun.tools.corba.se.idl.toJavaPortable.Util;
import com.app.sikuli.SelectionRectangle;
import com.app.sikuli.UIScreen;
import com.app.utilities.*;

public class InitialiseView extends JFrame implements View{

	JButton openButton;
	JButton initScanButton;
	JButton compareButton;
	JButton nextButton;
	JButton ignoreArea;
	JLabel statusbar;
	Container c;
	UIScreen s;
	SelectionRectangle ignoreAeaSelector;

	public InitialiseView(UIScreen s) {

		super("Choose Jar File");
		this.s = s;
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		c = getContentPane();
		c.setLayout(new FlowLayout());

		openButton = new JButton("Open");

		initScanButton = new JButton("Scan Screen");
		nextButton = new JButton("Next >>");

		ignoreArea = new JButton("Choose Ignore area");
		statusbar = new JLabel("Current status:");
		ignoreAeaSelector = new SelectionRectangle(this);
		// Create a file chooser that opens up as an Open dialog

		// Create a file chooser that allows you to pick a directory
		// rather than a file

		c.add(openButton);
		c.add(initScanButton);
		c.add(nextButton);

		
		c.add(ignoreArea);
	
		c.add(statusbar);
	}

	public void initialise() {
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
				int option = chooser.showOpenDialog(InitialiseView.this);

				if (option == JFileChooser.APPROVE_OPTION) {

					File choosenDirectory = chooser.getSelectedFile();
					try {
						Utility.openOriginalBuild(choosenDirectory.getAbsolutePath());
						statusbar.setText("Setting things up");
					} catch (Exception e) {
						// TODO: handle exception
						statusbar.setText("Failed to open due to:" + e.getMessage());
					}

				} else {
					statusbar.setText(" App open cancelled.");
				}
			}
		});

		initScanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Initiated");
				setVisible(false);

				s.getLoader().setTask(() -> s.createMap());

				s.getLoader().setFinalTask(() -> InitialiseView.this.setVisible(true));
				s.getLoader().execute();
			}
		});

		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s.printMap();
			}
		});

		ignoreArea.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InitialiseView.this.setVisible(false);
				ignoreAeaSelector.start();
			}
		});

	}
	
	@Override
	public void setStatus(String message) {
		statusbar.setText(message);
	}

	@Override
	public void setVisibility(boolean status) {
		// TODO Auto-generated method stub
		InitialiseView.this.setVisible(status);
		
	}

}
