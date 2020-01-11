package elementsofprogramminginterviews.binarytrees;

public class ComputeTheSuccessor {
	public Node successor(Node node) {
		if (node.right != null) {
			return leftMostLeaf(node.right);
		}
		Node parent = node.parent;
		if (parent == null) {
			return null;
		} else if (parent.left.equals(node)) {
			return parent;
		} else {
			while (parent != null && parent.right.equals(node)) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	private Node leftMostLeaf(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public class Node {
		public Node parent;
		public Node left;
		public Node right;
	}
}
