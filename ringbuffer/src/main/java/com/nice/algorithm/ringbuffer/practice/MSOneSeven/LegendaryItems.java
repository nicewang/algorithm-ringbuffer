package com.nice.algorithm.ringbuffer.practice.MSOneSeven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Little Hi is playing a video game. Each time he accomplishes a quest in the game,
 * Little Hi has a chance to get a legendary item.
 * 
 * At the beginning the probability is P%. Each time Little Hi accomplishes a quest without getting a legendary item,
 * the probability will go up Q%. Since the probability is getting higher he will get a legendary item eventually.
 * 
 * After getting a legendary item the probability will be reset to
 * ⌊P/(2I)⌋% (⌊x⌋ represents the largest integer no more than x)
 * where I is the number of legendary items he already has. 
 * The probability will also go up Q% each time Little Hi accomplishes a quest until he gets another legendary item.
 * 
 * Now Little Hi wants to know the expected number of quests he has to accomplish to get N legendary items.
 * 
 * Assume P = 50, Q = 75 and N = 2, as the below figure shows the expected number of quests is
 * 2*50%*25% + 3*50%*75%*100% + 3*50%*100%*25% + 4*50%*100%*75%*100% = 3.25
 */
public class LegendaryItems {

	public static class route{
		int count;
		double percent;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public double getPercent() {
			return percent;
		}
		public void setPercent(double percent) {
			this.percent = percent;
		}
	}
	
	public static List<route> routes = new ArrayList<route>();
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
        	int P = in.nextInt();
        	int Q = in.nextInt();
        	int N = in.nextInt();
        	double result = 0;
        	print(P, Q, N, result);
        }
        in.close();
    }
	
	public static void print(int P, int Q, int N, double result) {
		double result1 = result;
		addRoute(P, P, Q, N, 0, 0, 1, routes);
		for(route r : routes) {
			int count = r.getCount();
			double percent = r.getPercent();
			result1 = result1 + count*percent;
		}
		routes.clear();
		System.out.println(result1);
	}
	
	public static void addRoute(int P, int P_now, int Q, int N, int count, int N_lasts, double percent, List<route> r) {
		int count1 = count + 1;
		double P_now_percent = (double) P_now/100;
		double NP_now_percent = 1 - P_now_percent;
		if(N_lasts < N) {
			if(P_now >= 100) {
				//there exists left only.
				addRoute(P/2, P/2, Q, N, count1, N_lasts + 1, percent*1, r);
			} else {
				//left
				addRoute(P/2, P/2, Q, N, count1, N_lasts + 1, percent*P_now_percent, r);
				//right
				addRoute(P, P + Q, Q, N, count1, N_lasts, percent*NP_now_percent, r);
			}
		} else {
			route e = new route();
			e.setCount(count);
			e.setPercent(percent);
			r.add(e);
		}
	}
	
}
