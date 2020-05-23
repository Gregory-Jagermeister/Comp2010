package pqheap.student;

import java.util.PriorityQueue;

public class PQlec1 {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(5);
		pq.add(9);
		pq.add(3);
		System.out.println(pq.peek());
		System.out.println(pq.size());
		pq.poll();
		System.out.println(pq.peek());
	}
}
