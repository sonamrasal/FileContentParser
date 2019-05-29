package com.threadBasics;
public class JoinDemo {
	public static void main(String[] args) {
		RunnableThread thread1 = new RunnableThread("Thread1");
		RunnableThread thread2 = new RunnableThread("Thread2");
		RunnableThread thread3 = new RunnableThread("Thread3");
		System.out.println("Waiting for threads to complete execution");
		try{
			thread1.join();
			thread2.join();
			thread3.join();
		}catch(InterruptedException ie) {
			System.out.println("Main Thread interrupted");
		}
		System.out.println("All threads joined");
		for(int i = 0; i < 5; i++) {
			System.out.println("Main: " + i);
		}
		System.out.println("Completed execution");
	}
}

class RunnableThread implements Runnable {
	private Thread thread;

	public RunnableThread(String name) {
		thread = new Thread(this, name);
		thread.start();
	}

	public void join() throws InterruptedException {
		thread.join();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(thread.getName() + ": " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				System.out.println(thread.getName() + " interrupted");
			}
		}
	}

}
