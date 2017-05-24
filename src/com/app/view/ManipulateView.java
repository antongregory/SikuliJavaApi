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

public class ManipulateView extends JFrame {

	JButton openButton;
	JButton initButton;
	JButton pickClass;
	JButton compareButton;

	JLabel statusbar;
	Container c;

	public ManipulateView() {
		super("Choose Jar File");

		setSize(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		c = getContentPane();
		c.setLayout(new FlowLayout());

		openButton = new JButton("Open Manipulated jar");
		pickClass=new JButton("Pick a Class");

		initButton = new JButton("Compare Versions");
		statusbar = new JLabel("Comparison of outputs");

		// Create a file chooser that opens up as an Open dialog

		// Create a file chooser that allows you to pick a directory
		// rather than a file

		c.add(openButton);
		c.add(initButton);
		c.add(pickClass);

		c.add(statusbar);
	}

	void initialise() {
		
		pickClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("picked class");
				
					JFileChooser chooser = new JFileChooser();
					System.out.println("clicked ");
					chooser.setMultiSelectionEnabled(false);
					chooser.setFileFilter(new FileNameExtensionFilter("*.java", "java"));
					int option = chooser.showOpenDialog(ManipulateView.this);

					if (option == JFileChooser.APPROVE_OPTION) {

						File sf = chooser.getSelectedFile();

						statusbar.setText("Setting things up");

					//	Main.openOriginalBuild(sf.getAbsolutePath());

					} else {
						statusbar.setText(" canceled.");
					}
			
				
				
			}
		});
		
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();

				chooser.setMultiSelectionEnabled(false);
				chooser.setFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
				int option = chooser.showOpenDialog(ManipulateView.this);

				if (option == JFileChooser.APPROVE_OPTION) {

					File sf = chooser.getSelectedFile();

					statusbar.setText("Setting things up");

					//Main.openOriginalBuild(sf.getAbsolutePath());

				} else {
					statusbar.setText(" canceled.");
				}
			}
		});

		initButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				setVisible(false);

			}
		});

	}
	
}