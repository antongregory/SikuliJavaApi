import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame{


    JButton openButton;
    JButton initButton;
    JButton compareButton;

     JLabel statusbar ;
     Container c ;
   public FileChooser() {
    super("Choose Jar File");
    
    setSize(350, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

 c = getContentPane();
    c.setLayout(new FlowLayout());
    
     openButton = new JButton("Open");

     initButton=new JButton("Scan Screen");
 statusbar = 
                 new JLabel("Output of your selection will go here");

    // Create a file chooser that opens up as an Open dialog
    

  

    // Create a file chooser that allows you to pick a directory
    // rather than a file
 

    c.add(openButton);
    c.add(initButton);
    
    c.add(statusbar);
  }
   
   void initialise(){
	   openButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser chooser = new JFileChooser();
		        System.out.println("clicked ");
		        chooser.setMultiSelectionEnabled(false);
		        chooser.setFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
		        int option = chooser.showOpenDialog(FileChooser.this);
		     
		        if (option == JFileChooser.APPROVE_OPTION) {
		        	
		          File sf = chooser.getSelectedFile();
		   
		         
		          
		          statusbar.setText("Setting things up");
		       
		          Main.openOriginalBuild(sf.getAbsolutePath());
		          
		        }
		        else {
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
   
   
   public void setWaitingListener(Waiter waiterlistener){
	   
	   initButton.addActionListener(waiterlistener);
   }
   
   public static class Waiter implements ActionListener
   {
       private final CountDownLatch latch = new CountDownLatch(1);

       @Override
       public void actionPerformed(ActionEvent e)
       {
          	System.out.println("from wait istener");
           latch.countDown();
       }

       void waitFor()
       {
           try
           {
               latch.await();
           }
           catch (InterruptedException e)
           {
               Thread.currentThread().interrupt();
           }
       }
   }


 
   
   
  public static void main(String args[]) throws InterruptedException {
	  FileChooser sfc = new FileChooser();
    sfc.setVisible(true);
    //sfc.initialise(sfc);
    Thread.sleep(2000);
    System.out.println("sample");
  }
}