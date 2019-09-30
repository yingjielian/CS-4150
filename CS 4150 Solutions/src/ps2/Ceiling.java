package ps2;

import java.util.ArrayList;
import java.util.Scanner;

// Construct out tree
class Node {
	int v;
	Node right;
	Node left;

	public Node(int v) {
		this.v = v;
		left = null;
		right = null;
	}
}

// Construct our Binary Search Tree that we learned from lecture
class BinarySearchTree {
	Node root;

	BinarySearchTree(int[] v) {
		this.root = new Node(v[0]);
		for (int i = 1; i < v.length; i++) {
			addTree(v[i], this.root);
		}
	}

	// Add subtrees to the subnode
	Node addTree(int v, Node root) {
		if (root == null) 
		{
			root = new Node(v);
			return root;
		}

		if (v < root.v)
			root.left = addTree(v, root.left);
		if (v > root.v)
			root.right = addTree(v, root.right);
		
		return root;
	}
}

public class Ceiling {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);

		String firstLine = s.nextLine();
		String storeTree;
		String[] firstLineArray = firstLine.split(" ");
		String[] storeTress;
		int n = Integer.valueOf(firstLineArray[0]);
		int k = Integer.valueOf(firstLineArray[1]);
		int[] treeIntArray = new int[k];
		int result = 0;

		BinarySearchTree[] trees = new BinarySearchTree[n];

		for (int i = 0; i < n; i++) {
			storeTree = s.nextLine();
			storeTress = storeTree.split(" ");
			
			for (int j = 0; j < k; j++) {
				treeIntArray[j] = Integer.parseInt(storeTress[j]);
			}

			trees[i] = new BinarySearchTree(treeIntArray);
		}
		
		result = countTree(trees);
		
		System.out.println(result);
	}

	static boolean checkTreeShape(Node a, Node b) {
		if (a == null && b == null)
			return true;
		else if (!(a != null && b != null))
			return false;
		else
			return (checkTreeShape(a.left, b.left) && checkTreeShape(a.right, b.right));
	}
	
	static int countTree(BinarySearchTree[] tree) {

		ArrayList<BinarySearchTree> treeShape = new ArrayList<BinarySearchTree>();
		boolean flag = false;

		for (BinarySearchTree t : tree) {
			if (treeShape.size() == 0) {
				treeShape.add(t);
			} else {
				flag = true;
				for (BinarySearchTree tShape : treeShape) {
					if (checkTreeShape(t.root, tShape.root)) {
						flag = false;
					}
				}

				if (flag)
					treeShape.add(t);
			}
		}

		return treeShape.size();
	}
}