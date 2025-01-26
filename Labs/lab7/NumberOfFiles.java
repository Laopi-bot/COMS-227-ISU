package lab7;

import java.io.File;

public class NumberOfFiles {
	
	public static int numberOfFiles = 0;
	
	public static void main(String[] args) {
		// Choose the directory you want to list.
		// If running in Eclipse, "." will just be the current project directory.
		// Use ".." to list the whole workspace directory, or enter a path to
		// some other directory.
		File rootDirectory = new File(".");

		System.out.println(listFilesInDirectory(rootDirectory));
	}

	public static int listFilesInDirectory(File f) {
		if (f.isFile()) {
			return numberOfFiles++;
		} 
		else {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; ++i) {
				listFilesInDirectory(files[i]);
			}
			return numberOfFiles;
		}
	}
}
