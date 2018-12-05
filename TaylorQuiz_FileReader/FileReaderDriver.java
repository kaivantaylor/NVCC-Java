package Taylor_quiz;

import java.io.*;
import java.util.*;

public class FileReaderDriver
{
	public static void main(String[] args) throws FileNotFoundException {
		Boolean try_again = true;
		while (try_again) {
			try {
				Scanner sc_main = new Scanner(System.in);
				String user_input;

				File folder = new File("src/Taylor_quiz");
				File[] listOfFiles = folder.listFiles();

				System.out.println("Avaialable Files:\n");
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile()) {
						if (listOfFiles[i].getName().contains(".txt")) {
							System.out.println("File " + listOfFiles[i].getName());
						}
					} else if (listOfFiles[i].isDirectory()) {
						System.out.println("Directory " + listOfFiles[i].getName());
					}
				}

				System.out.println("\nPlease enter the file name to be read:\n> ");
				user_input = sc_main.next();
				ReadFromFile("src/Taylor_quiz/" + user_input);
				try_again = false;
			} catch (Exception e) {
				
				System.out.println("\nInvalid input! Try again.");
				System.out.println("-----------------------------");
			}
		}
	}

  public static void ReadFromFile(String fname) throws FileNotFoundException
  {
    // Create map
    MapInterface<String, String> pairs = new HMap<String, String>();

    // Set up file reading
    FileReader fin = new FileReader(fname);
    Scanner info = new Scanner(fin);
    info.useDelimiter("[#\\n\\r]");  // delimiters are # signs, line feeds,
                                     // carriage returns

    // get information about the key and value
    String keyInfo = info.next();
    String valueInfo = info.next();
    info.nextLine();
    
    // Reads the key/value pairs from the file and puts them into the map
    String key, value;
    while (info.hasNext())      
    {
      key = info.next();   value = info.next();
      info.nextLine();
      pairs.put(key, value);
    }
    
    // Interact with user, getting keys and displaying value
    Scanner scan = new Scanner(System.in);
    final String STOP = "~";     
    key = null;
		while (!STOP.equals(key)) {
			System.out.println("\nEnter " + keyInfo + " (" + STOP + " to exit):");
			key = scan.next();
			if (!STOP.equals(key))
				if (pairs.contains(key)) {
					//System.out.print(pairs.get(key));
					System.out.println(valueInfo + "\t" + pairs.get(key));
				} else {
					System.out.println("\tNo information available.");
				}
		}
  } 
}