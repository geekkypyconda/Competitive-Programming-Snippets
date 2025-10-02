import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;
import java.util.HashSet;

public class file {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in),256 * 1024);
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out),256 * 1024);
    static Helper sc = new Helper(br);
    static int MOD = 1000000007,INF = Integer.MAX_VALUE,SEM_INF = INF / 2,NEG_INF = Integer.MIN_VALUE;
    static long MAX_INF = Long.MAX_VALUE - 1000, NEG_MAX_INF = Long.MIN_VALUE + 1000;
    static BigInteger B = new BigInteger("1");
//    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        try {
            int t = sc.getInt(br.readLine());
//            preComp();
            while (t-- > 0) {
                testCase();
            }
            out.flush();
        } catch (Exception e) {
            System.out.println("Exception Occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void testCase() throws Exception {

    }

    public static void writeln() throws Exception
    {
        out.write("\n");
    }
    public static void write(Object o) throws Exception
    {
        out.write(String.valueOf(o));
    }
    public static void writeln(Object o) throws Exception
    {
        out.write(String.valueOf(o) + "\n");
    }
    public static void println()
    {
        System.out.println();
    }
    public static void print(Object o)
    {
        System.out.print(String.valueOf(o));
    }
    public static void println(Object o)
    {
        System.out.println(String.valueOf(o));
    }

}

class Helper
{
    FastReader fr;

    /*Constructor*/

    public Helper(BufferedReader br)
    {
        try{
            fr = new FastReader(br);
        }catch (Exception e){
            System.out.println("Exception Occured: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /* Inputs*/

    public String next() throws Exception
    {
        return fr.next();
    }

    public int nextInt() throws Exception
    {
        return fr.nextInt();
    }

    public long nextLong() throws Exception
    {
        return fr.nextLong();
    }

    public String trimLine() throws Exception
    {
        return fr.trimLine();
    }

    public String rawLine() throws Exception
    {
        return fr.nextLine();
    }

    public double nextDouble() throws Exception
    {
        return fr.nextDouble();
    }

    public float nextFloat() throws Exception
    {
        return fr.nextFloat();
    }

    public int  [] getIntArray() throws Exception
    {
        String s = this.trimLine();
        return this.getIntArrayFromLine(s);
    }

    public long  [] getLongArray() throws Exception
    {
        String s = this.trimLine();
        return this.getLongArrayFromLine(s);
    }

    public double  [] getDoubleArray() throws Exception
    {
        String s = this.trimLine();
        return this.getDoubleArrayFromLine(s);
    }

    public int  [] getIntArrayFromLine(String s) throws Exception
    {
        String input[] =  s.split(" ");
        int res[] = new int[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getInt(input[x]);

        return res;
    }

    public long  [] getLongArrayFromLine(String s) throws Exception
    {
        String input[] = s.split(" ");
        long res[] = new long[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getLong(input[x]);

        return res;
    }

    public double  [] getDoubleArrayFromLine(String s) throws Exception
    {
        String input[] = s.split(" ");
        double res[] = new double[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getDouble(input[x]);

        return res;
    }

    public int[][] getIntMatrix(int r,int c) throws Exception
    {
        int mat[][] = new int[r][c];
        for(int x = 0;x < r;x++)
            mat[x] = this.getIntArrayFromLine(this.trimLine());

        return mat;
    }

    public long[][] getLongMatrix(int r,int c) throws Exception
    {
        long mat[][] = new long[r][c];
        for(int x = 0;x < r;x++)
            mat[x] = this.getLongArrayFromLine(this.trimLine());

        return mat;
    }

    public double[][] getDoubleMatrix(int r,int c) throws Exception
    {
        double mat[][] = new double[r][c];
        for(int x = 0;x < r;x++)
            mat[x] = this.getDoubleArrayFromLine(this.trimLine());

        return mat;
    }

    public int[][] getIntMatrixFromLine(String s, int r,int c) throws Exception
    {
        int i = 0;int mat[][] = new int[r][c];
        String st[] = s.split(" ");
        for(int x = 0;x < r;x++)
            for(int y =0 ;y < c;y++)
                mat[x][y] = Integer.parseInt(st[i++]);

        return mat;
    }

    public long[][] getLongMatrixFromLine(String s, int r,int c) throws Exception
    {
        int i = 0;long mat[][] = new long[r][c];
        String st[] = s.split(" ");
        for(int x = 0;x < r;x++)
            for(int y =0 ;y < c;y++)
                mat[x][y] = Long.parseLong(st[i++]);

        return mat;
    }

    public double[][] getDoubleMatrixFromLine(String s, int r,int c) throws Exception
    {
        int i = 0;double mat[][] = new double[r][c];
        String st[] = s.split(" ");
        for(int x = 0;x < r;x++)
            for(int y =0 ;y < c;y++)
                mat[x][y] = Double.parseDouble(st[i++]);

        return mat;
    }

    public int getInt(String s)
    {
        return Integer.parseInt(s);
    }
    public long getLong(String s)
    {
        return Long.parseLong(s);
    }
    public float getFloat(String s)
    {
        return Float.parseFloat(s);
    }
    public double getDouble(String s)
    {
        return Double.parseDouble(s);
    }

    /*Some basic hepler methods*/

    public int Log2(int num)
    {
        return (int)(Math.log(num) / Math.log(2));
    }

    public long Log2(long num)
    {
        return (long) (Math.log(num) / Math.log(2));
    }

    public double Log2(double num)
    {
        return (double) (Math.log(num) / Math.log(2));
    }

    public long nCr(long n,long r)
    {
        if(r > n - r)
            r = n - r;
        long res = 1;
        for(long x = 0;x < r;x++) {
            res *= (n - x);
            res /= (x + 1);
        }
        return res;
    }

    public long nCrMod(long n,long r,long md)
    {
        if(r > n - r)
            r = n - r;
        long res = 1;
        for(long x = 0;x < r;x++) {
            res = (res * (n - x)) % md;
            res = (res * (modInverse(x + 1,md) % md)) % md;
            res %= md;
        }
        return res % md;
    }

    public long modInverse(long n,long md)
    {
        return modPow(n,md - 2,md);
    }

    public int getGCD(int a,int b)
    {
        if(b == 0)
            return a;
        return getGCD(b,a % b);
    }
    public long getGCD(long a,long b)
    {
        if(b == 0)
            return a;
        return getGCD(b,a % b);
    }
    public double getGCD(double a,double b)
    {
        if(b == 0)
            return a;
        return getGCD(b,a % b);
    }
    public float getGCD(float a,float b)
    {
        if(b == 0)
            return a;
        return getGCD(b,a % b);
    }

    public int getLCM(int a,int b)
    {
        return ((a * b) / getGCD(a,b));
    }
    public long getLCM(long a,long b)
    {
        return ((a * b) / getGCD(a,b));
    }
    public double getLCM(double a,double b)
    {
        return ((a * b) / getGCD(a,b));
    }
    public float getLCM(float a,float b)
    {
        return ((a * b) / getGCD(a,b));
    }

    public boolean isSafe(int a[][],int x,int y)
    {
        if(x >=0 && y >= 0 && x < a.length && y < a[0].length)
            return true;
        return false;
    }
    public boolean isSafe(long a[][],int x,int y)
    {
        return x >= 0 && y >= 0 && x < a.length && y < a[0].length;
    }
    public boolean isSafe(double a[][],int x,int y)
    {
        return x >= 0 && y >= 0 && x < a.length && y < a[0].length;
    }

    public boolean isSafe(char a[][],int x,int y)
    {
        return x >= 0 && y >= 0 && x < a.length && y < a[0].length;
    }

    public boolean isPerfectSquare(int n)
    {
        if(n == 0 || n == 1)
            return true;
        if(n == 2 || n == 3)
            return false;
        double d = Math.sqrt(n);

        return (d - Math.floor(d) == 0);
    }

    public boolean isPerfectSquare(long n)
    {
        if(n == 0 || n == 1)
            return true;
        if(n == 2 || n == 3)
            return false;
        double d = Math.sqrt(n);

        return (d - Math.floor(d) == 0);
    }

    public boolean isPowerOf2(long n)
    {
        return n != 0 && (n & (n - 1)) == 0;
    }


    public long fastPow(long n,long p)
    {
        long res = 1;
        while(p > 0){
            if(p % 2 != 0)
                res = res * n;
            p = p / 2;
            n = n * n;
        }
        return res;
    }
    public long modPow(long n,long p,long md)
    {
        long res = 1;
        n = n % md;
        if(n == 0)
            return 0;
        while(p > 0){
            if(p % 2 != 0)
                res = ((res % md) * (n % md)) % md;
            p = p / 2;
            n = ((n % md) * (n % md)) % md;
        }
        return (res % md);
    }

    public boolean isPalindrome(int n)
    {
        StringBuilder sb = new StringBuilder(n + "");
        return (Integer.parseInt(sb.reverse().toString()) == n);
    }

    public boolean isPalindrome(long n)
    {
        StringBuilder sb = new StringBuilder(n + "");
        return (Long.parseLong(sb.reverse().toString()) == n);
    }

    public boolean isPalindrome(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        return (sb.reverse().toString().equals(s));
    }

    public int getSmallestPrimeFactor(int n)
    {
        if(n == 1 || n == 0)
            return n;
        if(n % 2 == 0)
            return 2;
        else if(n % 3 == 0)
            return 3;
        int pf = -1;
        for(int x = 3;x <= Math.sqrt(n);x += 2)
            if(n % x == 0)
                return x;
        return n;
    }

    public int getLargestPrimeFactor(int n)
    {
        int pf = -1;

        if(n == 1 || n == 2 || n == 3 || n == 0)
            return n;
        while(n % 2 == 0){
            pf = 2;
            n /= 2;
        }
        for(int x = 3;x <= Math.sqrt(n);x += 2)
            while (n % x == 0){
                pf = x;
                n /= x;
            }
        if(n > 2)
            pf = n;
        return pf;
    }

    public long getSmallestPrimeFactor(long n)
    {
        if(n == 1 || n == 0)
            return n;

        if(n % 2 == 0)
            return 2;
        else if(n % 3 == 0)
            return 3;
        for(long x = 3;x <= Math.sqrt(n);x += 2)
            if(n % x == 0)
                return x;
        return n;
    }

    public long getLargestPrimeFactor(long n)
    {
        long pf = -1;
        if(n == 1 || n == 2 || n == 3 || n == 0)
            return n;
        while(n % 2 == 0){
            pf = 2;
            n /= 2;
        }
        for(long x = 3;x <= Math.sqrt(n);x += 2)
            while (n % x == 0){
                pf = x;
                n /= x;
            }
        if(n > 2)
            pf = n;
        return pf;
    }

    public boolean isPrime(int n)
    {
        if(n == 0 || n == 1)
            return false;
        if(getLargestPrimeFactor(n) == n)
            return true;
        return false;
    }

    public boolean isPrime(long n)
    {
        if(n == 0 || n == 1)
            return false;
        if(getLargestPrimeFactor(n) == n)
            return true;
        return false;
    }

    public StringBuilder swap(StringBuilder sb,int i,int j)
    {
        char a = sb.charAt(i),b = sb.charAt(j);
        sb.setCharAt(i,b);
        sb.setCharAt(j,a);

        return sb;
    }

    public int [] swap(int a[],int i,int j)
    {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
        return a;
    }

    public long[] swap(long a[],int i,int j)
    {
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
        return a;
    }

    public char[] swap(char a[],int i,int j)
    {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
        return a;
    }

    public int getArraySum(int a[],int s,int e)
    {
        int sum = 0;
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + e + ", " + s + "]");
        for(int x = s;x <= e;x++)
            sum += a[x];

        return sum;
    }

    public long getArraySum(long a[],int s,int e)
    {
        long sum = 0;
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + e + ", " + s + "]");
        for(int x = s;x <= e;x++)
            sum += a[x];
        return sum;

    }

    public double getArraySum(double a[],int s,int e)
    {
        double sum = 0;
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + e + ", " + s + "]");
        for(int x = s;x <= e;x++)
            sum += a[x];
        return sum;
    }

    public String reverse(String s)
    {
        StringBuilder sb = new StringBuilder(s + "");
        return sb.reverse().toString();
    }

    public Object[] getReverseArray(Object a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            Object o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }

    public int[] getReverseArray(int a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            int o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }

    public double[] getReverseArray(double a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            double o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }
    public char[] getReverseArray(char a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            char o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }

    public long[] getReverseArray(long a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            long o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }

    public String[] getReverseArray(String a[])
    {
        int i = 0,j = a.length - 1;
        while (i <= j)
        {
            String o = a[i];
            a[i] = a[j];
            a[j] = o;
            i++;j--;
        }
        return a;
    }

    public HashSet<Integer> getHashSet(int a[])
    {
        HashSet<Integer> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(Integer.valueOf(a[x]));
        return set;
    }

    public HashSet<Long> getHashSet(long a[])
    {
        HashSet<Long> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(Long.valueOf(a[x]));
        return set;
    }

    public HashSet<Character> getHashSet(char a[])
    {
        HashSet<Character> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(Character.valueOf(a[x]));
        return set;
    }

    public HashSet<String> getHashSet(String a[])
    {
        HashSet<String> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(a[x]);
        return set;
    }

    public int[] getMinMax(int a[])
    {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;;
        for (int i : a) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        return new int[]{min,max};
    }

    public long[] getMinMax(long a[])
    {
        long max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;;
        for (long l : a) {
            max = Math.max(max, l);
            min = Math.min(min, l);
        }

        return new long[]{min,max};
    }

    public double[] getMinMax(double a[])
    {
        double max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;;
        for (double v : a) {
            max = Math.max(max, v);
            min = Math.min(min, v);
        }

        return new double[]{min,max};
    }

    class DSU {
        int[] par,size;
        int n;

        // Constructor
        public DSU(int n) {
            this.n = n;
            this.par = new int[n];
            this.size = new int[n];
            // Initially each element is its own parent
            for (int i = 0; i < n; i++) {
                par[i] = i;
                size[i] = 1; // each set has rank 1 initially
            }
        }

        // Find with path compression
        public int findPar(int x) {
            if (par[x] == x) return x;

            par[x] = findPar(par[x]); // path compression

            return par[x];
        }

        // Union by rank
        public void union(int x, int y) {
            int parA = findPar(x);
            int parB = findPar(y);

            if (parA == parB) return; // already in same set

            // attach smaller set under larger set
            if (size[parA] < size[parB]) {
                par[parA] = parB;
                size[parB] += size[parA];
            } else {
                par[parB] = parA;
                size[parA] += size[parB];
            }
        }

        // Check if two elements belong to the same set
        public boolean isConnected(int x, int y) {
            return findPar(x) == findPar(y);
        }

    }

    class Trie {

        // Inner class representing each node in the Trie
        class TrieNode {
            TrieNode[] children;  // array of child references (a-z)
            boolean isEndOfWord;  // true if this node marks the end of a word

            public TrieNode() {
                children = new TrieNode[26]; // English lowercase letters
                isEndOfWord = false;
            }
        }

        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        // Insert a word into the Trie
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEndOfWord = true;
        }

        // Search for a full word
        public boolean search(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return node.isEndOfWord;
        }

        // Check if any word starts with a given prefix
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return false;
                }
                node = node.children[index];
            }
            return true;
        }

        // Print all words stored in the Trie
        public void printAll() {
            printAllHelper(root, new StringBuilder());
        }

        // Recursive DFS helper
        private void printAllHelper(TrieNode node, StringBuilder prefix) {
            if (node.isEndOfWord) {
                System.out.println(prefix.toString());
            }
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    prefix.append((char) ('a' + i));  // add current char
                    printAllHelper(node.children[i], prefix);
                    prefix.deleteCharAt(prefix.length() - 1); // backtrack
                }
            }
        }
    }

    private class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) throws Exception {
            this.br = br;
        }

        public String next() throws Exception
        {
            if(st == null || !st.hasMoreElements())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws Exception
        {
            return Integer.parseInt(next());
        }

        public long nextLong() throws Exception
        {
            return Long.parseLong(next());
        }

        public String trimLine() throws Exception
        {
            try{
                return br.readLine().trim();
            }catch (Exception e){
                System.out.println("Exception Occured: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        public String nextLine() throws Exception
        {
            try{
                return br.readLine();
            }catch (Exception e){
                System.out.println("Exception Occured: " + e.getMessage());
                e.printStackTrace();
                return null;
            }

        }

        public double nextDouble() throws Exception
        {
            return Double.parseDouble(next());
        }

        public float nextFloat() throws Exception
        {
            return Float.parseFloat(next());
        }
    }

}