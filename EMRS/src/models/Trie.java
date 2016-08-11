package models;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		
		String[] words = word.split(":");
		TrieNode p1 = root;
		TrieNode p2 = root;
		
		for (int i = 0; i < words[0].length(); i++) {
			char c = words[0].charAt(i);
			int index = c - 'a';
			if (p1.array[index] == null) {
				TrieNode tmp = new TrieNode();
				p1.array[index] = tmp;
				p1 = tmp;
			} else {
				p1 = p1.array[index];
			}
		}
		p1.otherName = words[1];
		p1.isLeaf = true;
		
		for (int n = 0; n < words[1].length(); n++) {
			char c = words[1].charAt(n);
			int index = c - 'a';
			if (p2.array[index] == null) {
				TrieNode tmp = new TrieNode();
				p2.array[index] = tmp;
				p2 = tmp;
			} else {
				p2 = p2.array[index];
			}
		}
		p2.otherName = words[0];
		p2.isLeaf = true;
	}
	
	// Returns true if word is found
	public boolean search(String word) {
		TrieNode p = searchNode(word);
		if (p == null) {
			return false;
		} else {
			if (p.isLeaf) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean startsWith(String prefix) {
		TrieNode p = searchNode(prefix);
		if (p == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public TrieNode searchNode(String s) {
		TrieNode p = root;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (p.array[index] != null) {
				p = p.array[index];
			} else {
				return null;
			}			
		}
		
		if (p == root) {
			return null;
		}
		
		return p;
	}

}
