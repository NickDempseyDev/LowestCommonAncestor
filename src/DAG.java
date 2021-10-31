
import java.util.ArrayList;

// DAG code taken from GeeksForGeeks https://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
public class DAG {
	  // Graph is represented using adjacency list. Every
	  // node of adjacency list contains vertex number of
	  // the vertex to which edge connects. It also
	  // contains weight of the edge
		  int V; // No. of vertices'
	 
		  // Pointer to an array containing adjacency lists
		  ArrayList<ArrayList<AdjListNode>> adj;
	 
		  DAG(int V) // Constructor
		  {
			  this.V = V;
			  adj = new ArrayList<ArrayList<AdjListNode>>(V);
	 
			  for(int i = 0; i < V; i++){
				  adj.add(new ArrayList<AdjListNode>());
			  }
		  }
	 
		  void addEdge(int u, int v)
		  {
			  AdjListNode node = new AdjListNode(v);
			  adj.get(u).add(node); // Add v to u's list
		  }
		  
		  int getOutDegree(int u) {
			  return adj.get(u).size();
		  }
		  
		  static class AdjListNode {
			  int v;
			  
			  AdjListNode(int _v)
			  {
				  v = _v;
			  }
			  int getV() { return v; }
		  }
}


