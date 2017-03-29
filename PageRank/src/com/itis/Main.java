package com.itis;

import org.htmlparser.util.ParserException;

public class Main {

	/**
	 * @param args
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws ParserException,
			InterruptedException {
		MyParser pars = new MyParser("http://mywishlist.ru/");

		 PageRank pr = new PageRank();
		 pr.hyperlinkMatrix(pars.sm, pars.matrix);
		 pr.writeFile(pr.calcPR());
		
		PageRank pr2 = new PageRank();
		pr2.hyperlinkMatrix(pars.sm, pars.matrix);
		pr2.writeFile(pr2.calcPRParallel());
		
		System.out.println(pr.timeConsumedMillis+" "+pr2.timeConsumedMillisParallel);
		System.out.println("Faster: "+(pr.timeConsumedMillis-pr2.timeConsumedMillisParallel));


	}

}
