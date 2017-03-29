package com.itis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class PageRank {

	SparseMatrix m = new SparseMatrix(100);
	SparseVector vec = new SparseVector(100);
	SparseVector newvec = new SparseVector(100);
	LinkedList<Vertex> names;
	long timeConsumedMillis;
	long timeConsumedMillisParallel;

	public PageRank() {

		int i = 0;
		while (i != 100) {
			vec.put(i, 0.01);
			i++;
		}

	}

	public void hyperlinkMatrix(SparseMatrix s, LinkedList<Vertex> l) {
		names = l;
		for (int i = 0; i < 100; i++) {
			if (l.get(i).v != 0) {
				double r = 1 / (double) (l.get(i).v);
				for (int j = 0; j < 100; j++) {
					if (s.get(i, j) != 0) {
						m.put(j, i, r);

					}

				}
			}
		}

	}

	public void printM() {
		System.out.println(m);

	}

	public SparseVector calcPRParallel() throws InterruptedException {
		long start = System.currentTimeMillis();

		for (int i = 0; i < 20; i++) {
			vec = m.timesParallel(m, vec);
		}

		long finish = System.currentTimeMillis();
		timeConsumedMillisParallel = finish - start;

		return vec;
	}

	public SparseVector calcPR() throws InterruptedException {

		long start = System.currentTimeMillis();

		for (int i = 0; i < 20; i++) {
			vec = m.times(vec);
		}

		long finish = System.currentTimeMillis();
		timeConsumedMillis = finish - start;

		return vec;
	}

	
	
	public void writeFile(SparseVector v) {

		try (FileWriter writer = new FileWriter(
				"C:\\Users\\Himi\\Desktop\\PageRank.xlsx", false)) {

			for (int i = 0; i < 100; i++) {
				String s = String.format("%.6f", v.get(i));
				writer.write(names.get(i).name+"\t");
				writer.write(s);
				writer.append(System.getProperty("line.separator"));
			}
			writer.flush();

		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}

	}

}
