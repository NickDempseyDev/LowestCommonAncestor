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
		findPath(root, target1, path1);
		findPath(root, target2, path2);
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
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null)
		{
			return null;
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0) 
		{
			return get(x.left, key);
		}
		else if (cmp > 0) 
		{
			return get(x.right, key);
		}
		else 
		{
			return x.val;
		}
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
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta(N) where N is the number of nodes (bst.N)
	 * 
	 * Explanation: The worst case for checking the height is when the tree has deteriorated into a linked list and is 
	 * N in height rather than a balanced lg(N)
	 *
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() {
		return height(root);
	}

	private int height(Node node)
	{
		// case 1 : node == NULL
		if(node == null) {
			return -1;
		}
		// case 2 : if the left and right subtrees are null
		else if (node.left == null && node.right == null)
		{
			return 0;
		}
		// case 3 : if just the left subtree is null
		else if(node.left == null) 
		{
			return 1 + height(node.right);
		}
		// case 4 : if just the right subtree is null
		else if(node.right == null)
		{
			return 1 + height(node.left);
		}
		// case 5 : node.left.N > node.right.N return 1 + height(node.left)
		else if(node.left.N > node.right.N) {
			return 1 + height(node.left);
		}
		// case 6 : node.right.N > node.left.N return 1 + height(node.right)
		else
		{
			return 1 + height(node.right);
		}
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N+1)/2 (where "/" here is integer division)
	 * 
	 * Asymptotic worst-case running time using Theta notation: Theta(h)
	 * 
	 * Explanation: The worst case for checking the height is when the tree has deteriorated into a linked list and is 
	 * N in height rather than a balanced lg(N)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() {
		if (isEmpty()) 
		{
			return null;
		}
		int n = size(root);
		return select((n-1)/2);
	}

	/* private method only used in the median() method 
	 * returns the nth key that is in the list.
	 * parameters: position of desired key
	 * returns: key
	 */

	private Key select(int position) {
		Node node = select(root, position);
		return node.key;
	}

	private Node select(Node x, int position) {
		int temp = size(x.left);
		if (temp > position) 
		{
			return select(x.left, position);
		}
		else if (temp < position) 
		{
			return select(x.right, position-temp-1);
		}
		else 
		{
			return x;
		}
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
	public void delete(Key key)
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
