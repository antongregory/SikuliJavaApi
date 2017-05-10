import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JFileChooser;

import org.sikuli.script.App;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.screenprocess.ScreenMapper;

public class Main {

	public static void main(String args[]){
		Screen screen=new Screen();
		
		System.out.println("sc "+screen.h);
		ScreenMapper screenMapper=new ScreenMapper(screen.h,screen.w,500);
		
		openOriginalBuild(new String(""),null);
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
	
	public static void openOriginalBuild(String url, FileChooser chooser){
		
		
		ProcessBuilder pb = new ProcessBuilder("java", "-jar",url);
		try {
			Process p = pb.start();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		
	}
	
	public static void initPattern(){
		Screen screen=new Screen();
		ScreenMapper screenMapper=new ScreenMapper(screen.h,screen.w,500);
		LinkedHashMap<Region,String> map=screenMapper.createRegion();
		 ArrayList<Region> unmatched=new ArrayList<Region>();
		 Set<Region> regionKey=map.keySet();
	}
	
	
	
}
