import java.util.*;
import java.io.*;
import java.math.*;

class Trie<T> {

    private TrieNode<T> root;
    private boolean access;
    private String nullChar;


    public Trie() {
        super();
        this.root = new TrieNode<T>();
        this.nullChar = "&";
        access = false;
    }

    public Trie(boolean needBuffer) {
        super();
        this.root = new TrieNode<T>(needBuffer);
        this.nullChar = "&";
        access = false;
    }


    public Trie(TrieNode<T> root) {
        super();
        this.root = root;
        access = false;
    }

    public void unlock(String pass) {
        if(pass.equalsIgnoreCase("9825"))
            access = true;
        else
            System.out.println("Wrong Password!");;
    }
    public void lock() {
        access = false;
    }

    public void insert(String s)
    {
        TrieNode curr = root;
        for(int x = 0;x < s.length();x++) {
            char ch = s.charAt(x);
            TrieNode<T> node = (TrieNode<T>)curr.getMap().getOrDefault(ch,null);
            if(node == null) {
                node = new TrieNode<T>();
                curr.getMap().put(ch,node);
            }
            curr = node;
        }
        curr.setEndOfWord(true);

    }
    public boolean searchWord(String s)
    {
        TrieNode<T> curr = root;
        for(int x = 0;x < s.length();x++) {
            char ch = s.charAt(x);
            TrieNode<T> node = (TrieNode<T>)curr.getMap().get(ch);
            if(node == null)
                return false;

            curr = node;
        }
        return curr.getEndOfWord();
    }
    public int searchPrefix(String s)
    {
        TrieNode curr = root;
        for(int x = 0;x < s.length();x++) {
            char ch = s.charAt(x);
            TrieNode<T> node = (TrieNode<T>)curr.getMap().get(ch);
            if(node == null)
                return 0;

            curr = node;
        }
        if(curr.getEndOfWord() && curr.getMap().size() == 0)
            return 2;
        return 1;
    }
    public boolean deleteWord(String s)
    {
        return deleteWordUtil(root,s,0);
    }
    private boolean deleteWordUtil(TrieNode<T> curr,String s,int index)
    {
        if(index == s.length()) {
            if(!curr.getEndOfWord())
                return false;
            curr.setEndOfWord(false);
            return curr.getMap().size() == 0;
        }

        char ch = s.charAt(index);
        TrieNode<T> node = (TrieNode<T>)curr.getMap().get(ch);
        if(node == null)
            return false;

        Boolean deleteNodeCompletely = deleteWordUtil(node,s,index + 1);
        if(deleteNodeCompletely) {
            curr.getMap().remove(ch);
            return curr.getMap().size() == 0;
        }
        return false;
    }
    public boolean searchDeleteWord(String s)
    {
        if(!searchWord(s))
            return false;
        return deleteWord(s);
    }


    public int deletePrefix(String s)
    {
        TrieNode curr = root;
        for(int x = 0;x < s.length() - 1;x++) {
            char ch = s.charAt(x);
            TrieNode<T> node = (TrieNode<T>)curr.getMap().get(ch);
            if(node == null)
                return 0;
            curr = node;
        }
        char ch = s.charAt(s.length() - 1);
        if(curr.getMap().get(ch) == null)
            return 0;


        if(((HashMap<Character,TrieNode<T>>)curr.getMap()).get(ch).getEndOfWord() && ((HashMap<Character,TrieNode<T>>)curr.getMap()).get(ch).getMap().size() == 0) {
            curr.setEndOfWord(false);
            curr.getMap().put(ch,null);
            return 2;
        }
        curr.getMap().put(ch,null);

        return 1;
    }

    public int searchDeletePrefix(String s)
    {
        if(searchPrefix(s) == 0)
            return 0;
        return deletePrefix(s);
    }

    public void printAllWords()
    {
        System.out.print("Trie [");
        printAllWordsUtil(root,"");
        System.out.println("]");
    }

    private void printAllWordsUtil(TrieNode<T> curr,String res)
    {
        if(curr.getEndOfWord())
            System.out.print(res + " ");

        Set<Character> set = curr.getMap().keySet();
        for(char ch : set)
            printAllWordsUtil((TrieNode<T>) curr.getMap().get(ch),res + ch + "");
    }

    public TrieNode getRoot() {
        if(access)
            return root;
        else {
            System.out.println("Access Denied");
            return null;
        }

    }

    public void setRoot(TrieNode<T> root) {
        if(access)
            this.root = root;
        else
            System.out.println("Access Denied");

    }

    public boolean isAccess() {
        return access;
    }

    public String getNullChar() {
        return nullChar;
    }

    public void setNullChar(String nullChar) {
        this.nullChar = nullChar;
    }


    public class TrieNode<T>
    {
        private HashMap<Character,TrieNode<T>> map;
        private  TrieNode<T> buffer[],bigBuffer[];
        private boolean endOfWord,needBuffer;
        private T value;
        private ArrayList<T> list;

        public TrieNode() {
            this.map = new HashMap<>();
            this.endOfWord = false;
            list = new ArrayList<T>();

        }
        public TrieNode(boolean needBuffer) {
            this.map = new HashMap<>();
            this.endOfWord = false;
            list = new ArrayList<T>();

            if(needBuffer) {
                buffer = new TrieNode[26];
                bigBuffer = new TrieNode[256];
                initializeBuffer();
                this.needBuffer = needBuffer;
            }

        }

        private void initializeBuffer()
        {
            for(int x = 0;x < 26;x++)
                this.buffer[x] = null;
            for(int x = 0;x < 256;x++)
                this.bigBuffer[x] = null;
        }

        @Override
        public String toString() {
            return "TrieNode [map=" + map + ", endOfWord=" + endOfWord + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (endOfWord ? 1231 : 1237);
            result = prime * result + ((map == null) ? 0 : map.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TrieNode other = (TrieNode) obj;
            if (endOfWord != other.endOfWord)
                return false;
            if (map == null) {
                if (other.map != null)
                    return false;
            } else if (!map.equals(other.map))
                return false;
            return true;
        }

        public HashMap<Character, TrieNode<T>> getMap() {
            return map;
        }
        public void setMap(HashMap<Character, TrieNode<T>> map) {
            this.map = map;
        }
        public boolean getEndOfWord() {
            return endOfWord;
        }
        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }


        /* Buffers */

        public TrieNode<T>[] getBuffer() {
            if(needBuffer)
                return buffer;
            return null;
        }
        public void setBuffer(TrieNode<T>[] buffer) {
            if(needBuffer)
                this.buffer = buffer;
            else {
                try {
                    System.out.println("Illegal Accessing of bigBuffer is denied");
                    throw new IllegalStateException("Illegal Accessing of buffer is denied");
                }catch(Exception e) {
                    System.out.println("Some Error : " + e.getMessage());
                    e.printStackTrace();
                }
            }

        }
        public TrieNode<T>[] getBigBuffer() {
            if(needBuffer)
                return bigBuffer;
            return null;
        }
        public void setBigBuffer(TrieNode<T>[] bigBuffer) {
            if(needBuffer)
                this.bigBuffer = bigBuffer;
            else {
                try {
                    System.out.println("Illegal Accessing of bigBuffer is denied");
                    throw new IllegalStateException("Illegal Accessing of bigBuffer is denied");
                }catch(Exception e) {
                    System.out.println("Some Error : " + e.getMessage());
                    e.printStackTrace();
                }
            }

        }
        public ArrayList<T> getList() {
            return list;
        }
        public void setList(ArrayList<T> list) {
            this.list = list;
        }

    }

}