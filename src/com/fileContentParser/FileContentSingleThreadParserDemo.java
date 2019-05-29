package com.fileContentParser;

import java.io.File;
import java.nio.file.Files;

public class FileContentSingleThreadParserDemo {
	static long counter = 0;

	public static void main(String[] args) {
		File baseFolder = new File("C:\\Windows");
		if (baseFolder.isDirectory()) {
			parse(baseFolder.listFiles());
		}
		System.out.println("Parsing complete");
	}

	private static void parse(File[] files) {
		for (int i = 0; i < files.length; i++) {
			File candidate = files[i];
			if (candidate.isFile()) {
				System.out.println(++counter 
						+ ", " + candidate.getName()
						+ ", " + candidate.length());
				//isReadble - required to check if have permission to access this file
				//else candidate.listFiles() returns null - leading to NPE at line 18
			} else if (candidate.isDirectory()
					&& Files.isReadable(candidate.toPath())) { 
				parse(candidate.listFiles());
			}
		}
	}
}
