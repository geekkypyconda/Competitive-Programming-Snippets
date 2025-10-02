import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;


class Pr
{

	public static void preComp()
	{

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static INPUT sc = new INPUT(br);
	static int MOD = 1000000007;
	// static int MOD = 17;

	public static void main(String args[]) throws Exception
	{
		try{
			preComp();
			// int t = Integer.parseInt(br.readLine());
			int t = 1;
			while(t-- > 0)
			{
				testCase();
			}
			out.flush();
		}catch(Exception e){
			System.out.println("Exception Occured");
			e.printStackTrace();
		}
		
	}

	public static void testCase() throws Exception{
		BufferedReader r = new BufferedReader(new FileReader("G:\\Google Chrome\\Chrome downloads\\fileInput.txt"));
		INPUT s = new INPUT(r);
		int n = s.nextInt();
		int a[] = new int[n];
		for(int x = 0;x < n;x++)
			a[x] = s.nextInt();

		println(Maximize(a,n));
		// println(getMul(9,10));
	}

	public static int Maximize(int a[], int n)
    {
        int s = 0;
        Arrays.sort(a);
        for(int x = 0;x < n;x++){
            int v = getMul(a[x],x);
            s = (s + v) % MOD;
        }
        
        return s;        
    }  

    static int getMul(int a,int b){
        if(a > b){
            int t = a;
            a = b;
            b = t;
        }
        
        long res = 0;
        int t = MOD / b;
        while(a > 0){
            int v = 0;

 			if(a <= t){
 				v = b * a;
 				res = (res + v) % MOD;
 				return (int)res;
 			}
 			else{
 				v = t * b;
 				a -= t;
 			}

            if(a > 0){
            	a--;
            	v = (v + b) % MOD;
            }

            res = (res + v) % MOD;

        }
        
        return (int)res;
            
    }

	public static void println(Object o){
		System.out.println(String.valueOf(o));
	}

	public static void println(){
		System.out.println();
	}

	public static void print(Object o){
		System.out.print(String.valueOf(o));
	}

	public static void write(Object o) throws Exception{
		out.write(String.valueOf(o));
	}

	public static void writeln(Object o) throws Exception{
		out.write(String.valueOf(o) + "\n");
	}

	public static void writeln() throws Exception{
		out.write("\n");
	}
}

class INPUT{

	BufferedReader br;
	StringTokenizer st;

	INPUT(BufferedReader bri)
	{
		this.br = bri;
	}

	//String Tokenizer Basic Inputs

	public String next() throws Exception
	{
		if(st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	public String nextLine() throws Exception
	{
		try{
			return br.readLine();	
		}catch(Exception e){
			System.out.println("Error Occured! " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}

	//Array & Matrix Inputs

	public int[] getIntArray(String s){
		String c[] = s.split(" ");
		int a[] = new int[c.length];
		for(int x = 0;x < c.length;x++)
			a[x] = getInt(c[x]);
		return a;
	}

	//Simple Inputs

	public int nextInt() throws Exception{
		return Integer.parseInt(next());
	}

	public double nextDouble() throws Exception{
		return Double.parseDouble(next());
	}

	public float nextFloat() throws Exception{
		return Float.parseFloat(next());
	}

	public long nextLong() throws Exception{
		return Long.parseLong(next());
	}

	//Conversion

	public long getLong(String s){
		return Long.parseLong(s);
	}

	public int getInt(String s){
		return Integer.parseInt(s);
	}

	public double getDouble(String s){
		return Double.parseDouble(s);
	}

	public float getFloat(String s){
		return Float.parseFloat(s);
	}

}