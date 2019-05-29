package com.fileContentParser;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileContentParserCustomThreads {
	private static final class ParserThread implements Runnable {
		@Override
		public void run() {
			while (!filesToBeParsed.isEmpty()) {
				File candidate = filesToBeParsed.remove(0);
				if (candidate.isFile()) {
					synchronized (FileContentParserCustomThreads.class) {
						System.out.println(++counter + ", "
								+ candidate.getName() + ", "
								+ candidate.length());
					}
				} else {
					if (Files.isReadable(candidate.toPath())) {
						synchronized (FileContentParserCustomThreads.class) {
							for (File file : candidate.listFiles()) {
								if (!filesToBeParsed.contains(file)) {
									filesToBeParsed.add(file);
								}
							}
						}
					}
				}
			}
		}
	}

	private static List<File> filesToBeParsed = new ArrayList<File>();
	private static int counter = 0;

	public static void main(String[] args) throws InterruptedException {
		File baseFolder = new File("C:\\Windows");
		if (baseFolder.isDirectory()) {
			File[] allFiles = baseFolder.listFiles();
			filesToBeParsed.addAll(Arrays.asList(allFiles));

			Thread t1 = new Thread(new ParserThread());
			Thread t2 = new Thread(new ParserThread());
			t1.start();
			t2.start();
			while (t1.isAlive() && t2.isAlive()) {
				t1.join();
				t2.join();
			}
		}
		System.out.println("Parsing completed");
	}
}
