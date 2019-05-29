package com.threadBasics;
public class ExtendThreadDemo {
	public static void main(String[] args) {
		ExtendedThread thread1 = new ExtendedThread();
		ExtendedThread thread2 = new ExtendedThread();
		ExtendedThread thread3 = new ExtendedThread();
		ExtendedThread thread4 = new ExtendedThread();

		for (int i = 0; i < 5; i++) {
			System.out.println("Main: " + i);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Sleeping main thread interrupted");
			}
		}
		System.out.println("Execution completed successfully");
	}
}

class ExtendedThread extends Thread {
	public ExtendedThread() {
		start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("ExtendedThread: " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Sleeping ExtendedThread interrupted");
			}
		}
	}
}
