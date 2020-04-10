// Copyright (c) Docugami, Inc. All rights reserved.
package docugami.takehome.tests;

import java.io.File;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import docugami.takehome.implementation.MyQueue;
public class QueueTests {

    public QueueTests() {
        super();
    }
    
    @Test
    public void testStringInput() {
    	MyQueue<String> queue = new MyQueue<String>(String.class, 2); 
    	queue.enqueue("1");
    	queue.enqueue("2");
    	Assert.assertEquals("1", queue.peek());
    }

    @Test
    public void testPeek() {
        MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2); 
        queue.enqueue(5);
        queue.enqueue(-8);        
        queue.dequeue();
        queue.enqueue(9);  
        queue.dequeue();
        Assert.assertEquals((Integer)(9), queue.peek()); 
        queue.enqueue(10);  
        Assert.assertEquals((Integer)(9), queue.peek()); 
    }
    
    @Test
    public void testDequeue() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	queue.enqueue(2);
    	queue.enqueue(3);
    	queue.enqueue(8);
    	Assert.assertEquals((Integer)(2), queue.dequeue());
    	Assert.assertEquals((Integer)(3), queue.dequeue());
    	Assert.assertEquals((Integer)(8), queue.dequeue());
    }
    
    @Test
    public void testEnqueue() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	queue.enqueue(5);
    	queue.enqueue(6);
    	Assert.assertEquals(2, queue.getInMemoryCount());
    	Assert.assertEquals((Integer)(5), queue.peek()); 
    	queue.enqueue(7);
    }
    
    @Test
    public void testWriteToDisk() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2); 
        File file1 = new File("./Files/1.txt");
        File file2 = new File("./Files/2.txt");
        queue.enqueue(5);
        queue.enqueue(-8);        
        queue.enqueue(9);
        Assert.assertTrue(file1.exists());
        queue.enqueue(6);
        queue.enqueue(7);  
        Assert.assertTrue(file2.exists());
    }
    
    @Test
    public void testInMemoryCount() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	Assert.assertEquals(0, queue.getInMemoryCount());
    	queue.enqueue(2);
    	Assert.assertEquals(1, queue.getInMemoryCount());
    }
    
    @Test
    public void testOnDiskCount() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	Assert.assertEquals(0, queue.getOnDiskCount());
    	queue.enqueue(2);
    	queue.enqueue(4);
    	Assert.assertEquals(0, queue.getOnDiskCount());
    	queue.enqueue(6);
    	Assert.assertEquals(1, queue.getOnDiskCount());
    	queue.enqueue(8);
    	queue.enqueue(10);
    	Assert.assertEquals(3, queue.getOnDiskCount());
    }
    
    @Test
    public void testCount() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	queue.enqueue(2);
    	queue.enqueue(4);
    	queue.enqueue(6);
    	queue.enqueue(8);
    	queue.enqueue(10);
    	Assert.assertEquals(5, queue.getCount());
    }
    
    @Test
    public void testIsMemoryFull() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	queue.enqueue(2);
    	Assert.assertEquals(false, queue.isMemoryFull());
    	queue.enqueue(4);
    	Assert.assertEquals(true, queue.isMemoryFull());
    	queue.dequeue();
    	Assert.assertEquals(false, queue.isMemoryFull());
    }
    
    @Test
    public void testIsMemoryEmpty() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	Assert.assertEquals(true, queue.isMemoryEmpty());
    	queue.enqueue(4);
    	Assert.assertEquals(false, queue.isMemoryEmpty());
    }
    
    @Rule public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIllegalStateExceptionForDequeue() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	thrown.expect( IllegalStateException.class );
        thrown.expectMessage("Queue is empty");
        queue.dequeue();
    }
    
    @Test
    public void testIllegalStateExceptionForPeek() {
    	MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 2);
    	thrown.expect( IllegalStateException.class );
        thrown.expectMessage("Queue is empty");
        queue.peek();
    }
    
    @Test
    public void testIllegalArgumentException() throws Exception {
    	thrown.expect( IllegalArgumentException.class );
        thrown.expectMessage("Queue size should be a positive value");
        MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 0);
    }
     
}
