package AdvancedDataStructures;

import java.util.Arrays;

public class LongFenwickTree {

    private long bit[],tempBit[],inputArr[];
    private int treeSize,MAX_SIZE;
    private long MOD;

    public LongFenwickTree(int size) {
        this.treeSize = size;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.inputArr = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
    }

    public LongFenwickTree(long[] input) {
        super();
        this.inputArr = input;
        this.treeSize = input.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

    public LongFenwickTree(long[] input,int s,int e) {
        super();
        if(!checkIndex(input,s,e))
            this.throwDoubleException(input,s,e);

        this.inputArr = Arrays.copyOfRange(input,s,e + 1);
        this.treeSize = inputArr.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

    public LongFenwickTree(int size,long mod) {
        this.treeSize = size;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.inputArr = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        this.MOD = mod;
    }

    public LongFenwickTree(long[] input,long mod) {
        super();
        this.inputArr = input;
        this.treeSize = input.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        this.MOD = mod;
        buildTree();
    }

    public LongFenwickTree(long[] input,int s,int e,long mod) {
        super();
        if(!checkIndex(input,s,e))
            this.throwDoubleException(input,s,e);

        this.inputArr = Arrays.copyOfRange(input,s,e + 1);
        this.treeSize = inputArr.length;
        this.MAX_SIZE = treeSize + 10;
        this.MOD = mod;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

    private void buildTree()
    {
        for(int x = 0;x < treeSize;x++)
            this.updateRange(x,x,this.inputArr[x]);
    }

    public void update(int index,long val)
    {
        this.updateRange(index,index,val);
    }

    public void updateRange(int l,int r,long val)
    {
        if(!this.checkIndex(this.inputArr,l,r))
            this.throwDoubleException(this.inputArr,l,r);

        this.updateUtil(this.bit,l,val);
        this.updateUtil(this.bit,r + 1,-val);

        this.updateUtil(this.tempBit,l,val * (l - 1));
        this.updateUtil(this.tempBit,r + 1,-val * r);
    }

    private void updateUtil(long tree[],int index,long val)
    {
        index++;
        while(index <= treeSize) {
            tree[index] = (tree[index] + val) % MOD;
            index += index & (-index);
        }
    }

    public long sumQuery(int index)
    {
        if(!this.checkIndex(this.inputArr,index))
            this.throwSingleException(index);
        return this.sumQueryUtil(index);
    }

    public long sumModQuery(int index,long mod)
    {
        if(!this.checkIndex(this.inputArr,index))
            this.throwSingleException(index);
        return this.sumModQueryUtil(index,mod) % mod;
    }

    public long sumQuery(int l,int r)
    {
        if(!this.checkIndex(this.inputArr,l,r))
            this.throwDoubleException(this.inputArr,l,r);
        return (this.sumQueryUtil(r) - this.sumQueryUtil(l - 1));
    }

    public long sumModQuery(int l,int r,long mod)
    {
        if(!this.checkIndex(this.inputArr,l,r))
            this.throwDoubleException(this.inputArr,l,r);
        return (this.sumModQueryUtil(r,mod) % mod - this.sumModQueryUtil(l - 1,mod) % mod) % mod;
    }

    private long sumQueryUtil(int index)
    {
        return (this.sumQueryDoubleUtil(this.bit,index) * index) - this.sumQueryDoubleUtil(this.tempBit,index);
    }

    private long sumModQueryUtil(int index,long mod)
    {
        return ((this.sumModQueryDoubleUtil(this.bit,index,mod) % mod * index) % mod - this.sumModQueryDoubleUtil(this.tempBit,index,mod) % mod) % mod;
    }

    private long sumQueryDoubleUtil(long tree[],int index)
    {
        index++;long sum = 0;
        while(index > 0) {
            sum = (sum + tree[index]) % MOD;
            index -= index & (-index);
        }

        return sum;
    }

    private long sumModQueryDoubleUtil(long tree[],int index,long mod)
    {
        index++;long sum = 0;
        while(index > 0) {
            sum = (sum + tree[index]) % mod;
            index -= index & (-index);
        }

        return sum;
    }


    private boolean checkIndex(long a[],int s,int e)
    {
        if(s >= 0 && e >= 0 && s < a.length && e < a.length && s <= e)
            return true;
        return false;
    }
    private boolean checkIndex(long a[],int index)
    {
        if(index >= 0 && index < a.length)
            return true;
        return false;
    }

    private void throwSingleException(int index)
    {
        throw new ArrayIndexOutOfBoundsException("Index :  " + index + " for array length : " + this.inputArr.length);
    }

    private void throwDoubleException(long input[],int s,int e)
    {
        throw new ArrayIndexOutOfBoundsException("Index Start : " + s + " End : " + e + " for array length : " + input.length);
    }

    public long[] getBit() {
        return bit;
    }

    public long[] getTempBit() {
        return tempBit;
    }

    public long[] getInputArr() {
        return inputArr;
    }

    public void setInputArr(long[] input) {
        this.inputArr = input;
        this.treeSize = input.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

    public void setInputArr(long[] input,int s,int e) {
        if(!checkIndex(input,s,e))
            this.throwDoubleException(input,s,e);

        this.inputArr = Arrays.copyOfRange(inputArr,s,e + 1);
        this.treeSize = inputArr.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

    public int getTreeSize() {
        return treeSize;
    }

    public void clear()
    {
        this.MAX_SIZE = treeSize + 10;
        this.bit = new long[MAX_SIZE];
        this.tempBit = new long[MAX_SIZE];
        buildTree();
    }

}
