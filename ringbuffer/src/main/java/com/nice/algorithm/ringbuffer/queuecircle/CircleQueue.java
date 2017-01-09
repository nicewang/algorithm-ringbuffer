package com.nice.algorithm.ringbuffer.queuecircle;

/**
 * 
 * @author NiceWang
 *
 * @param <T>
 */
public class CircleQueue<T> {

	//循环队列（数组）默认大小
	private final int DEFAULT_SIZE = 1000;
	
	//循环队列（数组）的容量
	private int capacity;
	
	//数组：保存循环队列的元素
	private Object[] elementData;
	
	//队头（先进先出）
	private int head = 0;
	
	//队尾
	private int tail = 0;
	
	/**
	 * 以循环队列默认大小创建空循环队列
	 */
	public CircleQueue() {
		
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
		
	}
	
	/**
	 * 以指定长度的数组来创建循环队列
	 * @param initSize
	 */
	public CircleQueue(final int initSize) {
		
		capacity = initSize;
		elementData = new Object[capacity];
		
	}
	
	/**
	 * 获取循环队列的大小（包含元素的个数）
	 * @return
	 */
	public int size() {
		
		if(isEmpty()) {
			
			return 0;
			
		} else if(isFull()) {
			
			return capacity;
			
		} else {
			
			return tail + 1;
			
		}
		
	}
	
	/**
	 * 插入队尾一个元素
	 * @param element
	 */
	public void add(final T element) {
		
		if(isEmpty()) {
			
			elementData[0] = element;
			
		} else if(isFull()) {
			
			//当队列已经满了的时候还有元素要被写入时，队头被覆盖，队头指针指向之前队列的第二个元素
			elementData[head] = element;
			head++;
			tail++;
			head = (head == capacity) ? 0 : head;
			tail = (tail == capacity) ? 0 : tail;
			
		} else {
			
			elementData[++tail] = element;
			
		}
		
	}
	
	/**
	 * 取循环队列里的值（先进的index=0）
	 * @return
	 */
	public Object[] getQueue() {
		
		final Object[] elementDataSort = new Object[capacity];
		final Object[] elementDataCopy = elementData.clone();
		if (isEmpty()){
			
		} else if(isFull()) {
			
			int indexMax = capacity;
			int indexSort = 0;
			for (int i = head; i < indexMax;) {
				
				elementDataSort[indexSort++] = elementDataCopy[i++]; //head指向最先进入的，最先被读出
				if(i == capacity) {
					
					i = 0;
					indexMax = head;
					
				}
			}
			
		} else {
			
			for(int i = 0; i < tail; i++) {
				
				elementDataSort[i] = elementDataCopy[i];
				
			}
			
		}
		return elementDataSort;
		
	}
	
	public boolean isEmpty() {
		
		return tail == head && tail == 0 && elementData[tail] == null; //队头队尾指针均为零且它们指向的元素为空
		
	}
	
	public boolean isFull() {
		
		return head != 0 && head - tail == 1 || head == 0 && tail == capacity - 1;
		
	}
	
}
