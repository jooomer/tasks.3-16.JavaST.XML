import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Sergey
 *
 */
public class FileUtilities {
	private static String fileText = "";
	
	public static void readFile(String file) {
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(file)));
			String line;
			while ((line = reader.readLine()) != null) {
				fileText += line + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void printFile() {
		System.out.println(fileText);
	}
	
	public String getFileText() {
		return fileText;
	}

}
