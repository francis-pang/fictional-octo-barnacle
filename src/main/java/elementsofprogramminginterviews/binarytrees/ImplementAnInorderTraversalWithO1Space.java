package elementsofprogramminginterviews.binarytrees;

public class ImplementAnInorderTraversalWithO1Space {
	public void inOrder(Node root) {
		if (root == null) {
			return;
		}
		Node next = leftMostLeaf(root);
		while (next != null) {
			System.out.println(next.val);
			next = findSuccessor(next);
		}
	}

	private Node leftMostLeaf(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	private Node findSuccessor(Node node) {
		if (node.right != null) {
			return leftMostLeaf(node.right);
		}
		Node parent = node.parent;
		while (parent != null && parent.right.equals(node)) {
			node = parent;
			parent = node.parent;
		}
		return parent;
	}

	public class Node {
		public int val;
		public Node parent;
		public Node left;
		public Node right;
	}
}
