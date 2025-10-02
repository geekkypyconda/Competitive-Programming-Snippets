import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

/* Hepler Class Methods **only Few Imp Ones**

* int[][] getIntMatrix(String s,int r,int c)                //Works only for int and long
* char getCharacterValue(int i)
* int getIntegerValueOfCharacter(char c)
* boolean isPerfectSquare(int n)                            // works only for int and long
* int getLargestPrimeFactor(int n)                          //works only for int and long
* boolean isPrime(int n)                                    //works only for int and long
* void printArray(int a[]),                                 //works for all
* void printMatrix(int a[][]),                              //works for all
* void printNestedList(ArrayList<ArrayList<Integer>> a)     //works for all
* boolean isPalindrome                                      //works for all except Character
* T swap(T)                                                 //works only for int[],long[] and String
* T getArraySum(T[])                                        //works only for int[],long[],double[]
* T reverse(T)                                              //works only for String,int and long
* T[] getReverseArray(T[])                                  //works for all
* HashSet<T> getHashSet(T[])                                //works for int[],long[],char[],String[]
* T setBit(T,k,side)                                        //works only when n =2 int or long and k is always int **returns int or long**
* String setBitString(T,k,side)                             //works only when n = int or long and k is always int **returns String**
* String cvtToBinary(T)
* int cvtToDecimal(String n)                                //Always returns Long
* int getSetBits(long n)                                    //Returns no of set bits in given number
* boolean isPowerOf2(T)
* long fastPow(long x,long y)                               //Calc Power in O(log y) time
* long modPow(long x,long y,long MOD)                       //Calc Power With MOD
* boolean isSafe(T [][], row,col)                           //Returns whether current row and col are under bounds
* T Log2(T num)                                             //Returns (Math.log(num) / Math.log(2))
* */


class Codechef {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static Helper sc = new Helper(br);
    static int MOD = 1000000007,INF = Integer.MAX_VALUE,SEM_INF = INF / 2,NEG_INF = Integer.MIN_VALUE;
    static long MAX_INF = Long.MAX_VALUE;
    static BigInteger B = new BigInteger("1");
//    static Scanner sc = new Scanner(System.in);
    static long dp[] = new long[3001];
    public static void main(String args[]) {
        try {
            int t = sc.getInt(br.readLine());
            while (t-- > 0)
            {
                testCase();
            }
            out.flush();
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void testCase() throws Exception
    {

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

    public int  [] getIntArray( String s) throws Exception
    {
        String input[] = s.split(" ");
        int res[] = new int[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getInt(input[x]);

        return res;
    }

    public long  [] getLongArray( String s) throws Exception
    {
        String input[] = s.split(" ");
        long res[] = new long[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getLong(input[x]);

        return res;
    }

    public double  [] getDoubleArray( String s) throws Exception
    {
        String input[] = s.split(" ");
        double res[] = new double[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getDouble(input[x]);

        return res;
    }

    public int[][] getIntMatrix(String s,int r,int c)
    {
        int i = 0;int mat[][] = new int[r][c];
        String st[] = s.split(" ");
        for(int x = 0;x < r;x++)
            for(int y =0 ;y < c;y++)
                mat[x][y] = Integer.parseInt(st[i++]);
        return mat;

    }

    public long[][] getlongMatrix(String s,int r,int c)
    {
        int i = 0;long mat[][] = new long[r][c];
        String st[] = s.split(" ");
        for(int x = 0;x < r;x++)
            for(int y =0 ;y < c;y++)
                mat[x][y] = Long.parseLong(st[i++]);
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

    public char getCharacterValue(int i)
    {
        return (char)i;
    }
    public int getIntegerValueOfCharacter(char c)
    {
        return (int)c;
    }

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
        if(x >=0 && y >= 0 && x < a.length && y < a[0].length)
            return true;
        return false;
    }
    public boolean isSafe(double a[][],int x,int y)
    {
        if(x >=0 && y >= 0 && x < a.length && y < a[0].length)
            return true;
        return false;
    }

    public boolean isSafe(char a[][],int x,int y)
    {
        if(x >=0 && y >= 0 && x < a.length && y < a[0].length)
            return true;
        return false;
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


    public static long fastPow(long n,long p)
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
    public static long modPow(long n,long p,long md)
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
        StringBuilder sb = new StringBuilder(s + "");
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

    public int getSetBits(long n)
    {
        int count = 0;
        while (n > 0){
            count += n % 2;
            n /= 2;
        }
        return count;
    }

    public int setBit(int n,int k,String side) throws Exception
    {
        if(side.equalsIgnoreCase("r") || side.equalsIgnoreCase("right")){
            return ((1 << k) | n);
        }else if(side.equalsIgnoreCase("l") || side.equalsIgnoreCase("left")){
            return (int)cvtToDecimal(setBitString(n,k,"l"));
        }else
            throw new Exception("Unknown Side of shift! side must be l,left,r,right");

    }

    public long setBit(long n,int k,String side) throws Exception
    {
        if(side.equalsIgnoreCase("r") || side.equalsIgnoreCase("right")){
            return ((1 << k) | n);
        }else if(side.equalsIgnoreCase("l") || side.equalsIgnoreCase("left")){
            return cvtToDecimal(setBitString(n,k,"l"));
        }else
            throw new Exception("Unknown Side of shift! side must be l,left,r,right");


    }

    public String setBitString(int n,int k,String side) throws Exception
    {
        StringBuilder sb = new StringBuilder(cvtToBinary(n) + "");
        if(side.equalsIgnoreCase("r") || side.equalsIgnoreCase("right")){
            sb.setCharAt(sb.length() - 1 - k,'1');
        }else if(side.equalsIgnoreCase("l") || side.equalsIgnoreCase("left")){
            sb.setCharAt(k,'1');
        }else
            throw new Exception("Unknown Side of shift! side must be l,left,r,right");
        return sb.toString();
    }

    public String setBitString(long n, int k,String side) throws Exception
    {
        StringBuilder sb = new StringBuilder(cvtToBinary(n) + "");
        if(side.equalsIgnoreCase("r") || side.equalsIgnoreCase("right")){
            sb.setCharAt(sb.length() - 1 - k,'1');
        }else if(side.equalsIgnoreCase("l") || side.equalsIgnoreCase("left")){
            sb.setCharAt(k,'1');
        }else
            throw new Exception("Unknown Side of shift! side must be l,left,r,right");
        return sb.toString();
    }

    public long cvtToDecimal(String n)
    {
        String num = n;
        long dec_value = 0;

        // Initializing base value to 1,
        // i.e 2^0
        long base = 1;

        int len = num.length();
        for (int i = len - 1; i >= 0; i--) {
            if (num.charAt(i) == '1')
                dec_value += base;
            base = base * 2;
        }

        return dec_value;
    }

    public String cvtToBinary(int n)
    {
        if(n == 0 || n == 1)
            return "" + n;
        StringBuilder sb = new StringBuilder();
        while (n > 1){
            if(n % 2 == 0)
                sb.append(0);
            else
                sb.append(1);
            n /= 2;
        }
        if(n == 1)
            sb.append(1);
        return sb.reverse().toString();
    }

    public String cvtToBinary(long n)
    {
        if(n == 0 || n == 1)
            return "" + n;
        StringBuilder sb = new StringBuilder();
        while (n > 1){
            if(n % 2 == 0)
                sb.append(0);
            else
                sb.append(1);
            n /= 2;
        }
        if(n == 1)
            sb.append(1);
        return sb.reverse().toString();
    }

    /*Printing Arena*/

    public void print(BufferedWriter out,Object s) throws Exception
    {
        out.write(String.valueOf(s));
    }

    public void println(BufferedWriter out,Object s) throws Exception
    {
        out.write(String.valueOf(s));
    }

    public void printArray(int a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(String.valueOf(a[x]) + " ");
        if(nextLine)
            System.out.println();

    }

    public void printArray(long a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(String.valueOf(a[x]) + " ");
        if(nextLine)
            System.out.println();

    }

    public void printArray(char a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(String.valueOf(a[x]) + " ");
        if(nextLine)
            System.out.println();

    }

    public void printArray(double a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(String.valueOf(a[x]) + " ");
        if(nextLine)
            System.out.println();

    }
    public void printArray(String a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(String.valueOf(a[x]) + " ");
        if(nextLine)
            System.out.println();

    }

    public void printArray(Object a[],int s,int e,boolean nextLine)
    {
        if(e >= a.length || s < 0)
            throw new ArrayIndexOutOfBoundsException("Array Index Out Of Bounds " + "[" + s + ", " + e + "] Array Size: " + a.length);
        for(int x = s;x <= e;x++)
            System.out.print(a[x].toString() + " ");
        if(nextLine)
            System.out.println();

    }


    public void printMatrix(int a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(a[x][y] + " ");
            System.out.println();
        }

    }

    public void printMatrix(long a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(String.valueOf(a[x][y]) + " ");
            System.out.println();
        }

    }
    public void printMatrix(char a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(String.valueOf(a[x][y]) + " ");
            System.out.println();
        }

    }
    public void printMatrix(double a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(String.valueOf(a[x][y]) + " ");
            System.out.println();
        }

    }

    public void printMatrix(String a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(String.valueOf(a[x][y]) + " ");
            System.out.println();
        }

    }

    public void printMatrix(Object a[][])
    {
        for(int x = 0;x < a.length;x++){
            for (int y = 0;y < a[0].length;y++)
                System.out.print(a[x][y].toString() + " ");
            System.out.println();
        }

    }


    public void printIntNestedList(ArrayList<ArrayList<Integer>> a)
    {
        for(int x = 0;x < a.size();x++){
            ArrayList<Integer> al = a.get(x);
            for(int y = 0;y < al.size();y++)
                System.out.print(String.valueOf(al.get(y)) + " ");
            System.out.println();
        }
    }
    public void printLongNestedList(ArrayList<ArrayList<Long>> a)
    {
        for(int x = 0;x < a.size();x++){
            ArrayList<Long> al = a.get(x);
            for(int y = 0;y < al.size();y++)
                System.out.print(String.valueOf(al.get(y)) + " ");
            System.out.println();
        }
    }

    public void printCharNestedList(ArrayList<ArrayList<Character>> a)
    {
        for(int x = 0;x < a.size();x++){
            ArrayList<Character> al = a.get(x);
            for(int y = 0;y < al.size();y++)
                System.out.print(String.valueOf(al.get(y)) + " ");
            System.out.println();
        }
    }

    public void printStringNestedList(ArrayList<ArrayList<String>> a)
    {
        for(int x = 0;x < a.size();x++){
            ArrayList<String> al = a.get(x);
            for(int y = 0;y < al.size();y++)
                System.out.print(String.valueOf(al.get(y)) + " ");
            System.out.println();
        }
    }

    public String swap(String st,int i,int j)
    {
        StringBuilder sb = new StringBuilder(st);
        sb.setCharAt(i,st.charAt(j));
        sb.setCharAt(j,st.charAt(i));
        return sb.toString();
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

    public long getArraySum(int a[],int s,int e)
    {
        long sum = 0;
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

    public int reverse(int n)
    {
        StringBuilder sb = new StringBuilder(n + "");
        return Integer.parseInt(sb.reverse().toString());
    }

    public long reverse(long n)
    {
        StringBuilder sb = new StringBuilder(n + "");
        return Long.parseLong(sb.reverse().toString());
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
            set.add(a[x]);
        return set;
    }

    public HashSet<Long> getHashSet(long a[])
    {
        HashSet<Long> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(a[x]);
        return set;
    }

    public HashSet<Character> getHashSet(char a[])
    {
        HashSet<Character> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(a[x]);
        return set;
    }

    public HashSet<String> getHashSet(String a[])
    {
        HashSet<String> set = new HashSet<>();
        for(int x = 0;x < a.length;x++)
            set.add(a[x]);
        return set;
    }

    public int getMax(int a[])
    {
        int max = Integer.MIN_VALUE;
        for(int x = 0;x < a.length;x++)
            max = Math.max(max,a[x]);
        return max;
    }

    public long getMax(long a[])
    {
        long max = Long.MIN_VALUE;
        for(int x = 0;x < a.length;x++)
            max = Math.max(max,a[x]);
        return max;
    }

    public double getMax(double a[])
    {
        double max = Double.MIN_VALUE;
        for(int x = 0;x < a.length;x++)
            max = Math.max(max,a[x]);
        return max;
    }

    public int getMin(int a[])
    {
        int min = Integer.MAX_VALUE;
        for(int x = 0;x < a.length;x++)
            min = Math.min(min,a[x]);
        return min;
    }

    public long getMin(long a[])
    {
        long min = Long.MAX_VALUE;
        for(int x = 0;x < a.length;x++)
            min = Math.min(min,a[x]);
        return min;
    }

    public double getMin(double a[])
    {
        double min = Double.MAX_VALUE;
        for(int x = 0;x < a.length;x++)
            min = Math.min(min,a[x]);
        return min;
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