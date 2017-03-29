package com.itis;

public class SparseVector {
    private final int n;             
    private ST<Integer, Double> st;  

    public SparseVector(int n) {
        this.n  = n;
        this.st = new ST<Integer, Double>();
    }

	public void put(int i, double value) {
        if (i < 0 || i >= n) throw new RuntimeException("Illegal index");
        if (value == 0.0) st.remove(i);
        else              st.put(i, value);
    }

    public double get(int i) {
        if (i < 0 || i >= n) throw new RuntimeException("Illegal index");
        if (st.contains(i)) return st.get(i);
        else                return 0.0;
    }

    public int nnz() {
        return st.size();
    }

    public int size() {
        return n;
    }

    public double dot(SparseVector that) {
        if (this.n != that.n) throw new IllegalArgumentException("Vector lengths disagree");
        double sum = 0.0;

        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys())
                if (that.st.contains(i)) sum += this.get(i) * that.get(i);
        }
        else  {
            for (int i : that.st.keys())
                if (this.st.contains(i)) sum += this.get(i) * that.get(i);
        }
        return sum;
    }
    
    
    public SparseVector plus(SparseVector that) {
        if (this.n != that.n) throw new IllegalArgumentException("Vector lengths disagree");
        SparseVector result = new SparseVector(n);
        for (int i : this.st.keys()) result.put(i, this.get(i));
        for (int i : that.st.keys()) result.put(i, that.get(i) + result.get(i));
        return result;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
		for (int i : st.keys()) {
            s.append("(" + i + ", " + st.get(i) + ") ");
        }
        return s.toString();
    }


}