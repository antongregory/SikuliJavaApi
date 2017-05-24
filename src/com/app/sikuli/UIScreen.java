package com.app.sikuli;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.app.view.InitialiseView;
import com.screenprocess.ScreenMapper;

public class UIScreen {

	
	AsyncTaskLoader loader=new AsyncTaskLoader(null);
	Screen screen;
	  Set<Region> regionKey;
	 LinkedHashMap<Region,String> map;
	
	ScreenMapper screenMapper;
	
	 ArrayList<Region> unmatched=new ArrayList<Region>();
	
	
	public void start(){
		screen=new Screen();
		
		
		InitialiseView initView=new InitialiseView(this);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initView.setVisible(true);
				initView.initialise();
				//ui.setWaitingListener(waiter);

			}
		});
		
	}
	
	public void createMap(){
		screenMapper=new ScreenMapper(screen.h,screen.w,500);
		map=screenMapper.createRegion();
		regionKey=map.keySet();
		
	}
	
	public void printMap(){
		
		System.out.println("number of maps "+regionKey.size());
	}
	
	public AsyncTaskLoader getLoader(){
		
		return this.loader;
	}
  
}