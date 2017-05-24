package com.app.utilities;

import java.io.IOException;

public class Utility {

	
	public static void openOriginalBuild(String url) throws IOException{
		
		ProcessBuilder pb = new ProcessBuilder("java", "-jar",url);

			Process p = pb.start();
	
		
		
	}
}
