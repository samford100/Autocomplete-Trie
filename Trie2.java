import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class Trie2 {
	Node root;
	List<String> possWords = new ArrayList<>();
	List<Node> possNodes = new ArrayList<>();
	Node holder;
//	String hString = "";
	StringBuilder hString = new StringBuilder();
	
	class Node {
		Map<Character, Node> map;
		boolean isComp;
		Node parent;
		String def; //the definition if it is a complete word
		public Node() {
			map = new HashMap<>();
			isComp = false;
			parent = null;
		}
		public Node(Character c) {
			map = new HashMap<>();
			isComp = false;
		}
		
		public void setDef(String word) {
			this.def = "Def : " + word;
		}
		
	}
	
	public Trie2() {
		root = new Node();
		holder = root;
	}
	
	public void addWord(String word) {
		//get the map of the root
		int i = 0;
		Node n = root;
		while (i < word.length()) {
			Character c = Character.toLowerCase(word.charAt(i));
			if (!n.map.containsKey(c)) {
				Node node = new Node(c);
				n.map.put(c, node);
			}
			n.map.get(c).parent = n;
			n = n.map.get(c);
			
			i++;
		}
		n.isComp = true;
		
		//
		n.setDef(word);
		//
		
		
	}
	

	
	public void removeWord(String word) {
		Node n = root;
		for (int i = 0; i < word.length(); i++) {
			n = n.map.get(word.charAt(i));
		}
		n.isComp = false;
	}
	
	public Node findNode(String word) {
		Node n = root;
		for (int i = 0; i < word.length(); i++) {
			if (n == null) {
				n = null;
			} else {
				n = n.map.get(word.charAt(i));
			}
		}
		return n;
	}
	
	public static void main(String...strings) {
		Trie2 t = new Trie2();
		t.importWords("/Users/samford/Desktop/words.txt");
		//t.importWords("/Users/samford/Desktop/Algorithms and Data Structures.txt");
		
//		t.addWords("The quick brown fox jumps over the lazy dog");
//		t.addWord("rat");
//		t.addWord("rod");
//		t.addWord("rope");
//		t.addWord("ram");
//		t.addWord("pram");
		
		
		//t.continualComp('b');
		
		
		//t.autoComp("abrac");
		
		System.out.println(t.possWords.toString());

//		System.out.println(t.possWords);
	}
	
	/**
	 * 
	 * @param c
	 * allows us to check continuously
	 */
	public void continualComp(Character c, int textLen) {
		possWords.clear();
		possNodes.clear();
		if (holder.map.containsKey(c) && hString.length() == textLen) {
			holder = holder.map.get(c);
			hString.append(c);
			//hString = hString + c;
			autoCompRec(holder, hString.toString());
//			autoCompRec(holder, hString);
		}
	}
	
	/**
	 * only called with backspace
	 */
	public void backer(int textLen) {
		//System.out.println(hString.length() + " : textLen:" + textLen);
		if ((holder != root || holder != null) && hString.length() >= textLen) {
			holder = holder.parent;
			//hString = hString.substring(0, hString.length() - 1);
			hString.deleteCharAt(hString.length() - 1);
		}
	}
	
	public void autoComp(String cur) {
		Node beg = findNode(cur);
		if (beg != null) {
			autoCompRec(findNode(cur), cur);
		}
	}
	
	public void autoCompRec(Node node, String cur) {
		if (node.isComp) {
			possWords.add(cur);
			possNodes.add(node);
		}
		Set<Entry<Character, Node>> s = node.map.entrySet();
		Iterator<Entry<Character, Node>> iter = s.iterator();
		while (iter.hasNext()) {
			Entry<Character, Node> e = iter.next();
			autoCompRec(e.getValue(), cur + e.getKey());
		}
	}
	
	
//	public void autoComp(String cur) {
//		Node beg = findNode(cur);
//		if (beg != null) {
//			autoCompRec(beg);
//		}
//	}
//	
//	public void autoCompRec(Node n) {
//		if (n.isComp) {
//			possWords.add(n.curString);
//		}
//		Set<Entry<Character, Node>> s = n.map.entrySet();
//		//System.out.println(n.map.keySet());
//		Iterator<Entry<Character, Node>> iter = s.iterator();
//		while (iter.hasNext()) {
//			Entry<Character, Node> e = iter.next();
//			autoCompRec(e.getValue());
//		}
//	}
	
	public void addWords(String sentence) {
		Scanner senScan = new Scanner(sentence);
		senScan.useDelimiter(" ");
		while (senScan.hasNext()) {
			String a = senScan.next();
			System.out.println(a);
			addWord(a);
		}
		senScan.close();
	}
	
	public boolean containsWord(String word) {
		return findNode(word).isComp;
	}
	
	public void importWords(String filename) {
		try {
			Scanner fScan = new Scanner(new File(filename));
			while (fScan.hasNextLine()) {
				this.addWord(fScan.nextLine());
			}
			fScan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
