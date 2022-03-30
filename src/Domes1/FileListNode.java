package Domes1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class FileListNode {
	public long firstPage;
	public long lastPage;
	private int DataPageSize;
	private int CoordinatesPerPage;
	private RandomAccessFile MyFile;
	
	public FileListNode(long page, int DataPageSize,RandomAccessFile MyFile) {
		this.firstPage = page;
		this.lastPage = page;
		this.DataPageSize = DataPageSize;
		this.CoordinatesPerPage = DataPageSize / 8;
		this.MyFile = MyFile;
	}
	
	public long getFileSize() throws IOException {
		long size = 0;
		size = MyFile.length();
		return size;
	}
	
	public ArrayList<Coordinate> readDiskPage(long page) throws IOException{
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
        
		return coords;
	}
	
	public void writeDiskPage(long page, ArrayList<Coordinate> coords) throws IOException {
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
	}
	
	public void addElement(int x,int y) throws IOException {		
		long fileSize = getFileSize();
		long page = lastPage;
        ArrayList<Coordinate> coords = readDiskPage(page);
        
        if(coords.size() >= this.CoordinatesPerPage - 1){
        	int newPage = (int)((fileSize <= DataPageSize) ? 0 : ((fileSize / DataPageSize )));
        	
        	Coordinate tc = new Coordinate();
        	tc.x = newPage;
        	tc.y = newPage;
        	coords.add(tc);
        	
        	writeDiskPage(page,coords);

        	page = lastPage = newPage;
        	coords.clear();
    	}
        
        Coordinate coord = new Coordinate();
        coord.x = x;
    	coord.y = y;
    	coords.add(coord);

    	writeDiskPage(page,coords);
		
	}
	
	
	public int Search(int x,int y) throws IOException {
		long page = firstPage;
		boolean continueWorking = true;
		int compareCount = 0;
		while(continueWorking) {
			ArrayList<Coordinate> coords = readDiskPage(page);
			if(coords.size() == CoordinatesPerPage ) {
				for(int j=0;j<coords.size() - 1;j++) {
					compareCount++;
					if(coords.get(j).x == x && coords.get(j).y == y) {
						return compareCount;
					}
				}
				
				page = coords.get(CoordinatesPerPage - 1).x;
			}
			else {
				for(int j=0;j<coords.size() - 1;j++) {
					compareCount++;
					if(coords.get(j).x == x && coords.get(j).y == y) {
						return compareCount;
					}
				}
				continueWorking = false;
			}
		}
		return -1;
	}
}
