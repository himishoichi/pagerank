package com.itis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import org.htmlparser.*;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class MyParser {

	LinkedList<Vertex> matrix = new LinkedList<Vertex>();
	SparseMatrix sm = new SparseMatrix(100);

	String s;
	String domen;

	public MyParser(String s) throws ParserException {
		this.s = s;
		this.domen = s;
		Vertex ver = new Vertex(s);
		matrix.add(ver);
		startParser(matrix);
		makeMatrix(matrix);
		writeNamesFile(matrix);
		writeFile(matrix);

	}

	public void startParser(LinkedList<Vertex> m) throws ParserException {

		for (int i = 0; i < m.size(); i++) {

			getLinksOnPage(m.get(i));

		}

	}

	public void getLinksOnPage(Vertex ver) throws ParserException {

		String url = ver.name;
		Parser htmlParser = new Parser(url);

		try {
			NodeList tagNodeList = htmlParser
					.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

			if (tagNodeList.size() != 0) {
				for (int i = 0; i < tagNodeList.size(); i++) {
					LinkTag loopLink = (LinkTag) tagNodeList.elementAt(i);
					String loopLinkStr = loopLink.getLink();
					Vertex t = new Vertex(loopLinkStr);
					if (!loopLinkStr.equals(ver.name)
							&& !ver.inc.contains(loopLinkStr)
							&& loopLinkStr.contains(domen)) {
						ver.inc.add(loopLinkStr);
					}

					if (!loopLinkStr.endsWith(".jpg")
							&& !loopLinkStr.contains("@")
							&& !loopLinkStr.contains("#")
							&& !loopLinkStr.contains("?")
							&& loopLinkStr
									.matches("(https://www\\..*)|(http://.*\\.(ru|com)/.*)")
							&& loopLinkStr.contains(domen)) {

						if (!matrix.contains(t) && !(matrix.size() == 100)) {
							matrix.add(t);
						}
					}
				}
			}
		} catch (ParserException e) {
			e.printStackTrace(); // TODO handle error
		}

	}

	public void makeMatrix(LinkedList<Vertex> m) {

		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.size(); j++) {

				if (i != j && m.get(i).inc.contains(m.get(j).name)) {

					m.get(i).setV(m.get(i).v + 1);

					sm.put(i, j, (double) 1);
				}

			}

		}

	}

	public void writeFile(LinkedList<Vertex> l) {

		try (FileWriter writer = new FileWriter(
				"C:\\Users\\Himi\\Desktop\\matrix.txt", false)) {

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {

					writer.write(String.valueOf((int) sm.get(i, j)));
					writer.append(' ');

				}
				writer.append(System.getProperty("line.separator"));
			}
			writer.flush();

		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}

	}

	public void writeNamesFile(LinkedList<Vertex> l) {

		try (FileWriter writer = new FileWriter(
				"C:\\Users\\Himi\\Desktop\\pages.txt", false)) {
			for (int i = 0; i < l.size(); i++) {

				writer.write(l.get(i).name);
				writer.append(System.getProperty("line.separator"));

			}
			writer.flush();
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}

	}

}
