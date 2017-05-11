package data_structures;
import data_structures.BalancedSearchTree;

public class SearchTreeTest {
	
	
	public static void main(String args[]) {
	
	
	BalancedSearchTree<Integer> tree = new BalancedSearchTree<Integer>();
	
	
	if(tree.isEmpty())
		System.out.println("First one");
	tree.add(7);
	System.out.println(tree.height() + "");
	
	tree.add(8);
	tree.add(10);
	tree.add(5);
	System.out.println("Root Is");
	System.out.println(tree.getRootData());
	System.out.println(tree.height() + "");
	
	
	tree.add(10);
	tree.add(100);
	tree.add(2500);
	System.out.println("Root Is");
	System.out.println(tree.getRootData());
	tree.add(1);
	tree.add(12);
	tree.add(19);
	
	System.out.println("Root Is");
	System.out.println(tree.getRootData());
	System.out.println(tree.height() + "");
	tree.add(25);
	tree.add(30);
	tree.add(50);
	System.out.println(tree.getRootData());
//    tree.delete(10);
	System.out.println(tree.get(5));
    System.out.println(tree.get(7));
	System.out.println(tree.get(8));
	System.out.println(tree.get(10));
    System.out.println(tree.get(12));
	System.out.println(tree.get(19));
	System.out.println(tree.get(25));
	
	if(tree.contains(new Integer(5)))
		System.out.println("YES");
	if(tree.contains(new Integer(7)))
		System.out.println("AGAIN");
	if(!tree.contains(new Integer (1111)))
		System.out.println("Booyah");
	
	tree.delete(new Integer(1111));
	
	if(!tree.contains(1111))
		System.out.println("1111 deleted");		
	
	System.out.println(tree.get(25));
	tree.delete(new Integer(25));
	if(!tree.contains(25))
		System.out.println("25 deleted");
	
	
		
}
	
}

