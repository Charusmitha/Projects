// Copyright (c) Docugami, Inc. All rights reserved.
package docugami.takehome.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

import docugami.takehome.implementation.MyQueue;

public class BaselineQueueTests {

    public class TestClass {
        private String value;

        public TestClass(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public BaselineQueueTests() {
        super();
    }

    private void NullTestHelper(int max) throws IllegalArgumentException, IOException {
        MyQueue<String> queue = new MyQueue<String>(String.class, max);

        // The queue must throw an exception when it is empty or enqueing a null value
        // is ambiguous.
        try {
            queue.dequeue();
            fail("Exception not thrown when dequeueing from an empty queue.");
        } catch (Exception ex) {
            assertTrue("Exception thrown from dequeue when queue is empty.", true);
        }
        
        String test = "This is a test.";
        queue.enqueue(test);
        queue.enqueue(null);

        assertEquals(2, queue.getCount());
        assertEquals(test, queue.dequeue());
        assertEquals(null, queue.dequeue());
        assertEquals(0, queue.getCount());
    }

    @Test
    public void testNegativeMax() throws IOException {
        try {
            new MyQueue<Integer>(Integer.class, -1);
            fail("Exception not thrown when passing in a negative maxInMemory value.");
        }
        catch (IllegalArgumentException ex) {
            assertTrue("IllegalArgumentException thrown when maxInMemory is negative.", true);
        }
    }

    @Test
    public void testEnqueueClass() throws IOException {
        MyQueue<TestClass> queue = new MyQueue<TestClass>(TestClass.class, 10);

        queue.enqueue(new TestClass("abc"));
        queue.enqueue(new TestClass("def"));
        queue.enqueue(new TestClass("ghi"));

        assertEquals("abc", queue.dequeue().value);
        assertEquals("def", queue.dequeue().value);
        assertEquals("ghi", queue.dequeue().value);

        queue.enqueue(new TestClass("jkl"));
        assertEquals("jkl", queue.dequeue().value);
    }

    @Test
    public void testPeek() throws IllegalArgumentException, IOException {
        MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 5);
        queue.enqueue(5);
        assertEquals((Integer)5, queue.peek());
    }

    @Test
    public void testNullEnqueue() throws IllegalArgumentException, IOException {
        this.NullTestHelper(10);
    }
    
    //Added this test case instead of this.NullTestHelper(0) in testNullEnqueue()
    @Test 
    public void testQueueCapacityZeroOrLess() throws IllegalArgumentException {
    	// The queue must throw an exception when size of the queue provided in the constructor is 0
        // or less.
        try {
        	MyQueue<String> queue = new MyQueue<String>(String.class, 0);
            fail("Exception not thrown when size of the queue is 0 or less than 0.");
        } catch (Exception ex) {
            assertTrue("Exception thrown when size of the queue is 0 or less than 0.", true);
        }
    }

    @Test
    public void testArrayIndexOutOfBoundsException() throws IOException {
        MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, 5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.enqueue(6);
        queue.enqueue(7);
    }
    
    //Modified this test case to make it compatible with my design
    @Test
    public void testMixed() throws IOException {
        int totalCount = 4;
        int halfWay = totalCount / 2;
        MyQueue<Integer> queue = new MyQueue<Integer>(Integer.class, halfWay);

        // enqueue one item and ensure that it is written to disk.
        queue.enqueue(0);
        assertEquals(1, queue.getInMemoryCount());
        assertEquals(queue.getInMemoryCount(), queue.getCount());
        assertEquals(0, queue.getOnDiskCount());

        // enqueue a bunch of items.
        for (int i = 1; i < totalCount; i++) {
            queue.enqueue(i);

            if (i < halfWay) {
                assertEquals(i + 1, queue.getInMemoryCount());
                assertEquals(queue.getInMemoryCount(), queue.getCount());
                assertEquals(0, queue.getOnDiskCount());
            } else {
                assertEquals(halfWay, queue.getInMemoryCount());
                assertEquals(i - halfWay + 1, queue.getOnDiskCount());
                assertEquals(queue.getCount(), queue.getInMemoryCount() + queue.getOnDiskCount());
            }
        }

        // dequeue some items.
        for (int i = 0; i < halfWay; i++) {
            Integer peek = queue.peek();
            assertEquals((Integer)i, peek);
            assertEquals((Integer)i, queue.dequeue());
            // When the last item from in memory queue is dequeued, N (N is max capacity 
            // of the queue) items from the disk are loaded into in memory queue immediately 
            if(i == halfWay-1) {
            	assertEquals(halfWay, queue.getInMemoryCount());
            	assertEquals(0, queue.getOnDiskCount());
            }
            else {
            	assertEquals(halfWay - (i + 1), queue.getInMemoryCount());
            	assertEquals(halfWay, queue.getOnDiskCount());
            }           
            assertEquals(queue.getCount(), queue.getInMemoryCount() + queue.getOnDiskCount());
        }

        // enqueue one more item
        queue.enqueue(totalCount);
        assertEquals(halfWay, queue.getInMemoryCount());
        assertEquals(1, queue.getOnDiskCount());
        assertEquals(queue.getCount(), queue.getInMemoryCount() + queue.getOnDiskCount());

        // Remove all items
        for (int i = halfWay, j = 1; i <= totalCount; i++, j++) {
            Integer peek = queue.peek();
            assertEquals((Integer)i, peek);
            assertEquals((Integer)i, queue.dequeue());
            // When the last item from in memory queue is dequeued, N (N is max capacity 
            // of the queue) items from the disk are loaded into in memory queue immediately 
            if(j == halfWay) {
            	assertEquals(1, queue.getInMemoryCount());
            	assertEquals(0, queue.getOnDiskCount());
            }
            else if(j < halfWay)
            {
            	assertEquals(halfWay - j, queue.getInMemoryCount());
            	assertEquals(1, queue.getOnDiskCount());
            }
            else {
            	assertEquals(0, queue.getInMemoryCount());
            	assertEquals(0, queue.getOnDiskCount());
            }
        }

        assertEquals(0, queue.getCount());
        assertEquals(0, queue.getOnDiskCount());
        assertEquals(0, queue.getInMemoryCount());

        try {
            queue.dequeue();
            fail("Exception not thrown when dequeueing from an empty queue.");
        } catch (Exception ex) {
            assertTrue("Exception thrown from dequeue when queue is empty.", true);
        }
    }
}
