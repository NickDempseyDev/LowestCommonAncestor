import java.util.ArrayList;
import java.util.List;

// code reused (and adjusted) from 2nd year Algorithms and Data structures assignment


public class BinaryTree<Key extends Comparable<Key>, Value> {
	public Node root;
	public ArrayList<Integer> path1;
	public ArrayList<Integer> path2;
	
	BinaryTree() {
		path1 = new ArrayList<Integer>();
		path2 = new ArrayList<Integer>();
	}
	/**
	 * Private node class.
	 */
	private class Node {
		public Key key;           // sorted by key
		public Value val;         // associated data
		public Node left, right;  // left and right subtrees
		public int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	

	public void findPaths(Key target1, Key target2)
	{
		path1.clear();
		path2.clear();
		if (!findPath(root, target1, path1)) {
			path1 = null;
		}
		if (!findPath(root, target2, path2)) {
			path2 = null;
		}
	}
	
	private boolean findPath(Node node, Key target, List<Integer> path)
	{
		// check if the root is a valid node
		if(node == null)
		{
			return false;
		}
		
		path.add((Integer) node.key);
		
		if(node.key.compareTo(target) == 0)
		{
			return true;
		}
		
		if (node.left != null && findPath(node.left, target, path)) {
            return true;
        }
 
        if (node.right != null && findPath(node.right, target, path)) {
            return true;
        }
        
        path.remove(path.size()-1);
		
		return false;
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null || key == null) 
		{ 
			if(key == null)
			{
				return;
			}
			delete(key); 
			return; 
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) 
	{
		if (x == null) 
		{
			return new Node(key, val, 1);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) 
		{ 
			x.left  = put(x.left,  key, val);
		}
		else if (cmp > 0) 
		{
			x.right = put(x.right, key, val);
		}
		else 
		{
			x.val   = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 * 										(((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */ 
	public String printKeysInOrder() {
		if (isEmpty()) return "()";
		return "(" + printKeysInOrder(root) + ")";
	}

	private String printKeysInOrder(Node node)
	{
		if(node == null)
		{
			return "";
		}
		String string = "(" + printKeysInOrder(node.left) + ")";
		string += node.key; 
		string += "(" + printKeysInOrder(node.right) + ")";
		return string;

	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public void prettyPrintKeys() {

		System.out.println(prettyPrint(root, ""));
	}

	private String prettyPrint(Node node, String prefix)
	{
		if(node != null)
		{
			String s = prefix + "-" + node.key + "\n";
			s += prettyPrint(node.left, prefix + " |");
			s += prettyPrint(node.right, prefix + "  ");
			return s;
		}
		return prefix + "-null\n";
	}



	/**
	 * Deteles a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	private void delete(Key key)
	{ 
		root = delete(root, key); 
	}

	private Node delete(Node node, Key key) {
		if (node == null) return null;

		int cmp = key.compareTo(node.key);

		if (cmp < 0) 
		{
			node.left = delete(node.left, key);
		}
		else if (cmp > 0) 
		{
			node.right = delete(node.right, key);
		}
		else 
		{
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;

			// replace with predecessor
			Node temp = node;
			node = max(temp.left);
			node.left = deleteMax(temp.left);
			node.right = temp.right;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	} 

	/* Private method only used in delete in order to delete a node
	 * parameters: the node to be deleted
	 * returns: the deleted node
	 * 
	 */
	private Node deleteMax(Node node)
	{
		if (node.right == null) 
		{
			return node.left;
		}
		node.right = deleteMax(node.right);
		node.N = 1 + size(node.left) + size(node.right);
		return node;
	}
	/* Private method only used in deleteMax in order get the max node
	 * parameters: the node to traverse from
	 * returns: the max node
	 * 
	 */
	private Node max(Node node)
	{
		if(node.right != null)
		{
			return max(node.right);
		}
		else
		{
			return node;
		}
	}

}
