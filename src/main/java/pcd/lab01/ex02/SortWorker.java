package pcd.lab01.ex02;

import java.util.*;

public class SortWorker extends Thread {

	private int[] v;
	private int startPos;
	private int endPos;
	private volatile boolean finished = false;

	public SortWorker(int[] v, int startPos, int endPos) {
		this.v = v;
		this.startPos = startPos;
		this.endPos = endPos;
		this.finished = false;
	}

	public void run() {
		long t0 = System.nanoTime();
		Arrays.sort(v, startPos, endPos);
		long t1 = System.nanoTime();
		this.finished = true;
		var threadName = Thread.currentThread().getName();
		System.out.println("Thread " + threadName + "Done. Time elapsed: " + ((t1 - t0) / 1000000) + " ms");
	}

}
