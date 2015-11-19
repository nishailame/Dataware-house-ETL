package spelling;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author Nisha Ilame
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	Queue<String> list;
	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should ignore the word's case. That is, you should convert the
	 * string to all lower case as you insert it.
	 */
	public boolean addWord(String word) {
		// TODO: Implement this method.
		String text = word.toLowerCase();
		char[] array = text.toCharArray();
		// int count = 0;
		TrieNode rootTemp = root;
		for (int i = 0; i < array.length; i++) {

			if (!text.equals(rootTemp.getText())) {
				TrieNode temp = rootTemp.getChild(array[i]);
				if (temp == null) {
					rootTemp = rootTemp.insert(array[i]);
					// count++;
					if (i == array.length - 1) {
						rootTemp.setEndsWord(true);
						size++;
					}
				} else {

					rootTemp = temp;
					if (i == array.length - 1) {
						if (!rootTemp.endsWord()) {
							rootTemp.setEndsWord(true);
							size++;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		// TODO: Implement this method

		System.out.println(size);
		return size;

	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		// TODO: Implement this method
		if (s == "") {
			return false;
		}
		String text = s.toLowerCase();
		char[] ch = text.toCharArray();
		TrieNode curr = root.getChild(ch[0]);
		// int i = 0;
		if (curr == null) {
			return false;
		}
		for (int i = 0; i < ch.length; i++) {
			// System.out.println(curr.getText());
			char d = ch[i];
			if (curr.getText().indexOf(d) >= 0) {
				if (i + 1 == ch.length) {
					break;
				}
				if (curr.getChild(ch[i + 1]) != null) {
					curr = curr.getChild(ch[i + 1]);

				} else {
					// System.out.println("first");
					return false;
				}

			} else {
				// System.out.println("second");
				return false;
			}
		}
		if (curr.endsWord()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// TODO: Implement this method
		// This method should implement the following algorithm:
		// 1. Find the stem in the trie. If the stem does not appear in the
		// trie, return an empty list
		// 2. Once the stem is found, perform a breadth first search to generate
		// completions
		// using the following algorithm:
		// Create a queue (LinkedList) and add the node that completes the stem
		// to the back
		// of the list.
		// Create a list of completions to return (initially empty)
		// While the queue is not empty and you don't have enough completions:
		// remove the first Node from the queue
		// If it is a word, add it to the completions list
		// Add all of its child nodes to the back of the queue
		// Return the list of completions
		list = new LinkedList<String>();
		LinkedList<String> completion_list = new LinkedList<String>();
		if (prefix == "") {
			return completion_list;
		}

		String word = prefix.toLowerCase();
		char[] ch = word.toCharArray();
		TrieNode curr = root.getChild(ch[0]);
		if (curr == null) {
			return completion_list;
		}
		for (int i = 0; i < ch.length; i++) {
			// System.out.println(curr.getText());
			// list.add(curr.getText());
			char d = ch[i];
			if (curr.getText().indexOf(d) >= 0) {
				if (i + 1 == ch.length) {
					break;
				}
				if (curr.getChild(ch[i + 1]) != null) {
					curr = curr.getChild(ch[i + 1]);
				}
			}
		}

		//System.out.println(curr.getText());
		
		addNode(curr);
		
		for (String element : list) {
			if (numCompletions > 0) {
				if (isWord(element)) {
					completion_list.add(element);
					numCompletions--;
				}
			}
		}

		
		Collections.sort(completion_list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() >= o2.length()) {
					return 1;
				} else {
					return -1;
				}
		}
		}

		);
		
		
        System.out.println(completion_list);
		
		return completion_list;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

	public void addNode(TrieNode curr) {
		if (curr == null)
			return;

		// System.out.println(curr.getText());
		list.add(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			// System.out.println(next.getText());
			addNode(next);
		}
	}

}