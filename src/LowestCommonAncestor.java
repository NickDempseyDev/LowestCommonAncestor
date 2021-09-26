import java.util.ArrayList;

public class LowestCommonAncestor {
	
	
	public static Integer findLCA(BinaryTree<Integer, Integer> bst, Integer no1, Integer no2) {
	
		bst.findPaths(no1, no2);
		
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
	
	
	public static void buildTree(String fileName, BinaryTree<Integer, Integer> bst) {
		FileLoader loader = new FileLoader(fileName);
		ArrayList<String> inputs = loader.readFile();
		for(int i = 0; i < inputs.size(); i++)
		{
			bst.put(Integer.parseInt(inputs.get(i)), Integer.parseInt(inputs.get(i)));
		}
	}

	public static void main(String[] args) {
		BinaryTree<Integer, Integer> bst = new BinaryTree<Integer, Integer>();
		buildTree("D:\\dev\\College\\Year 3\\Java\\LowestCommonAncestor\\src\\inputs.txt", bst);
		System.out.println(findLCA(bst, 27, 17));
		//bst.prettyPrintKeys();
	}

}
