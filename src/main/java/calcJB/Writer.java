package calcJB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

	//private String error;
	private File file; 
	private FileWriter fw;
	private BufferedWriter bw ;
 	public Writer(String error) {
		//this.error = error;
		file = new File("errorFile");
		try {
		    fw = new FileWriter(file,true);
			bw = new BufferedWriter(fw);
			bw.write(error);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
		
	
	
	
}
