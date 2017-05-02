package com.rectangleregion;

public class RectangleRegion {
		
	int[] startingPoint;
	int[] endPoint;
	
	
	
	public int[] getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(int[] startingPoint) {
		this.startingPoint = startingPoint;
	}
	public int[] getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(int[] endPoint) {
		this.endPoint = endPoint;
	}
	

	public void printStartingPoint(){
		
		System.out.println("Starting point is: "+"("+startingPoint[0]+","+startingPoint[1]+")");
	}
	public void printEndPoint(){
		System.out.println("End point is : "+"("+endPoint[0]+","+endPoint[1]+")");
	}
	
		
}
