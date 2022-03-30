package Domes1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;


public class Question_B1 {
	private int N;
    private int DataPageSize; // Default Data Page size
    private int CoordinatesPerPage;
    private String fileName;
    RandomAccessFile MyFile;
	public Question_B1(int N, int DataPageSize,String fileName) throws InterruptedException, FileNotFoundException {
		this.N = N;
		this.DataPageSize = DataPageSize;
		this.CoordinatesPerPage = DataPageSize / 8;
		this.fileName = fileName;
		MyFile = new RandomAccessFile (fileName, "rw");
	}
	
	public void generate(int samples) throws IOException {
		for(int i = 1;i<samples;i++) {
			Random rand = new Random();
			int randomNum = rand.nextInt(N);
			insertElement(randomNum, randomNum);
		}
	}
	
	public long getFileSize() throws IOException {
		long size = 0;
		size = MyFile.length();
		//MyFile.close();
		return size;
	}
	
	public ArrayList<Coordinate> readDiskPage(long page) throws IOException{
		RandomAccessFile MyFile = new RandomAccessFile (fileName, "r");
		byte[] ReadDataPage = new byte[DataPageSize];
		

        MyFile.seek(page * DataPageSize);
        MyFile.read(ReadDataPage); 
        
        ByteArrayInputStream bis= new ByteArrayInputStream(ReadDataPage);
        DataInputStream din= new DataInputStream(bis);

        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();

    	int tVal = din.readInt();
    	int counter = 0;
        while(tVal != 0 ) {
        	Coordinate coord = new Coordinate();
        	coord.x = tVal;
        	coord.y = din.readInt();
        	coords.add(coord);
        	counter++;
        	if(counter >= CoordinatesPerPage) 
        	{
        		break;
    		}
        	else {
            	tVal = din.readInt();
        	}
        }
        bis.close();
    	din.close();
        //MyFile.close();
        
		return coords;
	}
	
	public void writeDiskPage(long page, ArrayList<Coordinate> coords) throws IOException {
		RandomAccessFile MyFile = new RandomAccessFile (fileName, "rw");
		ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
    	DataOutputStream out = new DataOutputStream(bos);
        
        for(int i = 0 ; i< coords.size();i++) {
        	out.writeInt(coords.get(i).x);
        	out.writeInt(coords.get(i).y);
        }

    	byte[] buffer = bos.toByteArray(); 	
        byte[] WriteDataPage = new byte[DataPageSize];
        
    	System.arraycopy( buffer, 0, WriteDataPage, 0, buffer.length); // Copy buffer data to DataPage of DataPageSize
        
        MyFile.seek(page * DataPageSize);
        MyFile.write(WriteDataPage);
        bos.close();
        out.close();
        //MyFile.close();
	}
	
	public void insertElement(int x,int y) throws IOException {
		long fileSize = getFileSize();
		
		long page = 0;
		if(fileSize <= DataPageSize) {
			page = 0;
		}
		else {
			page = (fileSize / DataPageSize ) - 1;
		}
		ArrayList<Coordinate> coords;
		
		coords = readDiskPage(page);
		 
		
        if(coords.size() >= this.CoordinatesPerPage){
        	page++;
        	coords.clear();
    	}
    	
        Coordinate coord = new Coordinate();
    	coord.x = x;
    	coord.y = y;
    	
    	coords.add(coord);
    	writeDiskPage(page,coords);
	}
	
	public int search(int x, int y) throws IOException {
		long fileSize = getFileSize();
		
		long page = 0;
		if(fileSize <= DataPageSize) {
			page = 0;
		}
		else {
			page = (fileSize / DataPageSize ) - 1;
		}
		
		int compareCount = 0;
		ArrayList<Coordinate> coords;
		for(int i =0 ;i <= page;i++) {
			coords = readDiskPage(i);
			//System.out.println("Page " + i + " from " + page + " with size " + coords.size() + " and filesize: " + fileSize + "|" + CoordinatesPerPage);
			for(int j=0;j<coords.size();j++) {
				compareCount++;
				if(coords.get(j).x == x && coords.get(j).y == y) {
					return compareCount;
				}
			}
		}
		return -1;
	}
}
