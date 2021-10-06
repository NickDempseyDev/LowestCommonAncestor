import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestLowestCommonAncestor {

	@Test
	// test the constructor and check to see if the tree is generated as expected
	public void testConstructor1() {
		int arr[] = {10,5,20,3,7,15,25,1,4,6,8,12,17,22,27};
		LowestCommonAncestor lca = new LowestCommonAncestor(arr);
		assertEquals("","((((()1())3(()4()))5((()6())7(()8())))10(((()12())15(()17()))20((()22())25(()27()))))",lca.bst.printKeysInOrder());
	}
	
	@Test
	// test the constructor and check to see if the tree is generated as expected
	public void testConstructor2() {
		String file = "D:\\dev\\College\\Year 3\\Java\\LowestCommonAncestor\\src\\inputs.txt";
		LowestCommonAncestor lca = new LowestCommonAncestor(file);
		assertEquals("","((((()1())3(()4()))5((()6())7(()8())))10(((()12())15(()17()))20((()22())25(()27()))))",lca.bst.printKeysInOrder());
	}
	
	@Test
	public void testLCA() {
		int arr[] = {10,5,20,3,7,15,25,1,4,6,8,12,17,22,27};
		LowestCommonAncestor lca = new LowestCommonAncestor(arr);
		assertEquals("Find LCA of 6 and 8 (expect 7)",(Integer)7,lca.findLCA(6, 8));
		assertEquals("Find LCA of 27 and 1 (expect 10)",(Integer)10,lca.findLCA(27,1));
		assertEquals("Find LCA of 8 and 1 (expect 5)",(Integer)5,lca.findLCA(1, 8));
		assertEquals("Find LCA of 2121 and 8 (expect null)",null,lca.findLCA(2121, 8));
		assertEquals("Find LCA of null and null (expect null)",null,lca.findLCA(null, null));
	}

}
