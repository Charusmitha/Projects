// Copyright (c) Docugami, Inc. All rights reserved.

package docugami.takehome.implementation;

import java.lang.reflect.Array;
import docugami.takehome.base.BaseQueue;

/**
 *<h1>Disk backed queue</h1>
 *MyQueue class extends BaseQueue class and implements a queue that holds 'n' number
 *of nodes in memory. 'n' is the maximum number of nodes the queue can hold in memory 
 *and it is provided to the queue constructor.
 */
public class MyQueue<T> extends BaseQueue<T> {
	private T[] memoryArray;
	private int enqueuePointer;
	private int dequeuePointer;
	private int inMemoryCount;
	private int maxInMemory;
	private int maxFileMemory;
	private FileBasedImplementation<T> disk;
	private Class<T> type;

	/**
	 *This is the constructor that takes the class type and maximum in memory value
	 *which is an integer.
	 */
    @SuppressWarnings("unchecked")
	public MyQueue(Class<T> type, int maxInMemory) {
        super(maxInMemory);
        checkIfValid(maxInMemory);
        memoryArray = (T[]) Array.newInstance(type, maxInMemory);
        inMemoryCount = 0;
        this.maxInMemory = maxInMemory;
        maxFileMemory = maxInMemory;
        enqueuePointer = 0;
        dequeuePointer = 0;
        disk = new FileBasedImplementation<T>(type, maxFileMemory);
        this.type = type;
    }
    
    /**
	 *This method throws exception if the maximum size of the queue provided in constructor is 0
	 *or less than 0
	 *@param maxInMemory This is the maximum size of the queue provided in the constructor
	 */
    private void checkIfValid(int maxInMemory) throws IllegalArgumentException {
    	if(maxInMemory <= 0)
    	{
    		throw new IllegalArgumentException("Queue size should be a positive value");
    	}
    }
    
    /**
	 *This method is used to get the total number of items in the queue.
	 *@return int This returns the number of items in the queue.
	 */
    public int getCount() {   
        return getOnDiskCount() + inMemoryCount;
    }

 
    /**
     *This methods adds the item to inMemory queue if inMemory is not full.
     *If inMemory is full it calls a method to add the item to the disk.
     *@param item This is the parameter to enqueue method.
     */
    public void enqueue(T item) throws IllegalStateException {
        if(isMemoryFull()) {
        	disk.copyToFile(item);
        }
        else {
        	memoryArray[enqueuePointer] = (T) item;
    		inMemoryCount++; 
    		enqueuePointer++;
    		if(enqueuePointer == maxInMemory) {
    			enqueuePointer = 0;
    		}
        }
    }

    /**
     *This method removes an item from inMemory if its not empty.
     *If the last item inMemory is dequeued, it calls a method to load inMemory from disk.
     *If inMemory is empty it throws an exception.
     *@return T this returns the dequeued item.
     */
    public T dequeue() throws IllegalStateException {
    	if(isMemoryEmpty()) {
    		throw new IllegalStateException("Queue is empty");
    	}
        T dequeuedItem = memoryArray[dequeuePointer++];
        inMemoryCount--;
        if(dequeuePointer == maxInMemory && !isMemoryEmpty()) {
        	dequeuePointer = 0;
        }
        else if(dequeuePointer == maxInMemory || isMemoryEmpty())
        {
        	loadFromDisk();
        	dequeuePointer = 0;        	
        }              
        return dequeuedItem;
        
    }
    
    /**
     *This method loads items to inMemory from disk.
     */
    @SuppressWarnings("unchecked")
	public void loadFromDisk() {
    	T[] diskElements = disk.copyFromFile();
    	memoryArray = (T[]) Array.newInstance(type, maxInMemory);
    	enqueuePointer = 0;
    	inMemoryCount = 0;
    	for(int i=0; i<diskElements.length; i++)
    	{
    		if(diskElements[i] != null) {
    			memoryArray[i] = diskElements[i];
        		enqueuePointer++;
        		inMemoryCount++;
    		}		
    	}
    }

    /**
     *This method returns the first item in queue.
     *If inMemory is empty it throws an exception.
     *@return T This is the peeked item.
     */
    public T peek() throws IllegalStateException {
    	if(isMemoryEmpty()) {
        	throw new IllegalStateException("Queue is empty");
        }
    	return memoryArray[dequeuePointer];
    }

    /**
     *This method returns the number of items inMemory.
     *@return int This is the number of items inMemory.
     */
    @Override
    public int getInMemoryCount() {
        return inMemoryCount;
    }

    /**
     *This method tells us if inMemory if full or not.
     *@return boolean This is true if inMemory is full or false if inMemory if not full.
     */
    public boolean isMemoryFull() {
    	return inMemoryCount == maxInMemory;
    }
    
    /**
     *This method tells us if inMemory if empty or not.
     *@return boolean This is true if inMemory is empty or false if inMemory if not empty.
     */
    public boolean isMemoryEmpty() {
    	return inMemoryCount == 0;
    }

    /**
     *This method returns the count of items on disk.
     *@return int This is the count of items on the disk.
     */
	@Override
	public int getOnDiskCount() {
		return disk.getOnDiskCount();
	}
}

