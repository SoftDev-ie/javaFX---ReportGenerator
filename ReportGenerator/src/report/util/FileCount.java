package report.util;
import java.io.*;

public class FileCount {
	public static String folderPath;
		
		public static void fileRead()
		{
		//folderPath = "C:\\Users\\Chris\\Desktop\\schoolprogram";		    
		folderPath = System.getProperty("user.dir");
			
		String suffix = ".xlsx";
		File folder = new File(folderPath);
		    	
		      File[] listOfFiles = folder.listFiles();
		      for (int i = 0; i < listOfFiles.length; i++) 
		      {
		    	  if(!listOfFiles[i].isDirectory()&& listOfFiles[i].getName().toLowerCase().endsWith(suffix))
		    	  {
		    		  Const.excelFiles.add(listOfFiles[i].getName());
		    	  }     
		      }
		}
}
