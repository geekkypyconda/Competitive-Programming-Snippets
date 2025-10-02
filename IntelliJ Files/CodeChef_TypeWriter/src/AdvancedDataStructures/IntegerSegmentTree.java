package AdvancedDataStructures;

import java.util.*;
import java.io.*;
import java.math.*;

public class IntegerSegmentTree
{
    private int inputArr[],INT_MIN = Integer.MIN_VALUE,INT_MAX = Integer.MAX_VALUE;
    private IntegerSegmentTreeNode stn[];
    private int lazy[];
    private boolean lazyMode;
    private int start,end,size,limit;

    public IntegerSegmentTree(int[] inputArr, int start, int end,boolean mode) {
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
        this.stn = new IntegerSegmentTreeNode[size];
        this.lazy = new int[size];
        this.limit = 0;
        this.lazyMode = mode;
        buildTree();
    }

    public IntegerSegmentTree(int[] inputArr,boolean mode) {
        super();
        this.inputArr = inputArr;
        this.start = 0;
        this.end = inputArr.length - 1;
        int len = inputArr.length;
        this.size = (4 * len) + 200;
        this.stn = new IntegerSegmentTreeNode[size];
        this.lazy = new int[size];
        this.limit = 0;
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


    private void buildTreeUtil(int a[],IntegerSegmentTreeNode segArr[],int s,int e,int curr)
    {
        if(s > e || curr >= segArr.length)
            return;
        limit = Math.max(limit,curr);
        if(s == e) {
            segArr[curr] = new IntegerSegmentTreeNode();
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

        IntegerSegmentTreeNode left = segArr[c];
        IntegerSegmentTreeNode right = segArr[d];

        int sum = left.getSum() + right.getSum();
        int min = Math.min(left.getMin(),right.getMin());
        int max = Math.max(left.getMax(),right.getMax());
        IntegerSegmentTreeNode currNode = new IntegerSegmentTreeNode(min,max,sum,curr,true);
        segArr[curr] = currNode;
        limit = Math.max(limit,curr);
    }



    //Querying

    public int minQuery(int qs,int qe)
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

    public int minQuery(IntegerSegmentTreeNode a[],int qs,int qe)
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


    private int minQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return INT_MAX;
        if(qe < s || qs > e)
            return INT_MAX;
        if(qs <= s && qe >= e)
            return a[curr].getMin();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        int leftAns = minQueryUtil(a,s,mid,qs,qe,c);
        int rightAns = minQueryUtil(a,mid + 1,e,qs,qe,d);

        return Math.min(leftAns,rightAns);

    }

    public int maxQuery(int qs,int qe)
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

    public int maxQuery(IntegerSegmentTreeNode a[],int qs,int qe)
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


    private int maxQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return INT_MIN;
        if(qe < s || qs > e)
            return INT_MIN;
        if(qs <= s && qe >= e)
            return a[curr].getMax();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        int leftAns = maxQueryUtil(a,s,mid,qs,qe,c);
        int rightAns = maxQueryUtil(a,mid + 1,e,qs,qe,d);

        return Math.max(leftAns,rightAns);

    }


    public int sumQuery(int qs,int qe)
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

    public int sumQuery(IntegerSegmentTreeNode a[],int qs,int qe)
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


    private int sumQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int qs,int qe,int curr)
    {
        if(s > e || curr >= a.length)
            return 0;
        if(qe < s || qs > e)
            return 0;
        if(qs <= s && qe >= e)
            return a[curr].getSum();

        int mid = (s + e) / 2;
        int c = (curr * 2) + 1,d = c + 1;

        int leftAns = sumQueryUtil(a,s,mid,qs,qe,c);
        int rightAns = sumQueryUtil(a,mid + 1,e,qs,qe,d);

        return leftAns + rightAns;

    }



    //Update
    public void updateNode(int index,int val)
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

    public void updateNode(IntegerSegmentTreeNode a[],int index,int val)
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

    private void updateNodeUtil(IntegerSegmentTreeNode a[],int index,int val,int s,int e,int curr)
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
        int min = Math.min(a[c].getMin(),a[d].getMin());
        int max = Math.max(a[c].getMax(),a[d].getMax());
        int sum = a[c].getSum() + a[d].getSum();

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);
    }

    public void updateRange(int rs,int re,int inc)
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

    public void updateRange(IntegerSegmentTreeNode a[] ,int rs,int re,int inc)
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

    private void updateRangeUtil(IntegerSegmentTreeNode a[],int s,int e,int rs,int re,int inc,int curr)
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
        int min = Math.min(a[c].getMin(),a[d].getMin());
        int max = Math.max(a[c].getMax(),a[d].getMax());
        int sum = a[c].getSum() + a[d].getSum();

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);

    }

    private void updateRangeLazilyUtil(IntegerSegmentTreeNode a[],int s,int e,int rs,int re,int inc,int curr)
    {
        if(s > e || curr >= a.length)
            return;
        if(lazy[curr] != 0) {
            int val = lazy[curr];
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

        int min = Math.min(a[c].getMin(),a[d].getMin());
        int max = Math.max(a[c].getMax(),a[d].getMax());
        int sum = a[c].getSum() + a[d].getSum();

        a[curr].setMin(min);
        a[curr].setMax(max);
        a[curr].setSum(sum);
    }


    private int lazyMinQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return INT_MAX;
        if(lazy[curr] != 0) {
            int val = lazy[curr];
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
            return INT_MAX;
        if(rs <= s && re >= e)
            return a[curr].getMin();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        int left = lazyMinQueryUtil(a,s,mid,rs,re,c);
        int right = lazyMinQueryUtil(a,mid + 1,e,rs,re,d);

        return Math.min(left,right);
    }

    private int lazyMaxQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return INT_MIN;
        if(lazy[curr] != 0) {
            int val = lazy[curr];
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
            return INT_MIN;
        if(rs <= s && re >= e)
            return a[curr].getMax();

        int c = (2 * curr) + 1,d = c + 1;
        int mid = (s + e) / 2;

        int left = lazyMaxQueryUtil(a,s,mid,rs,re,c);
        int right = lazyMaxQueryUtil(a,mid + 1,e,rs,re,d);

        return Math.max(left,right);
    }


    private int lazySumQueryUtil(IntegerSegmentTreeNode a[],int s,int e,int rs,int re,int curr)
    {
        if(s > e || curr > a.length)
            return 0;
        if(lazy[curr] != 0) {
            int val = lazy[curr];
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

        int left = lazySumQueryUtil(a,s,mid,rs,re,c);
        int right = lazySumQueryUtil(a,mid + 1,e,rs,re,d);

        return left + right;
    }



    //Other Stuff

    private void computeNull(int i)
    {
        if(stn[i] == null)
            stn[i] = new IntegerSegmentTreeNode(Integer.MAX_VALUE - 1000,Integer.MIN_VALUE + 1000,Integer.MIN_VALUE + 1000,i,false);
    }

    public int[] getInputArr() {
        return inputArr;
    }

    public void setInputArr(int a[]) {
        this.inputArr = a;
        this.start = 0;
        this.end = a.length - 1;
    }

    public IntegerSegmentTreeNode[] getSegmentTree() {
        return stn;
    }

    public void setSegmentTree(IntegerSegmentTreeNode[] stn) {
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

    public void clear(int inputArr[]) {
        this.inputArr = inputArr;
        this.start = 0;
        this.end = inputArr.length - 1;
        int len = inputArr.length;
        this.size = (4 * len) + 200;
        this.stn = new IntegerSegmentTreeNode[size];
        this.lazy = new int[size];
        this.limit = 0;
        buildTree();
    }

    public void clear(int inputArr[],int start,int end) {
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
        this.stn = new IntegerSegmentTreeNode[size];
        this.lazy = new int[size];
        this.limit = 0;
        buildTree();
    }


    public class IntegerSegmentTreeNode
    {
        private int min,max,sum,index;
        private boolean isActive;
        public IntegerSegmentTreeNode() {
            super();
        }

        public IntegerSegmentTreeNode(int min, int max, int sum,int index,boolean isActive) {
            super();
            this.min = min;
            this.isActive = isActive;
            this.max = max;
            this.sum = sum;
            this.index = index;
        }

        public IntegerSegmentTreeNode(int min, int max, int sum,int index) {
            super();
            this.min = min;
            this.isActive = true;
            this.max = max;
            this.sum = sum;
            this.index = index;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }



        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }


        public void incMin(int val) {
            this.min += val;
        }

        public void incMax(int val) {
            this.max += val;
        }
        public void incSum(int val) {
            this.sum += val;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getEnclosingInstance().hashCode();
            result = prime * result + index;
            result = prime * result + (isActive ? 1231 : 1237);
            result = prime * result + max;
            result = prime * result + min;
            result = prime * result + sum;
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
            IntegerSegmentTreeNode other = (IntegerSegmentTreeNode) obj;
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

        private IntegerSegmentTree getEnclosingInstance() {
            return IntegerSegmentTree.this;
        }

        @Override
        public String toString() {
            return "SegmentTreeNode [min=" + this.min + ", max=" + this.max + ", sum=" + this.sum + ", isActive=" + this.isActive + ", index="
                    + this.index + "]";
        }



    }
}

