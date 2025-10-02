import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Trie
{
	private TrieNode root;
	Trie(){
		this.root = new TrieNode();
	}

	public void insert(String s)
	{
		TrieNode curr = root;
		for(int x = 0;x < s.length();x++){
			char ch = s.charAt(x);
			TrieNode node = curr.getMap().getOrDefault(ch,null);
			if(node == null){
				node = new TrieNode();
				node.getMap().put(ch,node);
			}
			curr = node;
		}
		curr.setEndOfWord(true);
	}

	public boolean searchWord(String s)
	{
		TrieNode curr = root;
		for(int x = 0;x < s.length();x++){
			char ch = s.charAt(x);
			TrieNode node = curr.getMap().getOrDefault(ch,null);
			if(node == null)
				return false;
			curr = node;
		}
		return true;
	}

	public TrieNode getRoot()
	{
		return root;
	}

	public class TrieNode
	{
		private HashMap<Character,TrieNode> map;
		private boolean endOfWord;
		private ArrayList<Integer> list;

		TrieNode(){
			this.map = new HashMap<>();
			this.endOfWord = false;
			list = new ArrayList<>();
		}

		public HashMap<Character,TrieNode> getMap()
		{
			return map;
		}

		public void setMap(HashMap<Character,TrieNode> newMap)
		{
			this.map = newMap;
		}

		public boolean getEndOfWord()
		{
			return endOfWord;
		}

		public void setEndOfWord(boolean b)
		{
			this.endOfWord = b;
		}
		public ArrayList<Integer> getList()
		{
			return this.list;
		}
		public void setList(ArrayList<Integer> l)
		{
			this.list = l;
		}
	}
}

