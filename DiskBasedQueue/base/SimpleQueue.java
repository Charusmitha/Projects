// Copyright (c) Docugami, Inc. All rights reserved.
package docugami.takehome.base;

public interface SimpleQueue<T> {
        /// The total number of items in the queue.
        int getCount();

        /// Adds an item to the queue.
        void enqueue(T item) throws IllegalStateException;

        /// Removes an item from the queue and returns it.
        T dequeue() throws IllegalStateException;

        /// Returns the first item in the queue.
        T peek() throws IllegalStateException;
}