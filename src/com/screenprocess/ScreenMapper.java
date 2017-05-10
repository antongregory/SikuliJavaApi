package com.screenprocess;

import com.rectangleregion.RectangleRegion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import org.sikuli.script.*;
public class ScreenMapper {

	

	private int max;
	private int screenHeight,screenWidth;
	private RectangleRegion lastRectRegion;
	private boolean start=true;
	private Region lastRegion;
	private Screen screen;
	private LinkedHashMap<Region,String> regionMap;
	private ScreenImage screenImage;
	private String resDir;
	public ScreenMapper(int h,int w,int maxValue){
		screen =new Screen();
		regionMap=new LinkedHashMap<>();
		this.screenHeight=h;
		this.screenWidth=w;
		this.max=maxValue;
	}
	
	public  LinkedHashMap<Region,String> createRegion(){
		
		Region region = null;
		resDir="C:\\Users\\antongregory\\workspace\\SikuliMain\\res";
		while(true){
			if(start){
				region=new Region(0, 0, max,max);
				lastRegion=region;
				start=false;
			}
			else{
				if(hasNextCell()){
					region=new Region(lastRegion.x+max+1,lastRegion.y,max,max);
				
				}
				else{
					if(hasNextRow()){
						region=new Region(0,lastRegion.y+max+1,max,max);
					}
					else{
						break;
					}
				}
			}
			
			lastRegion=region;
			//region.highlight(2);
			screenImage=screen.capture(region);
			screenImage.getFile("C:\\Users\\antongregory\\workspace\\SikuliMain\\res");
			
			String url=screenImage.save(resDir);
			
			regionMap.put(region,url);
			System.out.println("Region x and y: "+region.x+","+region.y);
		}
		return regionMap;

		
		
	}

	
	

	private boolean hasNextCell(){
		System.out.println("last:"+lastRegion.getX()+" screenwidth "+screenWidth+" // added val"+lastRegion.getX()+max);
		
		if(lastRegion.getX()+max>=screenWidth){
			System.out.println("returning f");
			return false;
		}
		else{
			System.out.println("returning t");
			return true;
		}
		
		
	}
	
	
	private boolean hasNextRow(){
		if(lastRegion.getY()+max+1>=screenHeight){
			return false;
		}
		else
			return true;
		
	}
	
	

	
	public static boolean getComparison(Screen screen,Pattern ps){
		Region reg=screen.exists(ps.exact());
		if(reg != null){
			
			return true;
		}
		else{
			
			return false;
		}
			
	}
}
