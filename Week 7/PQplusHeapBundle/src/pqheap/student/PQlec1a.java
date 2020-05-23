package pqheap.student;

import java.util.PriorityQueue;
import java.util.Comparator;

class RevComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer x, Integer y)
    {
    	return y - x;
    }

}

public class PQlec1a {
	public static void main(String[] args) {
		Comparator<Integer> comp = new RevComparator();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, comp);
		pq.add(5);
		pq.add(9);
		pq.add(3);
		System.out.println(pq.peek());
		System.out.println(pq.size());
		pq.poll();
		System.out.println(pq.peek());   
	}
}
