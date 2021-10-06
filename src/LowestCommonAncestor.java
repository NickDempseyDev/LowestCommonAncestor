import java.util.ArrayList;

public class LowestCommonAncestor {
	
	BinaryTree<Integer, Integer> bst;
	
	public LowestCommonAncestor(int[] nums) {
		bst = new BinaryTree<Integer, Integer>();
		buildTree(nums, bst);
	}
	
	public LowestCommonAncestor(String fileName) {
		bst = new BinaryTree<Integer, Integer>();
		buildTree(fileName, bst);
	}
	
	
	public Integer findLCA(Integer no1, Integer no2) {
		
		// make sure the inputs are not null
		if (no1 == null || no2 == null) {
			bst.path1 = null;
			bst.path2 = null;
			return null;
		}
		
		bst.findPaths(no1, no2);
		// return null if it can't find a path 
		if (bst.path1 == null || bst.path2 == null) return null;
		
		int i;
	    for (i = 0; i < bst.path1.size() && i < bst.path2.size() ; i++)
	    {
	    	if (bst.path1.get(i) != bst.path2.get(i))
	    	{
	    		break;
	    	}
	    }
	    return bst.path1.get(i - 1);
	}
	
	
	public void buildTree(String fileName, BinaryTree<Integer, Integer> bst) {
		FileLoader loader = new FileLoader(fileName);
		ArrayList<String> inputs = loader.readFile();
		for(int i = 0; i < inputs.size(); i++)
		{
			bst.put(Integer.parseInt(inputs.get(i)), Integer.parseInt(inputs.get(i)));
		}
	}
	
	public static void buildTree(int[] nums, BinaryTree<Integer, Integer> bst) {
		for(int i = 0; i < nums.length; i++)
		{
			bst.put(nums[i], nums[i]);
		}
	}

	public static void main(String[] args) {
	
		LowestCommonAncestor lca = new LowestCommonAncestor("D:\\dev\\College\\Year 3\\Java\\LowestCommonAncestor\\src\\inputs.txt");
		lca.findLCA(1,4);
	}

}
