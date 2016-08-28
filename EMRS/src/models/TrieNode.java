package models;

public class TrieNode {
	TrieNode[] array;
	boolean isLeaf;
	String otherName;
	
	public TrieNode() {
		this.array = new TrieNode[26];
	}
}
