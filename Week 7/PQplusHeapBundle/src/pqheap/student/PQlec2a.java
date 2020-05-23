package pqheap.student;

import java.util.PriorityQueue;
import java.util.Comparator;

class ItemComparatorStr implements Comparator<Item>
{
    @Override
    public int compare(Item x, Item y)
    {
    	return x.getName().compareTo(y.getName());
    }

}

public class PQlec2a {
	public static void main(String[] args) {
		Comparator<Item> comp = new ItemComparatorStr();
		PriorityQueue<Item> pq = new PriorityQueue<Item>(10, comp);
		Item e = new Item(3, "Mark");
		pq.add(e);
		e = new Item(4, "Lulu");
		pq.add(e);
		e = new Item(1, "Tim");
		pq.add(e);
		while (pq.size() > 0)
        {
        	Item x = pq.remove();
            System.out.println(x.getKey() + " " + x.getName());
        }

		/*
		String x = "hello";
		String y = "hi";
		System.out.println(x.compareTo(y));
		Item e1 = new Item(3, "Mark");
		Item e2 = new Item(4, "Lulu");
		System.out.println(e1.getName().compareTo(e2.getName()));
		*/
	}
}
