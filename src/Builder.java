import java.io.File;

import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;


public class Builder {

	public static void main(String args[]){
		
		System.out.println("testing before");
		
		
		Builder builder=new Builder();
		builder.runAntScripts("C:\\Users\\agregory\\git\\ArtOfIllusion\\ArtOfIllusion.xml");
		System.out.println("testing after");
	}
	
	private void runAntScripts(String filename){
		BuildLogger logger = new DefaultLogger();
	    logger.setOutputPrintStream(System.out);
	    logger.setErrorPrintStream(System.out);
	    logger.setMessageOutputLevel(Project.MSG_INFO);

	    Project metricsProject  = new Project();
	    metricsProject.addBuildListener(logger);
	    
	    ProjectHelper helper = ProjectHelper.getProjectHelper();
	    
	    System.out.println("project helper : "+helper.getDefaultBuildFile());
	    metricsProject.addReference("ant.projectHelper", helper);
	    

	    File buildFile=new File(filename);


	    File folder=new File(buildFile.getParent());
	   
	        buildFile = new File(filename);

	        if(buildFile.isFile()){
	        	
	        	
	            helper.parse(metricsProject, buildFile);
	            
	            metricsProject.setProperty("ant.file", buildFile.getAbsolutePath());

	            metricsProject.init();
	            metricsProject.setBaseDir(folder);
	            
	            metricsProject.executeTarget("core");
	        }
	    
	}
}
