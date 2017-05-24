package com.app.sikuli;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.screenprocess.ScreenMapper;

public class SwingTesting {
	public static void main(String[] args) {
	    EventQueue.invokeLater( new Runnable() {
	      @Override
	      public void run() {
	        JFrame frame = new JFrame();
	        JButton button = new JButton();
	        button.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("swing testing thread num: "+Thread.currentThread().activeCount());
	            new GuiWorker(frame).execute();
	          }
	        });
	        button.setText("Test Me");
	        frame.getContentPane().add(button);
	        frame.pack();
	        frame.setVisible(true);
	      }
	    } );

	  }
	}

	class GuiWorker extends SwingWorker<Integer, Integer> {

	  /*
	  * This should just create a frame that will hold a progress bar until the
	  * work is done. Once done, it should remove the progress bar from the dialog
	  * and add a label saying the task complete.
	  */
		  Set<Region> regionKey;
		  LinkedHashMap<Region,String> map;
	  private JFrame frame = new JFrame();
	  private JDialog dialog = new JDialog(frame, "Swingworker test", true);
	  private JProgressBar progressBar = new JProgressBar();
	  private JFrame jram;

	  public GuiWorker(JFrame jrame) {
		  jram=jrame;
	    progressBar.setString("Waiting on time");
	    progressBar.setStringPainted(true);
	    progressBar.setIndeterminate(true);
	    dialog.getContentPane().add(progressBar);
	    dialog.pack();
	    dialog.setModal( false );
	    dialog.setVisible(false);
	  }

	  @Override
	  protected Integer doInBackground() throws Exception {
		
		 
		 jram.setVisible(false);  
		 //new SelectionRectangle();
		 
			Screen screen=new Screen();
			
			ScreenMapper screenMapper=new ScreenMapper(screen.h,screen.w,500);
			map=screenMapper.createRegion();
			 ArrayList<Region> unmatched=new ArrayList<Region>();
			regionKey=map.keySet();
		System.out.println( "GuiWorker.doInBackground"+Thread.activeCount() );
		//Thread.currentThread().sleep(2000);
	   
	    return 0;
	  }

	  @Override
	  protected void done() {
		  jram.setVisible(true);
	    System.out.println("done");
	    JLabel label = new JLabel("Task Complete");
	    dialog.getContentPane().remove(progressBar);
	    dialog.getContentPane().add(label);
	    dialog.getContentPane().validate();
	  }
}
