package Ex1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	public static String readFile(String nameOfFile) throws IOException{ //read only from the project folder
		
		String current = new java.io.File( "." ).getCanonicalPath();
		current += "\\" + nameOfFile;
		String data = "";
		FileInputStream inputStream = new FileInputStream(current);
		
		int c;
		while ((c = inputStream.read()) != -1) {
			data += (char)c;
		}
		inputStream.close();
		return data;
	}
	
	public static void writeFile(String nameOfFile, String data) throws IOException { //write only to project folder
	
		String current = new java.io.File( "." ).getCanonicalPath();
		current += "\\" + nameOfFile;
		FileOutputStream outputStream = new FileOutputStream(current);
		outputStream.write(data.getBytes());
		outputStream.close();
	}
}
