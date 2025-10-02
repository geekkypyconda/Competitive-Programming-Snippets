import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

public class file
 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static INPUT sc = new INPUT(br);
	static int INF = Integer.MAX_VALUE,NEG_INF = Integer.MIN_VALUE,SEM_INF = INF / 2,count = 0,MOD = 1000000007,ops = 0;
    static long MAX_INF = Long.MAX_VALUE;
    static BigInteger B = new BigInteger("1");
    //static Scanner sc = new Scanner(System.in);

	public static void main (String[] args) throws Exception
	{
	    try{
            int t = sc.getInt(br.readLine());
            while(t-- > 0)
            {
                testCase();           
            }
            out.flush();
        }catch(Exception e){
            System.out.println("Exception Occured!");
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

class INPUT
{
    BufferedReader br;
    StringTokenizer st;
    
    public INPUT(BufferedReader br)
    {
        this.br = br;
    }

    /*RAW INPUTS*/

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

    public int  [] getIntArray( String s) 
    {
        String input[] = s.split(" ");
        int res[] = new int[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getInt(input[x]);

        return res;
    }
    public long  [] getLongArray( String s) 
    {
        String input[] = s.split(" ");
        long res[] = new long[input.length];
        for(int x = 0;x < res.length;x++)
            res[x] = getLong(input[x]);

        return res;
    }

    public double  [] getDoubleArray( String s) 
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

}
