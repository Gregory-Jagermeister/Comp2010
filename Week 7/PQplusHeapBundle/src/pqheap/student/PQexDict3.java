package pqheap.student;

import java.util.PriorityQueue;
import java.util.ArrayList;

class Dict3 {
	private ArrayList<PriorityQueue<String>> dict;
	public Dict3(int maxSize) {
		dict = new ArrayList<PriorityQueue<String>>(maxSize);
		for (int i = 0; i < maxSize; i++) {
			PriorityQueue<String> pq = new PriorityQueue<String>();
			dict.add(pq);
		}
	}
	public void add(String s) {
		int indx = (int) s.charAt(0) - (int) 'a';
		dict.get(indx).add(s);
	}
}

public class PQexDict3 {
	
	public static void main(String[] args) {
		String words[] = {"in", "the", "second", "century", "of", "the",
                  "christian",  "era", "the", "empire", "of", "rome",
                  "comprehended", "the", "fairest", "part", "of",
                  "the", "earth"};

		Dict3 myDict = new Dict3(26);
		
		for (int i = 0; i < words.length; i++)
			myDict.add(words[i]);

	}
}
