import java.io.BufferedReader;
import java.util.HashSet;
import java.io.*;
import java.util.*;

// import sun.net.www.content.text.plain;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    static boolean invalid;
    public static void main(String args[] ) throws Exception {
        
        File file = new File("P:\\Sublime Text 3 Projects\\Java Projects\\Practice\\HE2input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int t = getInt(br.readLine());
        while(t-- > 0){
            testCase(br);
        }

    }

    public static void testCase(BufferedReader br) throws Exception{
        String in[] = br.readLine().split(" ");
        int n = getInt(in[0]),m = getInt(in[1]);
        ArrayList<HashSet<Integer>> b = new ArrayList<>();

        for(int x = 0;x < n;x++)
            b.add(new HashSet<Integer>());

        for(int x = 0;x < m;x++){
            int input[] = getIntArray(br.readLine());
            int u = input[0] - 1,v = input[1] - 1;
            b.get(v).add(u);
        }

        boolean vis[] = new boolean[n]; invalid = false;
        for(int x = 0;x < n;x++){
            if(invalid == true)
                break;
            if(vis[x] == false){
                HashSet<Integer> curr = new HashSet<>();
                recur(x,b,curr,vis);
            }
        }

        if(invalid == true)
            System.out.println(0);
        else
            System.out.println(1);
    }

    public static void recur(int c,ArrayList<HashSet<Integer>> b,HashSet<Integer> curr,boolean vis[]){
        int n = vis.length;
        curr.add(c);
        HashSet<Integer> a = b.get(c);

        for(int x : a){
            if(invalid == true)
                return;
                
            if(vis[x] == true)
                continue;
            else{
                if(curr.contains(x)){
                    invalid = true;
                    return;
                }else{
                    recur(x,b,curr,vis);
                }
            }
            
        }

        vis[c] = true;
    }

    public static int[] getIntArray(String s){
        String str[] = s.split(" ");
        int a[] = new int[str.length];
        for(int x = 0;x < str.length;x++)
            a[x] = getInt(str[x]);
        
        return a;
    }

    public static int getInt(String s){
        return Integer.parseInt(s);
    }
}
