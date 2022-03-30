package Domes1;

import java.io.IOException;
import java.util.Random;

public class MainClass {
	private static int M = 500;
	private static int N = 65536;
	private static int numSearches = 1000;
	
	private static int DataPageSize = 256;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		int [] Ks = {1000,10000,50000,70000,100000};
		System.out.println("--------RESULTS OF QUESTION A1-----------");
		for (int i=0;i<Ks.length;i++) {
			int sum = 0;
			Question_A1 qA1 = new Question_A1(N);
			qA1.generate(Ks[i]);
			int tNumSearches = numSearches;
			for (int j=0; j< numSearches; j++) {
				Random rand = new Random();
				int c = rand.nextInt(N);
				int t = qA1.search(c, c);
				if(t != -1) {
					sum += t;
				}
				else {
					tNumSearches -= 1;
				}
			}
			sum = sum/tNumSearches;
			System.out.println("Found results in " + Ks[i] + " samples after " + sum + " compares in " + tNumSearches + " searches. " + (numSearches - tNumSearches) + " searches failed because the coords were not found");
		}

		System.out.println("--------RESULTS OF QUESTION A2-----------");
		for (int i=0;i<Ks.length;i++) {
			int sum = 0;
			Question_A2 qA2 = new Question_A2(M,N);
			qA2.generate(Ks[i]);
			int tNumSearches = numSearches;
			for (int j=0; j< numSearches; j++) {
				Random rand = new Random();
				int c = rand.nextInt(N);
				int t = qA2.search(c, c);
				if(t != -1) {
					sum += t;
				}
				else {
					tNumSearches -= 1;
				}
			}
			sum = sum/tNumSearches;
			System.out.println("Found results in " + Ks[i] + " samples after " + sum + " compares in " + tNumSearches + " searches. " + (numSearches - tNumSearches) + " searches failed because the coords were not found");
		}
		
		System.out.println("--------RESULTS OF QUESTION B1-----------");
		for (int i=0;i<Ks.length;i++) {
			int sum = 0;
			Question_B1 qB1 = new Question_B1(N,DataPageSize,"myFile_"+Ks[i]);
			qB1.generate(Ks[i]);
			int tNumSearches = numSearches;
			for (int j=0; j< numSearches; j++) {
				Random rand = new Random();
				int c = rand.nextInt(N);
				int t = qB1.search(c, c);
				if(t != -1) {
					sum += t;
				}
				else {
					tNumSearches -= 1;
				}
			}
			sum = sum/tNumSearches;
			System.out.println("Found results in " + Ks[i] + " samples after " + sum + " compares in " + tNumSearches + " searches. " + (numSearches - tNumSearches) + " searches failed because the coords were not found");
		}
		
		System.out.println("--------RESULTS OF QUESTION B2-----------");
		for (int i=0;i<Ks.length;i++) {
			int sum = 0;
			Question_B2 qB2 = new Question_B2(M,N,DataPageSize,"myFile1_"+Ks[i]);
			qB2.generate(Ks[i]);
			int tNumSearches = numSearches;
			for (int j=0; j< numSearches; j++) {
				Random rand = new Random();
				int c = rand.nextInt(N);
				int t = qB2.search(c, c);
				if(t != -1) {
					sum += t;
				}
				else {
					tNumSearches -= 1;
				}
			}
			sum = sum/tNumSearches;
			System.out.println("Found results in " + Ks[i] + " samples after " + sum + " compares in " + tNumSearches + " searches. " + (numSearches - tNumSearches) + " searches failed because the coords were not found");
		}
	}
}
