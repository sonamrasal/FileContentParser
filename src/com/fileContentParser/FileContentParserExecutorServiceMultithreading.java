package com.fileContentParser;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileContentParserExecutorServiceMultithreading {
	private static int counter = 0;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(25);

	public static void main(String[] args) {
		File baseFolder = new File("C:\\Windows");
		if (baseFolder.isDirectory()) {
			parse(baseFolder.listFiles());
		}
	}

	private static void parse(File[] files) {

		for (int i = 0; i < files.length; i++) {
			File candidate = files[i];
			if (candidate.isFile()) {
				threadPool.execute(new Runnable() {

					@Override
					public void run() {
						synchronized (FileContentParserExecutorServiceMultithreading.class) {
							System.out.println(++counter 
									+ ", " + candidate.getName()
									+ ", " + candidate.length());
						}
						
					}
				});
			} else if (candidate.isDirectory()
					&& Files.isReadable(candidate.toPath())) {
				parse(candidate.listFiles());
			}
		}
	}
}
