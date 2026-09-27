import java.lang.*;
import java.io.*;
import java.math.*;
import java.util.*;


class Pair{
    int one, two;
    int child1, child2;
    Pair(){
        this.one = 0;
        this.two = 0;
        this.child1 = -1;
        this.child2 = - 1;
    }

    void add(int val, int child){
        if(val >= one){
            two = one;
            one = val;

            child2 = child1;
            child1 = child;
        }else if(val > two){
            two = val;
            child2 = child;
        }
    }

    int getMax(){
        return Math.max(one, two);
    }

    int getMax(int child){
        if(child1 == child) return two;
        return one;
    }

}

class Solution {
    List<String> ans;
    String till;
    public List<String> findItinerary(List<List<String>> a) {
        HashMap<String, Integer> si = new HashMap<>();
        HashMap<Integer, String> is = new HashMap<>();

        int i = 0;
        for(List<String> l : a){
            String one = l.get(0), two = l.get(1);

            if(!si.containsKey(one)) si.put(one, i++);
            if(!si.containsKey(two)) si.put(two, i++);
        }

        for(String k : si.keySet()){
            is.put(si.get(k), k);
        }

        int n = si.size();
        ArrayList<Integer> set[] = new ArrayList[n];
        for(int x = 0;x < n;x++) set[x] = new ArrayList<>();
        for(List<String> l : a){
            String one = l.get(0), two = l.get(1);

            int u = si.get(one), v = si.get(two);
            set[u].add(v);
        }

        boolean vis[][] = new boolean[n][n];
        ans = new ArrayList<>();
        till = "";
        dfs(set, 0, vis, new ArrayList<>(), is, a.size());

        return ans;
    }

    void dfs(ArrayList<Integer> set[], int u, boolean vis[][], ArrayList<String> curr,
             HashMap<Integer, String> map, int total){

        curr.add(map.get(u));
        if(curr.size() == total + 1){
            StringBuilder sb = new StringBuilder();
            for(int x = 0;x < curr.size();x++)
                sb.append(curr.get(x));

            if(till.equals("") || sb.toString().compareTo(till) < 0){
                till = sb.toString();
                ans = new ArrayList<>(curr);
            }

            curr.remove(curr.size() - 1);
            return;
        }

        for(int v : set[u]){
            if(vis[u][v]) continue;
            vis[u][v] = true;
            dfs(set, v, vis, curr, map, total);
            vis[u][v] = false;
        }


        curr.remove(curr.size() - 1);
    }


}