package Domes1;

import java.util.Random;

public class Question_A1 {
	private int N;
	private LinkedList myList;

	public Question_A1(int N){
		this.N = N;
		myList = new LinkedList();
	}
	
	public void generate(int samples) {
		int randomNum = 0;
		for (int i=0;i<samples;i++) {
			Random rand = new Random();
			randomNum = rand.nextInt(N);
			myList.insertElement(randomNum, randomNum);
		}
	}
	
	public int search(int x,int y) {
		return myList.searchElement(x, y);
	}
	
}
