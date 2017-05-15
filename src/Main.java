import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import org.sikuli.script.App;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.screenprocess.ScreenMapper;




public class Main {

	static FileChooser fileChooser;
	 static Set<Region> regionKey;
	 static LinkedHashMap<Region,String> map;
		static Screen screen;
		
	public static void main(String args[]) throws InterruptedException{

		FileChooser.Waiter waiter = new FileChooser.Waiter();
		
		fileChooser=new FileChooser();
		
		   SwingUtilities.invokeLater(new Runnable()
	        {
	            @Override
	            public void run()
	            {
	            	fileChooser.setVisible(true);
	            	fileChooser.initialise();
	            	fileChooser.setWaitingListener(waiter);
	            	
	            }
	        });
		   
		   waiter.waitFor();
		   System.out.println("App opened");
		   initPattern();
		   ManipulatedBuild.Waiter mWaiter=new   ManipulatedBuild.Waiter();
		   ManipulatedBuild mBuild=new ManipulatedBuild();
		   
		   SwingUtilities.invokeLater(new Runnable()
	        {
	            @Override
	            public void run()
	            {
	            	mBuild.setVisible(true);
	            	mBuild.initialise();
	            	mBuild.setWaitingListener(mWaiter);
	            	
	            }
	        });
		   mWaiter.waitFor();
		   compareResult();

		   
		   
//		Screen screen=new Screen();
//		
//		System.out.println("sc "+screen.h);
//		ScreenMapper screenMapper=new ScreenMapper(screen.h,screen.w,500);
//		
//		openOriginalBuild(new String(""),null);
		//App.focus("Original.aoi");
		/* LinkedHashMap<Region,String> map=screenMapper.createRegion();
		 ArrayList<Region> unmatched=new ArrayList<Region>();
		 Set<Region> regionKey=map.keySet();
		 App.focus("Untitled");
		 for(Region region:regionKey){
	            
	            System.out.println("region: "+map.get(region));
	            Pattern ps=new Pattern(map.get(region));
	            if(ScreenMapper.getComparison(screen,ps)){
	            	System.out.println("printing blue");
	            	region.highlight(1,"BLUE");
	            }
	            else{
	            	System.out.println("printing red");
	            	region.highlight(1,"RED");
	            }
	        
		 }*/
	
	}
	
	public static void compareResult(){
		
		System.out.println("trying to focus");

		System.out.println("focus");
		  ArrayList<Region> unmatched=new ArrayList<>();
		for(Region region:regionKey){
            
            System.out.println("region: "+map.get(region));
            Pattern ps=new Pattern(map.get(region));
          
            if(ScreenMapper.getComparison(screen,ps.exact())){
            	System.out.println("printing blue");
            	
            	//region.highlight(1,"BLUE");
            }
            else{
            	unmatched.add(region);
            	System.out.println("printing red");
            	//region.highlight(1,"RED");
            }
        
	 }
		if(unmatched.size()==0){
			System.out.println("unamathc");
		}
		for(Region region:unmatched){
			region.highlight(1,"RED");
		}
	}
	
	public static void openOriginalBuild(String url){
		
		
		ProcessBuilder pb = new ProcessBuilder("java", "-jar",url);
		try {
			Process p = pb.start();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		
	}
	
	public static void initPattern(){
		screen=new Screen();
		
		ScreenMapper screenMapper=new ScreenMapper(screen.h,screen.w,500);
		map=screenMapper.createRegion();
		 ArrayList<Region> unmatched=new ArrayList<Region>();
		regionKey=map.keySet();
		 
	}
	
	
	
}
