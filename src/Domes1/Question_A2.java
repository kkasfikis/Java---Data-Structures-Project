package Domes1;

import java.util.ArrayList;
import java.util.Random;

public class Question_A2 {
	private int M;
	private int N;
	private ArrayList<LinkedList> myListOfLists;
	
	public Question_A2(int M,int N) {
		this.M = M;
		this.N = N;
		myListOfLists = new ArrayList<LinkedList>();
		for(int i=0;i<M;i++){
			myListOfLists.add(new LinkedList());
		}
	}
	
	public void generate(int samples) {
		int index = 0 ;
		int randomNum = 0;
		for(int i=0;i<samples;i++) {
			Random rand = new Random();
			randomNum = rand.nextInt(N);
			index = CalculateIndex(randomNum,randomNum);
			myListOfLists.get(index).insertElement(randomNum, randomNum);
		}
	}
	
	public int search(int x,int y) {
		int index = CalculateIndex(x,y);
		return myListOfLists.get(index).searchElement(x, y);
	}
	
	public int CalculateIndex(int x,int y) {
		int res = ((x* N + y) % M) ;
		if(res < 0) 
		{
			return res * -1;
		}
		else 
		{
			return res;
		}
	}
}
