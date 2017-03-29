package com.itis;

public class MyThread extends Thread {
	SparseMatrix s;
	SparseVector v;
	SparseVector res;
	int start;
	int end;

	public MyThread(SparseMatrix s, SparseVector v, SparseVector res,
			int start, int end) {
		this.s = s;
		this.v = v;
		this.start = start;
		this.end = end;
		this.res = res;
	}

	@Override
	public void run() {
		try {
			for (int i = start; i < end; i++) {
				res.put(i, s.rows[i].dot(v));
				//Thread.sleep(10000);
			}
			s.setRes(res);
			

		} catch (Exception e) {

		}
	}
}