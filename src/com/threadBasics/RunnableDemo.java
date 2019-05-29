package com.threadBasics;
public class RunnableDemo {

	public static void main(String[] args) {
		new CustomRunnable();

		for (int i = 1; i < 10; i++) {
			System.out.println("Main: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Sleeping main thread interrupted");
			}
		}
		System.out.println("Execution completed successfully");
	}
}

class CustomRunnable implements Runnable {

	public CustomRunnable() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		for (int i = 1; i < 10; i++) {
			System.out.println("CustomRunnable: " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Sleeping CustomRunnable interrupted");
			}
		}
	}

}
