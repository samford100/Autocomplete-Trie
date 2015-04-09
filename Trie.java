import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie {
	Node head;
	final static int ALPHA = 50; //spaces and dashes

	//use this tree to look up information on various data structures from wikipedia and give the user suggestions with each letter typed
	//http://en.wikipedia.org/wiki/List_of_data_structures
	//maybe add weight to the more popular ones

	private class Node {
		Node[] children;
		int count;
		//weight????
		char letter;
		public Node(char letter) {
			children = new Node[ALPHA];
			this.letter = letter;
		}

		/**
		 *
		 * @param n
		 * @return if the letter is contained in the children of the node
		 */
		public boolean containsLetter(char c) {
			for (Node n : this.children) {
				if (n != null && n.letter == c) {
					return true;
				}
			}
			return false;
		}

		public Node findNode(char c) {
			for (Node n : this.children) {
				if (n != null && n.letter == c) {
					return n;
				}
			}
			return null;
		}

		public Node findNextNode(char c) {
			for (Node n : this.children) {
				if (n != null && n.letter == c) {
					return n;
				}
			}
			//if letter is not found...
			Node a = add(c); //adds the letter to the current node
			return a;
		}

		public Node add(char l) {
			children[count] = new Node(l);
			count++;
			return children[count - 1]; //returns the added node
		}
	}

	public Trie() {
		head = new Node(' ');
	}

	public void addWord(String word) {
		//System.out.println(word);
		addRec(head, word, 0);
	}

	private void addRec(Node node, String word, int pos) {
		//System.out.println(word[pos]);
		if (pos == word.length() - 1) { //change. Too tired to think about it
			//end?
		} else {
			pos++;
			Node n = node.findNextNode(word.charAt(pos)); //finds the node based on the node given, or creates a new node
			//either way, the node I want is found
			addRec(n, word, pos); //cool beans
		}

	}

//	public boolean contains(String w) {
//		char[] word = formatWord(w);
//		Node n = head;
//		for (int i = 0; i < word.length; i++) {
//
//
//		}
//
//	}


	public void searchRec(Node node, String word , int i) {
		node = node.findNode(word.charAt(i));
	}

	private void search(String word) {
		searchRec(head, word, 0);
	}

	public boolean containsWord(String word) {
		int i = 0;
		Node cur = head;
		while (i < word.length()) {
			Node n = cur.findNode(word.charAt(i));
			if (n == null) {
				return false;
			}
			cur = n;
			System.out.println(cur.letter);
		}
		return true;
	}

	private void containsWordRec(Node node, String word, int pos) {
		if (pos == word.length()) {
			//return
		}
	}

	public static void main(String[] args) {
		Trie t = new Trie();

		Scanner fScan;
		try {
			fScan = new Scanner(new File("/Users/samford/Desktop/Algorithms and Data Structures.txt"));
			while (fScan.hasNextLine()) {
				//System.out.println(fScan.nextLine());
				t.addWord(fScan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//System.out.println(t.head.findNode('a').findNode('v').letter);

		System.out.println(t.containsWord("a"));

		//System.out.println(t.head.letter);

		//System.out.println(t.head.containsLetter('o'));

//		System.out.println(formatWord("dOg"));
//		System.out.println(t.head.letter);
//		System.out.println(t.head.children[0].letter);
//		System.out.println(t.head.children[1].letter);
//		for (Node n : t.head.children) {
//			if (n != null) {
//				System.out.println(n.letter);
//			}
//		}

//		System.out.println(t.head.children[0].children[0].letter);
//		System.out.println(t.head.children[0].children[0].children[0].letter);
//		System.out.println(t.head.children[0].children[0].children[1].letter);
	}

}
