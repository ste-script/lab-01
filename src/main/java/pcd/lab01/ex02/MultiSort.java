package pcd.lab01.ex02;

import java.util.*;

public class MultiSort {

	static final int VECTOR_SIZE = 400_000_000;

	public static void main(String[] args) {
		var numberOfCpus = Runtime.getRuntime().availableProcessors();

		log("Generating array...");
		var v = genArray(VECTOR_SIZE);

		log("Array generated.");
		log("Sorting (" + VECTOR_SIZE + " elements)...");

		long t0 = System.nanoTime();
		numberOfCpus = numberOfCpus - 1;

		// sort the array using all processors

		var threads = new ArrayList<SortWorker>();
		var chunkSize = VECTOR_SIZE / numberOfCpus;
		for (int i = 0; i < numberOfCpus; i++) {
			var startPos = i * chunkSize;
			var endPos = (i == numberOfCpus - 1) ? VECTOR_SIZE : startPos + chunkSize;
			threads.add(new SortWorker(v, startPos, endPos));
		}
		threads.forEach(Thread::start);

		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Arrays.sort(v);
		long t1 = System.nanoTime();
		log("Done. Time elapsed: " + ((t1 - t0) / 1000000) + " ms");

		// dumpArray(v);
	}

	private static int[] genArray(int n) {
		Random gen = new Random(System.currentTimeMillis());
		var v = new int[n];
		for (int i = 0; i < v.length; i++) {
			v[i] = gen.nextInt();
		}
		return v;
	}

	private static void dumpArray(int[] v) {
		for (var l : v) {
			System.out.print(l + " ");
		}
		System.out.println();
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
