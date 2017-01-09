package com.nice.algorithm.ringbuffer.main;

import com.nice.algorithm.ringbuffer.queuecircle.CircleQueue;

/**
 * 
 * @author NiceWang
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
    	CircleQueue<Integer> queue = new CircleQueue<Integer>(20);
    	
        for(int i = 0; i < 1000; i++) {
        	
        	queue.add(i);
        	
        }
        
        final Object[] queueArray = queue.getQueue();
        
        System.out.println("按入队列的先后顺序打印出来：");
        
        for (Object o : queueArray) {
        	
            System.out.println(o);  
            
        }
        
    }
}
