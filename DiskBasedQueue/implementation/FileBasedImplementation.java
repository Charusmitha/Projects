package docugami.takehome.implementation;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 *<h1>Disk implementation</h1>
 *This class deals with disk related tasks.
 *The tasks include, creating files, writing to file, reading from files, deleting files
 */
public class FileBasedImplementation<T> {
	
	private String currEnqueueFile;
	private String currDequeueFile;
	private int enqueueFileCounter;
	private int dequeueFileCounter;
	private int maxFileMemory;
	private int enqueueFileElementsCount;
	private Class<T> type;
	private HashMap<String, ObjectOutputStream> oosMap = new HashMap<>();
	private HashMap<String, ObjectInputStream> oisMap = new HashMap<>();
	
	/**
	 *This is the constructor that takes the class type and maximum disk memory value
	 *which is an integer.
	 */
	public FileBasedImplementation(Class<T> type, int maxFileMemory) {
		cleanup();
		enqueueFileCounter = 1;
		dequeueFileCounter = 1;
		currEnqueueFile = getFilePath(enqueueFileCounter);
		currDequeueFile = getFilePath(dequeueFileCounter);
		this.maxFileMemory = maxFileMemory;
		enqueueFileElementsCount = 0;
		this.type = type;
	}
	
	/**
	 *This method deletes all the existing files in the folder when the program starts up if the folder exists.
	 *If the folder does not exist, it creates one.
	 */
	public void cleanup() {
		File dir = new File("./Files");
		if(dir.exists()) {
			for(String f : dir.list()) {
				File file = new File("./Files/" + f);
				file.delete();
			}
		}	
		else {
			dir.mkdir();
		}
	}
	
	/**
	 *This method calls copyToFileIfExistsOrCreateNewAndCopy method.
	 *@param item this is the item to be written to disk.
	 */
	public void copyToFile(T item) {
    	copyToFileIfExistsOrCreateNewAndCopy(item);
	}
	
	/**
	 *This method writes the item to file if exists or creates a new file and then write the item to it.
	 *@param item this is the item to be written to disk.
	 */
	private void copyToFileIfExistsOrCreateNewAndCopy(T item) {
		try {
			File path = new File(currEnqueueFile);
			boolean exists = path.exists();
			if(exists)
			{
				if(enqueueFileElementsCount == maxFileMemory) {					
					enqueueFileElementsCount = 0;
					enqueueFileCounter++;
					createNewFileAndCopy(item);
				}
				else
				{
					writeToFile(item);
			        enqueueFileElementsCount++;
				}
			}
			else
			{
				createNewFileAndCopy(item);
			}
			
		} 
		catch (IOException e) {
		      System.out.println("An error occurred here.");
		      e.printStackTrace();
		}			
	}
	
	/**
	 *This method creates a new file and then writes the item to it.
	 *@param item this is the item to be written to disk.
	 */
	private void createNewFileAndCopy(T item) {		
		try {
			currEnqueueFile = getFilePath(enqueueFileCounter);
			File myObj = new File(currEnqueueFile);
			if (myObj.createNewFile()) {
				writeToFile(item);
				enqueueFileElementsCount++;
			}
			else {
		        System.out.println("File already exists.");
		    }
		} 
		catch (IOException e) {
		      System.out.println("An error occurred hereee.");
		      e.printStackTrace();
		}		
	}
	
	/**
	 *This method writes the item to the file.
	 *This method also stores the output stream of the file to the map.
	 *@param item this is the item to be written to disk.
	 */
	private void writeToFile(T item) throws IOException {
		ObjectOutputStream oos = null;
		if(oosMap.containsKey(currEnqueueFile)) {
			oos = oosMap.get(currEnqueueFile);
		}
		else {
			oos = new ObjectOutputStream(new FileOutputStream(currEnqueueFile, true));
			oosMap.put(currEnqueueFile, oos);
		}
		oos.writeObject(item);
	}
	
	/**
	 *This method gets the items from file and returns it to loadFromDisk() method in MyQueue class.
	 *@return T[] This is the array of items that will be returned.
	 */
	@SuppressWarnings("unchecked")
	public T[] copyFromFile() {
		File file = new File(currDequeueFile);
		boolean exists = file.exists();
		if(exists)
		{
			T[] res = readFromFile();
			if (dequeueFileCounter < enqueueFileCounter) {
				currDequeueFile = getFilePath(++dequeueFileCounter);
			} else {
				enqueueFileElementsCount = 0;
			}
			file.delete();
			return res;
			
		}
		else {
			return (T[]) Array.newInstance(type, 0);
		}
	}
	
	/**
	 *This method reads the item from the file.
	 **This method also stores the input stream of the file to the map.
	 *@return T[] These are the items read from the disk.
	 */
	@SuppressWarnings("unchecked")
	private T[] readFromFile() {
		ObjectInputStream ois = null;
		if(oisMap.containsKey(currDequeueFile)) {
			ois = oisMap.get(currDequeueFile);
		}
		else {
			try {
				ois = new ObjectInputStream(new FileInputStream(currDequeueFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			oisMap.put(currDequeueFile, ois);			
		}
		T[] res = (T[]) Array.newInstance(type, maxFileMemory);
		T item = null;
		int i = 0;
		try {
			while((item = (T) ois.readObject()) != null) {
				res[i] = item;
				i++;
			}
		} catch (IOException | ClassNotFoundException e) {
			if(e instanceof EOFException) {
				
			}
			else {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 *This method builds the file path.
	 *@param fileNumber This is the file number that is used to form the file name.
	 *@return String This is the file path.
	 */
	private String getFilePath(int fileNumber) {
		return "./Files/" + fileNumber + ".txt";
	}
	
	/**
	 *This method gets the count of items on disk.
	 *@return int This is the count of items on disk.
	 */
	public int getOnDiskCount() {
		File dir = new File("./Files");
		if(dir.exists() && dir.list().length > 0) {
			int numOfFiles = dir.list().length - 1;
			return (numOfFiles * maxFileMemory) + enqueueFileElementsCount;
		}	
		return 0;
	}	
}

