package pqheap.student;

import java.util.PriorityQueue;
import java.util.Comparator;

class ItemComparator implements Comparator<Item>
{
    @Override
    public int compare(Item x, Item y)
    {
    	return x.getKey() - y.getKey();
    }

}

class Item {
	private int key;
	private String name;
	public Item(int k, String s) {
		key = k;
		name = s;
	}
	int getKey() {
		return key;
	}
	String getName() {
		return name;
	}
}

public class PQlec2 {

	public static void main(String[] args) {
		Comparator<Item> comp = new ItemComparator();
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

	}
}
