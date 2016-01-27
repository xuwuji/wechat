package com.xuwuji.wechat.common.algorithm;

/**
 * A max oriented priority queue
 * 
 * The first element stores in index 1
 * 
 * the capacity of elements should be n+1
 * 
 * use this max pq to find a series of a given number of smallest elements
 * 
 * for example, find 10 smallest elements in 1 million elements
 * 
 * because this is a max-oriented heap, so we always know that the maximum is in
 * the top, set a heap with 10 capacity, scan all elements, if the new element
 * is smaller than top, swap them and reorder the pq, so the heap will always
 * store "small" elements
 * 
 * @author wuxu
 *
 *         2016-1-27
 */
public class MaxPQStarts1<Key extends Comparable<Key>> {

	private Key[] array;
	// size of elements in the array, also the last element index
	private int size;

	public MaxPQStarts1(int capacity) {
		array = (Key[]) new Comparable[capacity + 1];
	}

	private boolean isEmpty() {
		return size == 0;
	}

	private boolean isFull() {
		return size + 1 == array.length;
	}

	private boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}

	/**
	 * return the index of the current minimum element
	 * 
	 * @return
	 */
	private int findMin() {
		int min = 1;
		int k = 1;
		while (2 * k < size) {
			if ((1 + 2 * k) <= size && less(array[2 * k + 1], array[2 * k])) {
				min = 2 * k + 1;
			} else {
				min = 2 * k;
			}
			k = min;
		}
		System.out.println("min" + min);
		return min;
	}

	private void swap(int i, int j) {
		Key temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * insert a new element into the priority queue
	 * 
	 * if the queue is full, change it with the minimum
	 * 
	 * @param element
	 */
	public void insert(Key element) {
		if (isFull()) {
			Key max = this.array[1];
			if (less(element, max)) {
				array[1] = element;
				sink(1);
			}
		} else {
			size++;
			// System.out.println("index:" + size);
			array[size] = element;
			swim(size);
		}
	}

	/**
	 * swim a new element, make it to the right place
	 * 
	 * @param index
	 */
	private void swim(int index) {
		while (index > 1) {
			if (less(array[index / 2], array[index])) {
				swap(index / 2, index);
			}
			index = index / 2;
		}
	}

	/**
	 * sink a element, make it to the right place
	 * 
	 * @param index
	 */
	private void sink(int index) {
		int biggerChild = 2 * index;
		while (biggerChild < size) {
			if ((1 + biggerChild) <= size && less(array[biggerChild], array[biggerChild + 1])) {
				biggerChild = biggerChild + 1;
			}
			if (less(array[index], array[biggerChild])) {
				swap(index, biggerChild);
			} else {
				break;
			}
			index = biggerChild;
		}
	}

	public Key[] getArray() {
		return this.array;
	}

	public static void main(String[] args) {
		MaxPQStarts1<Integer> pq = new MaxPQStarts1<Integer>(10);
		for (int i = 1; i < 100000000; i++) {
			pq.insert(i);
		}
		for (Comparable key : pq.array) {
			System.out.println(key);
		}
	}

}
