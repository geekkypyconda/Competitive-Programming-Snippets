package AdvancedDataStructures;

import java.util.*;
import java.io.*;
import java.math.*;


public class LongSegmentTree {
    private long inputArr[],LONG_MIN = Long.MIN_VALUE,LONG_MAX = Long.MAX_VALUE;
    private LongSegmentTreeNode stn[];
    private long lazy[];
    private long MOD = Long.MAX_VALUE;
    private boolean lazyMode;
    private int start,end,size,limit;

    public LongSegmentTree(long[] inputArr, int start, int end,boolean mode) {
        super();
        this.inputArr = inputArr;
        this.start = start;
        this.end = end;
        try {
            if(this.end < 0 || this.start < 0)
                throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + end + ", Start : " + start);
            else if(this.end >= inputArr.length || this.start >= inputArr.length)
                throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + end + ", Start : " + start + " Arr.len : " + inputArr.length);
            else if(this.end < this.start )
                throw new ArrayIndexOutOfBoundsException("End < Start  End : " + end + ", Start : " + start);
        }catch(Exception e) {
            System.out.println("Index Exception " + e.getMessage());
            e.printStackTrace();
            return;
        }

        int len = end - start + 1;
        this.size = (4 * len) + 100;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        this.lazyMode = mode;
        buildTree();
    }

    public LongSegmentTree(long[] inputArr,boolean mode) {
        super();
        this.inputArr = inputArr;
        this.start = 0;
        this.end = inputArr.length - 1;
        int len = inputArr.length;
        this.size = (4 * len) + 200;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        this.lazyMode = mode;
        buildTree();
    }

    public LongSegmentTree(long[] inputArr, int start, int end,boolean mode,long mod) {
        super();
        this.inputArr = inputArr;
        this.start = start;
        this.end = end;
        try {
            if(this.end < 0 || this.start < 0)
                throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + end + ", Start : " + start);
            else if(this.end >= inputArr.length || this.start >= inputArr.length)
                throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + end + ", Start : " + start + " Arr.len : " + inputArr.length);
            else if(this.end < this.start )
                throw new ArrayIndexOutOfBoundsException("End < Start  End : " + end + ", Start : " + start);
        }catch(Exception e) {
            System.out.println("Index Exception " + e.getMessage());
            e.printStackTrace();
            return;
        }

        int len = end - start + 1;
        this.size = (4 * len) + 100;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        this.lazyMode = mode;
        this.MOD = mod;
        buildTree();
    }

    public LongSegmentTree(long[] inputArr,boolean mode,long mod) {
        super();
        this.inputArr = inputArr;
        this.start = 0;
        this.end = inputArr.length - 1;
        int len = inputArr.length;
        this.size = (4 * len) + 200;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        this.MOD = mod;
        this.lazyMode = mode;
        buildTree();
    }

    public void printTree()
    {
        for(int x = 0;x <= limit;x++) {
            if(stn[x] == null) {
                computeNull(x);
                continue;
            }

            System.out.println(stn[x].toString());
        }


        System.out.println();
    }


    //Building Tree

    private void buildTree()
    {
        buildTreeUtil(inputArr,stn,start,end,0);
    }


    private void buildTreeUtil(long a[],LongSegmentTreeNode segArr[],int s,int e,int curr)
    {
        if(s > e || curr >= segArr.length)
            return;
        limit = Math.max(limit,curr);
        if(s == e) {
            segArr[curr] = new LongSegmentTreeNode();
            segArr[curr].setMin(a[s]);
            segArr[curr].setMax(a[e]);
            segArr[curr].setSum(a[s]);
            segArr[curr].setIndex(curr);
            segArr[curr].setActive(true);
            return;
        }
        int mid = (s + e) / 2,c = (2 * curr) + 1,d = (2 * curr) + 2;
        buildTreeUtil(a,segArr,s,mid,c);
        buildTreeUtil(a,segArr,mid + 1,e,d);

        LongSegmentTreeNode left = segArr[c];
        LongSegmentTreeNode right = segArr[d];

        long sum = (left.getSum() + right.getSum()) % MOD;
        long min = Math.min(left.getMin(),right.getMin());
        long max = Math.max(left.getMax(),right.getMax());
        LongSegmentTreeNode currNode = new LongSegmentTreeNode(min,max,sum,curr,true);
        segArr[curr] = currNode;
        limit = Math.max(limit,curr);
    }



    //Querying

    public long minQuery(int qs,int qe)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazyMinQueryUtil(stn,start,end,qs,qe,0);
        else
            return minQueryUtil(stn,start,end,qs,qe,0);
    }

    public long minQuery(LongSegmentTreeNode a[],int qs,int qe)
    {

        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazyMinQueryUtil(a,start,end,qs,qe,0);
        else
            return minQueryUtil(a,start,end,qs,qe,0);

    }


    private long minQueryUtil(LongSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return LONG_MAX;
        if(qe < s || qs > e)
            return LONG_MAX;
        if(qs <= s && qe >= e)
            return a[curr].getMin();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        long leftAns = minQueryUtil(a,s,mid,qs,qe,c);
        long rightAns = minQueryUtil(a,mid + 1,e,qs,qe,d);

        return Math.min(leftAns,rightAns);

    }

    public long maxQuery(int qs,int qe)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazyMaxQueryUtil(stn,start,end,qs,qe,0);
        else
            return maxQueryUtil(stn,start,end,qs,qe,0);
    }

    public long maxQuery(LongSegmentTreeNode a[],int qs,int qe)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazyMaxQueryUtil(a,start,end,qs,qe,0);
        else
            return maxQueryUtil(a,start,end,qs,qe,0);
    }


    private long maxQueryUtil(LongSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return LONG_MIN;
        if(qe < s || qs > e)
            return LONG_MIN;
        if(qs <= s && qe >= e)
            return a[curr].getMax();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        long leftAns = maxQueryUtil(a,s,mid,qs,qe,c);
        long rightAns = maxQueryUtil(a,mid + 1,e,qs,qe,d);

        return Math.max(leftAns,rightAns);

    }


    public long sumQuery(int qs,int qe)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazySumQueryUtil(stn,start,end,qs,qe,0);
        else
            return sumQueryUtil(stn,start,end,qs,qe,0);
    }

    public long sumModQuery(int qs,int qe,long mod)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazySumModQueryUtil(stn,start,end,qs,qe,0,mod);
        else
            return sumModQueryUtil(stn,start,end,qs,qe,0,mod);
    }


    public long sumQuery(LongSegmentTreeNode a[],int qs,int qe)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazySumQueryUtil(a,start,end,qs,qe,0);
        else
            return sumQueryUtil(a,start,end,qs,qe,0);
    }

    public long sumModQuery(LongSegmentTreeNode a[],int qs,int qe,long mod)
    {
        if(qe < 0 || qs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + qe + ", Start : " + qs);
        else if(qe >= inputArr.length || qs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + qe + ", Start : " + qs + " Arr.len : " + inputArr.length);
        else if(qe < qs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + qe + ", Start : " + qs);
        else
        if(this.lazyMode)
            return lazySumModQueryUtil(a,start,end,qs,qe,0,mod);
        else
            return sumModQueryUtil(a,start,end,qs,qe,0,mod);
    }


    private long sumQueryUtil(LongSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return 0;
        if(qe < s || qs > e)
            return 0;
        if(qs <= s && qe >= e)
            return a[curr].getSum();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        long leftAns = sumQueryUtil(a,s,mid,qs,qe,c);
        long rightAns = sumQueryUtil(a,mid + 1,e,qs,qe,d);

        return leftAns + rightAns;

    }

    private long sumModQueryUtil(LongSegmentTreeNode a[],int s,int e,int qs,int qe,int curr,long mod)
    {
        if(s > e || curr >= a.length)
            return 0;
        if(qe < s || qs > e)
            return 0;
        if(qs <= s && qe >= e)
            return a[curr].getSum() % mod;

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        long leftAns = sumModQueryUtil(a,s,mid,qs,qe,c,mod);
        long rightAns = sumModQueryUtil(a,mid + 1,e,qs,qe,d,mod);

        return (leftAns + rightAns) % mod;

    }



    //Update
    public void updateNode(int index,long val)
    {
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index :" + index);
        else if(index >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length :: " + " Index : " + index + " Arr.len : " + inputArr.length);
        else {
            this.inputArr[index] = val;
            updateNodeUtil(stn,index,val,start,end,0);
        }
    }

    public void updateNode(LongSegmentTreeNode a[],int index,long val)
    {
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index :" + index);
        else if(index >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length :: " + " Index : " + index + " Arr.len : " + inputArr.length);
        else {
            this.inputArr[index] = val;
            updateNodeUtil(stn,index,val,start,end,0);
        }
    }

    private void updateNodeUtil(LongSegmentTreeNode a[],int index,long val,int s,int e,int curr)
    {
        if(s > e || curr >= a.length || index < s || index > e)
            return;
        if(s == e) {
            a[curr].setMax(val);
            a[curr].setMin(val);
            a[curr].setSum(val);
            return;
        }

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        updateNodeUtil(a,index,val,s,mid,c);
        updateNodeUtil(a,index,val,mid + 1,e,d);
        long min = Math.min(a[c].getMin(),a[d].getMin());
        long max = Math.max(a[c].getMax(),a[d].getMax());
        long sum = (a[c].getSum() + a[d].getSum()) % MOD;

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);
    }

    public void updateRange(int rs,int re,long inc)
    {
        if(re < 0 || rs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + re + ", Start : " + rs);
        else if(re >= inputArr.length || rs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + re + ", Start : " + rs + " Arr.len : " + inputArr.length);
        else if(re < rs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + re + ", Start : " + rs);
        else
        if(this.lazyMode)
            updateRangeLazilyUtil(stn,start,end,rs,re,inc,0);
        else
            updateRangeUtil(stn,start,end,rs,re,inc,0);
    }

    public void updateRange(LongSegmentTreeNode a[] ,int rs,int re,long inc)
    {
        if(re < 0 || rs < 0)
            throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + re + ", Start : " + rs);
        else if(re >= inputArr.length || rs >= inputArr.length)
            throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + re + ", Start : " + rs + " Arr.len : " + inputArr.length);
        else if(re < rs)
            throw new ArrayIndexOutOfBoundsException("End < Start  End : " + re + ", Start : " + rs);
        else
        if(this.lazyMode)
            updateRangeLazilyUtil(a,start,end,rs,re,inc,0);
        else
            updateRangeUtil(a,start,end,rs,re,inc,0);
    }

    private void updateRangeUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,long inc,int curr)
    {
        if(s > e || curr >= a.length)
            return;

        if(re < s || rs > e)
            return;

        if(s == e) {
            a[curr].incMin(inc);
            a[curr].incMax(inc);
            a[curr].incSum(inc);
            return;
        }

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        updateRangeUtil(a,s,mid,rs,re,inc,c);
        updateRangeUtil(a,mid + 1,e,rs,re,inc,d);
        long min = Math.min(a[c].getMin(),a[d].getMin());
        long max = Math.max(a[c].getMax(),a[d].getMax());
        long sum = (a[c].getSum() + a[d].getSum()) % MOD;

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);

    }

    private void updateRangeLazilyUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,long inc,int curr)
    {
        if(s > e || curr >= a.length)
            return;
        if(lazy[curr] != 0) {
            long val = lazy[curr];
            a[curr].incMin(val);
            a[curr].incMax(val);
            a[curr].incSum(val);

            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] = (lazy[c] + val) % MOD;
                lazy[d] = (lazy[d] +  val) % MOD;
            }
            lazy[curr] = 0;
        }

        if(rs > e || re < s)
            return;
        if(rs <= s && re >= e) {
            a[curr].incMin(inc);
            a[curr].incMax(inc);
            a[curr].incSum(inc);
            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] += inc;
                lazy[d] += inc;
            }
            return;
        }

        int c = (2 * curr) + 1,d = c + 1;

        int mid = (s + e) / 2;
        updateRangeLazilyUtil(a,s,mid,rs,re,inc,c);
        updateRangeLazilyUtil(a,mid + 1,e,rs,re,inc,d);

        long min = Math.min(a[c].getMin(),a[d].getMin());
        long max = Math.max(a[c].getMax(),a[d].getMax());
        long sum = (a[c].getSum() + a[d].getSum()) % MOD;

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);
    }


    private long lazyMinQueryUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return LONG_MAX;
        if(lazy[curr] != 0) {
            long val = lazy[curr];
            a[curr].incMin(val);
            a[curr].incMax(val);
            a[curr].incSum(val);

            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] = (lazy[c] + val) % MOD;
                lazy[d] = (lazy[d] +  val) % MOD;
            }
            lazy[curr] = 0;
        }

        if(re < s || rs > e)
            return LONG_MAX;
        if(rs <= s && re >= e)
            return a[curr].getMin();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        long left = lazyMinQueryUtil(a,s,mid,rs,re,c);
        long right = lazyMinQueryUtil(a,mid + 1,e,rs,re,d);

        return Math.min(left,right);
    }

    private long lazyMaxQueryUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return LONG_MIN;
        if(lazy[curr] != 0) {
            long val = lazy[curr];
            a[curr].incMin(val);
            a[curr].incMax(val);
            a[curr].incSum(val);

            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] = (lazy[c] + val) % MOD;
                lazy[d] = (lazy[d] +  val) % MOD;
            }
            lazy[curr] = 0;
        }

        if(re < s || rs > e)
            return LONG_MIN;
        if(rs <= s && re >= e)
            return a[curr].getMax();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        long left = lazyMaxQueryUtil(a,s,mid,rs,re,c);
        long right = lazyMaxQueryUtil(a,mid + 1,e,rs,re,d);

        return Math.max(left,right);
    }


    private long lazySumQueryUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return 0;
        if(lazy[curr] != 0) {
            long val = lazy[curr];
            a[curr].incMin(val);
            a[curr].incMax(val);
            a[curr].incSum(val);

            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] += val;
                lazy[d] += val;
            }
            lazy[curr] = 0;
        }

        if(re < s || rs > e)
            return 0;
        if(rs <= s && re >= e)
            return a[curr].getSum();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        long left = lazySumQueryUtil(a,s,mid,rs,re,c);
        long right = lazySumQueryUtil(a,mid + 1,e,rs,re,d);

        return left + right;
    }


    private long lazySumModQueryUtil(LongSegmentTreeNode a[],int s,int e,int rs,int re,int curr,long mod)
    {
        if(s > e || curr > a.length)
            return 0;
        if(lazy[curr] != 0) {
            long val = lazy[curr];
            a[curr].incMin(val);
            a[curr].incMax(val);
            a[curr].incSum(val);

            if(s != e) {
                int c = (2 * curr) + 1,d = c + 1;
                lazy[c] = (lazy[c] + val) % mod;
                lazy[d] = (lazy[d] + val) % mod;
            }
            lazy[curr] = 0;
        }

        if(re < s || rs > e)
            return 0;
        if(rs <= s && re >= e)
            return a[curr].getSum();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        long left = lazySumModQueryUtil(a,s,mid,rs,re,c,mod);
        long right = lazySumModQueryUtil(a,mid + 1,e,rs,re,d,mod);

        return (left + right) % mod;
    }



    //Other Stuff

    private void computeNull(int i)
    {
        if(stn[i] == null)
            stn[i] = new LongSegmentTreeNode(Integer.MAX_VALUE - 1000,Integer.MIN_VALUE + 1000,Integer.MIN_VALUE + 1000,i,false);
    }

    public long[] getInputArr() {
        return inputArr;
    }

    public void setInputArr(long a[]) {
        this.inputArr = a;
        this.start = 0;
        this.end = a.length - 1;
    }

    public LongSegmentTreeNode[] getSegmentTree() {
        return stn;
    }

    public void setSegmentTree(LongSegmentTreeNode[] stn) {
        this.stn = stn;
        this.size = stn.length;
        this.limit = size;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return limit;
    }

    public void clear(long inputArr[]) {
        this.inputArr = inputArr;
        this.start = 0;
        this.end = inputArr.length - 1;
        int len = inputArr.length;
        this.size = (4 * len) + 200;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        buildTree();
    }

    public void clear(long inputArr[],int start,int end) {
        this.inputArr = inputArr;
        this.start = start;
        this.end = end;
        try {
            if(this.end < 0 || this.start < 0)
                throw new ArrayIndexOutOfBoundsException("Negative Array Index ::  End : " + end + ", Start : " + start);
            else if(this.end >= inputArr.length || this.start >= inputArr.length)
                throw new ArrayIndexOutOfBoundsException("Index > Arr.length ::  End : " + end + ", Start : " + start + " Arr.len : " + inputArr.length);
            else if(this.end < this.start )
                throw new ArrayIndexOutOfBoundsException("End < Start  End : " + end + ", Start : " + start);
        }catch(Exception e) {
            System.out.println("Index Exception " + e.getMessage());
            e.printStackTrace();
            return;
        }

        int len = end - start + 1;
        this.size = (4 * len) + 100;
        this.stn = new LongSegmentTreeNode[size];
        this.lazy = new long[size];
        this.limit = 0;
        buildTree();
    }


    public class LongSegmentTreeNode
    {
        private long min,max,sum,index;
        private boolean isActive;
        public LongSegmentTreeNode() {
            super();
        }

        public LongSegmentTreeNode(long min, long max, long sum,long index,boolean isActive) {
            super();
            this.min = min;
            this.isActive = isActive;
            this.max = max;
            this.sum = sum;
            this.index = index;
        }

        public LongSegmentTreeNode(long min, long max, long sum,long index) {
            super();
            this.min = min;
            this.isActive = true;
            this.max = max;
            this.sum = sum;
            this.index = index;
        }

        public long getMin() {
            return min;
        }

        public void setMin(long min) {
            this.min = min;
        }

        public long getMax() {
            return max;
        }

        public void setMax(long max) {
            this.max = max;
        }

        public long getSum() {
            return sum;
        }

        public void setSum(long sum) {
            this.sum = sum;
        }

        public long getIndex() {
            return index;
        }

        public void setIndex(long index) {
            this.index = index;
        }



        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }


        public void incMin(long val) {
            this.min = (this.min + val) % MOD;
        }

        public void incMax(long val) {
            this.max = (this.max + val) % MOD;
        }
        public void incSum(long val) {
            this.sum = (this.sum + val) % MOD;
        }





        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + (int) (index ^ (index >>> 32));
            result = prime * result + (isActive ? 1231 : 1237);
            result = prime * result + (int) (max ^ (max >>> 32));
            result = prime * result + (int) (min ^ (min >>> 32));
            result = prime * result + (int) (sum ^ (sum >>> 32));
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
            LongSegmentTreeNode other = (LongSegmentTreeNode) obj;
            if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
                return false;
            if (index != other.index)
                return false;
            if (isActive != other.isActive)
                return false;
            if (max != other.max)
                return false;
            if (min != other.min)
                return false;
            if (sum != other.sum)
                return false;
            return true;
        }

        private LongSegmentTree getEnclosingInstance() {
            return LongSegmentTree.this;
        }

        @Override
        public String toString() {
            return "LongTreeNode [min=" + this.min + ", max=" + this.max + ", sum=" + this.sum + ", isActive=" + this.isActive + ", index="
                    + this.index + "]";
        }



    }
}
