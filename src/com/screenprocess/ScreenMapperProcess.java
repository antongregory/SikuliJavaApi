package com.screenprocess;

import java.util.LinkedHashMap;
import org.sikuli.script.*;
import com.rectangleregion.RectangleRegion;

public class ScreenMapperProcess {
	
	private boolean start=true;
	private int max;
	private int screenHeight,screenWidth;
	private RectangleRegion lastRegion;
	
	private int[] startingPoint,endPoint;
	
	public ScreenMapperProcess(int screenHeight,int screenWidth,int max){
		this.screenHeight=screenHeight;
		this.screenWidth=screenWidth;
		this.max=max;
		
	}
	
	public  LinkedHashMap<RectangleRegion,String> createMapper(){
		
		RectangleRegion rectangle;
		if(start){
			//startingPoint=getNextRegion(0,0);
			start=false;
		}
		else{
			
		}		
		return null;
		
	}
	
	
	public RectangleRegion createRegion(){
		RectangleRegion region=new RectangleRegion();
		int[] temp = {0,0};
		while(true){
			if(hasNextCell()){
				region.setStartingPoint(getNextStartPoint());
				//region.setEndPoint(getNextEndPoint());
			}
			else{
				if(hasNextRow()){
					temp[0]=0;
					temp[1]=lastRegion.getStartingPoint()[1]+1+max;
					lastRegion.setStartingPoint(temp);
					region=lastRegion;
				}
				else{
					break;
				}
			
			}
			lastRegion=region;
			region.printStartingPoint();
			//region.printEndPoint();
			
		}
			return region;
	}
	
	
	
	public  int[] getNextStartPoint(){
		
		int startpoint[] = {0,0};
		int lastx=0;
		int lasty=0;
		if(lastRegion==null){
			startpoint[0]=0;
			startpoint[1]=0;
		}
		else{
			
			lastx=lastRegion.getStartingPoint()[0]+max+1;
			if(lastx>getScreenWidth())
				lastx=getScreenWidth();
			lasty=lastRegion.getStartingPoint()[1];

			
			startpoint[0]=lastx;
			startpoint[1]=lasty;
			
		}
		return startpoint;
	
	}
	
	
	public  int[] getNextEndPoint(){
	
		int endpoint[] = {0,0};
		int lastx=0;
		int lasty=0;
		if(lastRegion==null){
			endpoint[0]=max;
			endpoint[1]=max;
		}
		else{
			
			lastx=lastRegion.getEndPoint()[0]+max+1;
			if(lastx>getScreenWidth())
				lastx=getScreenWidth();
			lasty=lastRegion.getEndPoint()[1];
			
			endpoint[0]=lastx;
			endpoint[1]=lasty;
			
		}
		return endpoint;
	
	
	}
	
	private boolean hasNextCell(){
		if(lastRegion==null){
			return true;
		}
		else{
			if(lastRegion.getStartingPoint()[0]==getScreenWidth()){
				return false;
			}
			else{
				return true;
			}
		}
	}
	
	private boolean hasNextRow(){
		if(lastRegion.getStartingPoint()[1]+max==getScreenHeight()){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getMax(){
		return max;
	}
	
	public static void main(String args[]){
		
		ScreenMapperProcess screen=new ScreenMapperProcess(1080, 1920,300);
		
		screen.createRegion();
	}
	
}
