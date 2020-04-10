## Mar 2020

# Disk Backed Queue

This program implements a queue that holds **N** number of items in memory at any given time and writes the remaining items to the disk.

**N is the integer value provided to the constructor**


#### MyQueue Class

This class extends **BaseQueue** class and takes integer **N** which is the maximum number of items that can be held in memory.

* If **N** is 0 or less than 0, IllegalArgumentException will be thrown.
* In memory queue of length **N** is created to hold the items in memory. 
* Enqueue operation will add the item to the in memory queue until the in memory queue reaches its maximum.
* Any enqueue operation done after the in memory queue reaches its maximum will add the item to the disk.
* Dequeue operation will remove the item from the in memory queue and return it.
* When the last item from the in memory queue is dequeued, the items are loaded from the disk to the in memory queue.
* Peek operation will always return the first element in the in memory queue.
* Dequeue and Peek operations will throw IllegalStateException when the queue is empty.

#### FileBasedImplementation Class

This class handles the disk related tasks like, creating files, writing to files, reading from files and deleting files. It takes **N** which is the maximum number of items that can be stored in one file. **N** will be same as the maximum in memory value **N**.

* The disk is designed to hold files and each file will contain maximum of **N** items at any given time.
* Two pointers will be used to point to the files that will help us determine the file from which we can dequeue items and to which we can enqueue items.
* The file names will be integer values starting from 1 and will get incremented by 1 for each new file that will be created. **Ex: 1.txt, 2.txt ..**
* When in memory queue is full the enqueue will write item to the disk.
* A new file will be created and the item will be written to it.
* Each file can hold upto **N** items.
* Once the file reaches its maximum, a new file will be created. Any new enqueue operations will then write the items to this new file until it reaches its maximum. This process continues.
* When the last dequeue operation is done on the in memory queue, we load **N** items from the disk. This is to reduce number of disk reads in dequeue and enqueue operations. This also keeps the peek operation to O(1).

## Example

This example shows how the disk backed queue works

* Queue **queue** of size **2** is created which is the in memory queue.
* queue.enqueue(7) - This will add the item to the in memory queue.

	```
	queue = [7, null]
	```

* queue.enqueue(8) - This will add the item to the in memory queue.

	```
	queue = [7, 8]
	```

* queue.enqueue(9) - This will add the item to file 1.txt

	```
	queue = [7, 8]
	1.txt = 9
	```

* queue.enqueue(10) - This will add the item to file 1.txt

	```
	queue = [7, 8]
	1.txt = 9 10
	```

* queue.enqueue(11) - This will add the item to file 2.txt

	```
	queue = [7, 8]
	1.txt = 9 10
	2.txt = 11
	```

* queue.dequeue() - This will remove an item from the in memory queue

	```
	queue = [8]
	1.txt = 9 10
	2.txt = 11
	```

* queue.dequeue() - This will remove an item from the in memory queue

	```
	queue = [9 10]
	1.txt = 9 10 - This will be read and deleted
	2.txt = 11
	```

* queue.dequeue() - This will remove an item from the in memory queue

	```
	queue = [10]
	2.txt = 11
	```

* queue.dequeue() - This will remove an item from the in memory queue

	```
	queue = [11]
	2.txt = 11 - This will be read and deleted
	```
	


