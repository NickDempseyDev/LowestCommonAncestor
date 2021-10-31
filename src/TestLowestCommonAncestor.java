import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TestLowestCommonAncestor {

	@Test
	// test the constructor and check to see if the tree is generated as expected
	public void testConstructorDAG() {
		 DAG graph = new DAG(9);
		 assertEquals("checking size of graph", 9, graph.V);
		 assertEquals("checking size of graph", 9, graph.adj.size());
		 
		 graph = new DAG(0);
		 assertEquals("checking size of empty graph", 0, graph.V);
		 assertEquals("checking size of empty graph", 0, graph.adj.size());
	}
	
	@Test
	//test to make sure the graph adds the edges correctly
	public void testAddingEdges() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		graph.addEdge(1, 6);
		graph.addEdge(2, 4);
		graph.addEdge(2, 6);
		graph.addEdge(2, 3);
		graph.addEdge(3, 6);
		graph.addEdge(6, 5);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		assertEquals("checking if edge 0 -> 1 exists", Integer.valueOf(1), Integer.valueOf(graph.adj.get(0).get(0).v));
		assertEquals("checking if edge 1 -> 4 exists", Integer.valueOf(4), Integer.valueOf(graph.adj.get(1).get(0).v));
		assertEquals("checking if edge 2 -> 6 exists", Integer.valueOf(6), Integer.valueOf(graph.adj.get(2).get(1).v));
		assertEquals("checking if edge 7 -> 8 exists", Integer.valueOf(8), Integer.valueOf(graph.adj.get(7).get(0).v));
	}
	
	@Test
	public void testLCA() {
		DAG graph = new DAG(9);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		graph.addEdge(1, 6);
		graph.addEdge(2, 4);
		graph.addEdge(2, 6);
		graph.addEdge(2, 3);
		graph.addEdge(3, 6);
		graph.addEdge(6, 5);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		LowestCommonAncestor lca = new LowestCommonAncestor(graph);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		assertArrayEquals("checking the LCA of 4 and 7", list.toArray(), lca.lowestCommonAncestor(4, 7).toArray());
		
		list.clear();
		list.add(0);
		assertArrayEquals("checking the LCA of 1 and 3", list.toArray(), lca.lowestCommonAncestor(1, 3).toArray());
		
		list.clear();
		list.add(6);
		assertArrayEquals("checking the LCA of 5 and 8", list.toArray(), lca.lowestCommonAncestor(5, 8).toArray());
	}


}
