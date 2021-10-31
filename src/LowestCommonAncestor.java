import java.util.ArrayList;
import java.util.HashSet;

public class LowestCommonAncestor {
	
DAG graph;
	
	public LowestCommonAncestor(DAG graph) {
		this.graph = graph;
		pathU = new ArrayList<ArrayList<Integer>>();
		pathV = new ArrayList<ArrayList<Integer>>();
	}
	
	
	 ArrayList<ArrayList<Integer>> pathU;
	 ArrayList<ArrayList<Integer>> pathV;
	 DAG g;
	 
	 @SuppressWarnings("rawtypes")
	void getPaths(Integer u, Integer v) {
		 
		 pathU.clear();
		 pathV.clear();
		 ArrayList tempU = new ArrayList<Integer>();
		 ArrayList tempV = new ArrayList<Integer>();
		 findPath(0, u, tempU, pathU);
		
		 findPath(0, v, tempV, pathV);
		
		 
	 }
	 
	
	private void findPath(Integer node, Integer target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> listOfPaths)
	{	
		path.add(node);
		
		if(node == target)
		{
			listOfPaths.add(new ArrayList<Integer>(path));
			path.remove(path.size() - 1);
			return;
		}
		
		ArrayList<DAG.AdjListNode> temp = graph.adj.get(node);

		for (DAG.AdjListNode tempNode : temp) {
			findPath(tempNode.getV(), target, path, listOfPaths);
		}
		
		path.remove(path.size()-1);
     
	}

	 public ArrayList<Integer> lowestCommonAncestor(int u, int v) {
		
		 getPaths(u, v);
		 
		 HashSet<Integer> setU = new HashSet<Integer>();
		 HashSet<Integer> setV = new HashSet<Integer>();
		 
		 for (ArrayList<Integer> list : pathU) {
			 setU.addAll(list);
		 }
		 
		 for (ArrayList<Integer> list : pathV) {
			 setV.addAll(list);
		 }
		 
		 HashSet<Integer> inter = new HashSet<Integer>(setU);
		 ArrayList<Integer> lca = new ArrayList<Integer>();
		 
		 inter.retainAll(setV);
		 
		 for (Integer i : inter) {
			 boolean bool = true;
			 for (DAG.AdjListNode j : graph.adj.get(i)) {
				 if (inter.contains(j.v)) {
					 bool = false;
				 }
			 }
			 
			 if (bool) {
				 lca.add(i);
			 }
		 }
		
		 return lca;
	 }
}
