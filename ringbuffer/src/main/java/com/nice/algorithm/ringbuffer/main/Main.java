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
    	final CircleQueue<Integer> queue = new CircleQueue<Integer>(10);
    	
        for(int i = 0; i < 100; i++) {
        	
        	queue.add(i);
        	
        }
        
        final Object[] queueArray = queue.getQueue();
        
        System.out.println("按入队列的先后顺序打印出来：");
        
        for (final Object o : queueArray) {
        	
            System.out.println(o);  
            
        }
        
    }
}
