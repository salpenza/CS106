package spellchecker;

import static sbcc.Core.*;

import java.util.*;

public class BasicDictionary implements Dictionary {
	private BinaryTreeNode root;
	private int count;


	@Override
	public void importFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		var list = readFileAsLines(filename);
		Collections.shuffle(list);
		for (String p : list) {
			add(p);
		}
	}


	@Override
	public void load(String filename) throws Exception {
		// TODO Auto-generated method stub
		var list = readFileAsLines(filename);
		for (String p : list) {
			add(p);
		}
	}


	@Override
	public void save(String filename) throws Exception {
		// TODO Auto-generated method stub
		if (root == null) {
			return;
		}
		var list = new ArrayList<String>();
		var stack = new Stack<BinaryTreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			BinaryTreeNode node = stack.pop();
			list.add(node.value);

			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		writeFileAsLines(filename, list);
	}


	@Override
	public String[] find(String word) {
		// TODO Auto-generated method stub
		String string[] = { "", "" };
		BinaryTreeNode current = this.getRoot();
		while (true) {
			int compare = current.value.compareToIgnoreCase(word);
			if (compare < 0) {
				string[0] = current.value;
				if (current.right == null) {
					return string;
				} else {

					current = current.right;
				}
			} else if (compare > 0) {
				string[1] = current.value;
				if (current.left == null) {
					return string;
				} else {
					current = current.left;
				}
			} else {
				return null;
			}

		}

	}


	@Override
	public void add(String word) {
		// TODO Auto-generated method stub
		if (root == null) {
			root = new BinaryTreeNode(word.toLowerCase().trim());
		} else {
			BinaryTreeNode node = root;
			while (node != null) {
				int compare = word.toLowerCase().compareToIgnoreCase(node.value);
				if (compare == 0) {
					return;
				} else if (compare < 0) {
					if (node.left != null) {
						node = node.left;
					} else {
						node.left = new BinaryTreeNode(word.toLowerCase().trim());
						return;
					}
				} else if (compare > 0)
					if (node.right != null) {
						node = node.right;
					} else {
						node.right = new BinaryTreeNode(word.toLowerCase().trim());
						return;
					}
			}
		}
	}


	@Override
	public BinaryTreeNode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
