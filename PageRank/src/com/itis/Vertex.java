package com.itis;

import java.util.LinkedList;

public class Vertex extends Object {
	public String name;
	public int v = 0;
	public LinkedList<String> inc = new LinkedList<String>();
	public double pagerank = 0;

	public Vertex(String name) {
		this.name = name;

	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (!(getClass() == obj.getClass()))
			return false;
		else {
			Vertex tmp = (Vertex) obj;

			String s = tmp.name;
			if (s.equalsIgnoreCase(this.name)) {
				return true;
			} else
				return false;
		}
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public boolean sameName(String s) {
		if (this.name.equals(s))
			return true;

		else
			return false;
	}

	public String getStart() {
		return name;
	}

	public void setStart(String start) {
		this.name = start;
	}

	public LinkedList<String> getEnd() {
		return inc;
	}

	public void setEnd(LinkedList<String> end) {
		this.inc = end;
	}

	public String print(Vertex v) {
		return v.name;

	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public void setPR(int pr) {
		this.pagerank = pr;
	}

	public void printInc() {

		if (this.inc.size() != 0) {
			for (int i = 0; i < this.inc.size(); i++) {
				System.out.println

				("[" + inc.get(i) + "]");
			}
			System.out.println();
		} else {

			System.out.println("[]");
		}

	}

}
