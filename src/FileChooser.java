import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame {


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
   
   private  void initialise(FileChooser filechooser){
	   openButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        JFileChooser chooser = new JFileChooser();
		    
		        chooser.setMultiSelectionEnabled(false);
		        chooser.setFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
		        int option = chooser.showOpenDialog(FileChooser.this);
		     
		        if (option == JFileChooser.APPROVE_OPTION) {
		        	
		          File sf = chooser.getSelectedFile();
		   
		         

		          statusbar.setText("Setting things up");
		       
		          Main.openOriginalBuild(sf.getPath(),filechooser);
		          
		        }
		        else {
		          statusbar.setText(" canceled.");
		        }
		      }
		    });
	   
	   
	   initButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		    	  filechooser.setVisible(false);
		    	  
		    	  Thread safe = new Thread(() -> {
		              System.out.println(SwingUtilities.isEventDispatchThread());
		  	        Main.initPattern();
		          });
		          safe.start();
		          try {
					Thread.sleep(3000);
					filechooser.setVisible(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    });
	   
	   
	   
   }

  public static void main(String args[]) throws InterruptedException {
	  FileChooser sfc = new FileChooser();
    sfc.setVisible(true);
    sfc.initialise(sfc);
    Thread.sleep(2000);
    System.out.println("sample");
  }
}