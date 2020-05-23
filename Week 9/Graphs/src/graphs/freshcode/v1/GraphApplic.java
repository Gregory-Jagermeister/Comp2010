package graphs.freshcode.v1;

import java.util.*;

public class GraphApplic extends Graph {

	public void depthFirstTraversalRec1(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		// (for just the connected component containing v)

		// Recursive version of DFT
		System.out.print(" " + v);
		getVertex(v).setMarked(); // get vertex object with id v,
		// indicate visited by setting marked
		VertexIDList adjList = getVertex(v).getAdjs();
		// get adjacency list representing neighbours
		Iterator<Integer> vIt = adjList.iterator();
		while (vIt.hasNext()) { // iterate over neighbours
			Integer nextVertex = vIt.next();
			if (!getVertex(nextVertex).isMarked()) // if neighbour hasn't been visited
				depthFirstTraversalRec1(nextVertex); // visit it
		}
	}

	public List<Integer> depthFirstTraversalRec2(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Returns a list containing a depth-first traversal of a graph
		// (for just the connected component containing v)

		// Recursive version of DFT
		List<Integer> resList = new Vector<Integer>(); // list to hold DFT

		getVertex(v).setMarked(); // get vertex object with id v,
		// indicate visited by setting marked
		VertexIDList adjList = getVertex(v).getAdjs();
		// get adjacency list representing neighbours
		Iterator<Integer> vIt = adjList.iterator();
		while (vIt.hasNext()) { // iterate over neighbours
			Integer nextVertex = vIt.next();
			if (!getVertex(nextVertex).isMarked()) { // if neighbour hasn't been visited
				List<Integer> tmpResList = depthFirstTraversalRec2(nextVertex);
				// get traversal from recursive call
				resList.addAll(tmpResList); // combine lists from recursive calls
			}
		}
		resList.add(0, v); // put current vertex at start of list
		return resList;
	}

	public Integer depthFirstTraversalLastVertex(Integer v) {

		int vert = v;
		getVertex(v).setMarked();

		VertexIDList adjVerts = getVertex(v).getAdjs();

		Iterator<Integer> vertIt = adjVerts.iterator();

		while (vertIt.hasNext()) {
			Integer nextVert = vertIt.next();
			if (!getVertex(nextVert).isMarked()) {
				vert = depthFirstTraversalLastVertex(nextVert);
			}
		}
		return vert;

	}

	public void breadthFirstTraversalSingleComponent(Integer v) {
		Queue<Integer> q = new LinkedList<Integer>();
		VertexIDList adjList;
		q.add(v);
		getVertex(v).setMarked();
		System.out.print(" " + v);
		while (!q.isEmpty()) {
			v = q.poll();
			adjList = getVertex(v).getAdjs();
			// the list of nodes adjacent to node at the front of the queue
			Iterator<Integer> vIt = adjList.iterator();
			while (vIt.hasNext()) {
				Integer u = vIt.next();
				if (!getVertex(u).isMarked()) {
					q.add(u);
					getVertex(u).setMarked();
					System.out.print(" " + u);
				}
			}
		}
	}

	public Integer breadthFirstTraversalLastVertex(Integer v)
	// PRE: Graph is connected, v is the id of a vertex in the graph
	// POST: returns the last vertex encountered during a
	// breadth-first traversal
	{
		Queue<Integer> q = new LinkedList<Integer>();
		int vert = v;
		VertexIDList adjList;
		q.add(v);
		getVertex(v).setMarked();
		while (!q.isEmpty()) {
			v = q.poll();
			adjList = getVertex(v).getAdjs();
			// the list of nodes adjacent to node at the front of the queue
			Iterator<Integer> vIt = adjList.iterator();
			while (vIt.hasNext()) {
				Integer u = vIt.next();
				if (!getVertex(u).isMarked()) {
					q.add(u);
					vert = u;
					getVertex(u).setMarked();

				}
			}
		}

		return vert;
	}

	public Integer breadthFirstTraversalSecondLastVertex(Integer v)
	// PRE: Graph is connected, v is the id of a vertex in the graph
	// POST: returns the second-last vertex encountered during a
	// breadth-first traversal
	{
		Queue<Integer> q = new LinkedList<Integer>();
		int vert = v;
		int lastVert = v;
		VertexIDList adjList;
		q.add(v);
		getVertex(v).setMarked();
		while (!q.isEmpty()) {
			v = q.poll();
			adjList = getVertex(v).getAdjs();
			// the list of nodes adjacent to node at the front of the queue
			Iterator<Integer> vIt = adjList.iterator();
			while (vIt.hasNext()) {
				Integer u = vIt.next();
				if (!getVertex(u).isMarked()) {

					q.add(u);

					if (!vIt.hasNext()) {
						vert = u;
					} else {
						vert = lastVert;
					}
					lastVert = vert;
					getVertex(u).setMarked();

				}
			}

		}
		return vert;
	}

	public void depthFirstTraversalIter1(Integer v) {
		// PRE: v is the id of a vertex in the graph
		// POST: Prints out a depth-first traversal of a graph
		// (for just the connected component containing v)

		// Iterative version of DFT
		Stack<Integer> s = new Stack<Integer>();

		System.out.print(" " + v);
		s.push(v);
		getVertex(v).setMarked();

		while (!s.isEmpty()) { // while not all vertices visited
			v = s.peek();
			VertexIDList adjList = getVertex(v).getAdjs();
			Iterator<Integer> vIt = adjList.iterator();
			// get iterator over adjacency list representing neighbours
			while (vIt.hasNext() && getVertex(v).isMarked())
				v = vIt.next(); // skip over visited neighbours

			if (getVertex(v).isMarked()) { // only occurs if all neighbours visited
				s.pop(); // remove from stack
			} else { // v is an unvisited neighbour
				s.push(v); // add to stack
				getVertex(v).setMarked();
				System.out.print(" " + v);
			}
		}
	}

	public static void main(String[] args) {
		GraphApplic g = new GraphApplic();
		List<Integer> l;

		g.setDefault();
		System.out.println("DFT #1");
		g.depthFirstTraversalRec1(g.getFirstVertexID());
		g.setUnmarked();
		System.out.println();

		System.out.println("DFT #2");
		l = g.depthFirstTraversalRec2(g.getFirstVertexID());
		g.setUnmarked();
		Iterator<Integer> lIt = l.iterator();
		while (lIt.hasNext())
			System.out.print(" " + lIt.next());
		System.out.println();

		System.out.println("DFT #3");
		g.depthFirstTraversalIter1(g.getFirstVertexID());
		g.setUnmarked();
		System.out.println();

		System.out.println("DFT #4");
		System.out.println(g.depthFirstTraversalLastVertex(g.getFirstVertexID()));
		g.setUnmarked();
		System.out.println();

		System.out.println("BFT #1");
		g.breadthFirstTraversalSingleComponent(g.getFirstVertexID());
		g.setUnmarked();
		System.out.println();

		System.out.println("BFT #2");
		System.out.println(g.breadthFirstTraversalLastVertex(g.getFirstVertexID()));
		g.setUnmarked();
		System.out.println();

		System.out.println("BFT #3");
		System.out.println(g.breadthFirstTraversalSecondLastVertex(g.getFirstVertexID()));
		g.setUnmarked();
		System.out.println();

	}
}
