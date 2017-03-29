package com.itis;

public class SparseMatrix {
	private final int n;
	public SparseVector[] rows;
	public SparseVector res = new SparseVector(100);

	public SparseMatrix(int n) {
		this.n = n;
		rows = new SparseVector[n];
		for (int i = 0; i < n; i++)
			rows[i] = new SparseVector(n);
	}

	public void put(int i, int j, double value) {
		if (i < 0 || i >= n)
			throw new RuntimeException("Illegal index");
		if (j < 0 || j >= n)
			throw new RuntimeException("Illegal index");
		rows[i].put(j, value);
	}

	public double get(int i, int j) {
		if (i < 0 || i >= n)
			throw new RuntimeException("Illegal index");
		if (j < 0 || j >= n)
			throw new RuntimeException("Illegal index");
		return rows[i].get(j);
	}

	public SparseVector times(SparseVector x) {
		if (n != x.size())
			throw new IllegalArgumentException("Dimensions disagree");
		SparseVector b = new SparseVector(n);
		for (int i = 0; i < n; i++) {
			b.put(i, this.rows[i].dot(x));
		}
		return b;
	}

	//синхронизация
	public synchronized SparseVector timesParallel(SparseMatrix m, SparseVector x)
			throws InterruptedException {
		SparseVector b = new SparseVector(n);
		for (int i = 0; i < 100; i = i + 20) {
			Thread first = new MyThread(m, x, b, i, i + 20);
			first.start();
		}

		return b;
	}

	public SparseVector getRes() {
		return res;
	}

	public void setRes(SparseVector res) {
		this.res = res;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < n; i++) {
			s.append(i + ": " + rows[i] + "\n");
		}
		return s.toString();
	}

}
