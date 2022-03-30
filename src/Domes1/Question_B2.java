package Domes1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;


public class Question_B2 {
	private int M;
	private int N;
	private int DataPageSize;
	private String fileName;
	private ArrayList<FileListNode> myList;

	RandomAccessFile MyFile;
	public Question_B2(int M,int N,int DataPageSize,String fileName) throws FileNotFoundException{
		this.M = M;
		this.N = N;
		this.DataPageSize = DataPageSize;
		this.fileName = fileName;
		MyFile = new RandomAccessFile (this.fileName, "rw");
		myList = new ArrayList<FileListNode>();
		for(int i=0;i<M;i++) {
			FileListNode t = new FileListNode(i,this.DataPageSize,this.MyFile);
			myList.add(t);
		}
		
	}
	
	
	public void generate(int samples) throws IOException {
		int index = 0 ;
		for(int i=1;i<samples;i++) {
			Random rand = new Random();
			int randomNum = rand.nextInt(N);
			index = CalculateIndex(randomNum,randomNum);
			myList.get(index).addElement(randomNum, randomNum);
		}
	}
	
	
	public int search(int x,int y) throws IOException {
		int index = CalculateIndex(x,y);
		return myList.get(index).Search(x, y);
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
