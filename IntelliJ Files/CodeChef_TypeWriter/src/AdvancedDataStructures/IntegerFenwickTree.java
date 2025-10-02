package AdvancedDataStructures;

import java.util.*;
import java.io.*;
import java.math.*;

public class IntegerFenwickTree {
    private int bit[],tempBit[],inputArr[],treeSize,MAX_SIZE;

    public IntegerFenwickTree(int size) {
        this.treeSize = size;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.inputArr = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
    }

    public IntegerFenwickTree(int[] input) {
        super();
        this.inputArr = input;
        this.treeSize = input.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
        buildTree();
    }

    public IntegerFenwickTree(int[] input,int s,int e) {
        super();
        if(!checkIndex(input,s,e))
            this.throwDoubleException(input,s,e);

        this.inputArr = Arrays.copyOfRange(input,s,e + 1);
        this.treeSize = inputArr.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
        buildTree();
    }

    private void buildTree()
    {
        for(int x = 0;x < treeSize;x++)
            this.updateRange(x,x,this.inputArr[x]);
    }

    public void update(int index,int val)
    {
        this.updateRange(index,index,val);
    }

    public void updateRange(int l,int r,int val)
    {
        if(!this.checkIndex(this.inputArr,l,r))
            this.throwDoubleException(this.inputArr,l,r);

        this.updateUtil(this.bit,l,val);
        this.updateUtil(this.bit,r + 1,-val);

        this.updateUtil(this.tempBit,l,val * (l - 1));
        this.updateUtil(this.tempBit,r + 1,-val * r);
    }

    private void updateUtil(int tree[],int index,int val)
    {
        index++;
        while(index <= treeSize) {
            tree[index] += val;
            index += index & (-index);
        }
    }

    public int sumQuery(int index)
    {
        if(!this.checkIndex(this.inputArr,index))
            this.throwSingleException(index);
        return this.sumQueryUtil(index);
    }

    public int sumQuery(int l,int r)
    {
        if(!this.checkIndex(this.inputArr,l,r))
            this.throwDoubleException(this.inputArr,l,r);
        return (this.sumQueryUtil(r) - this.sumQueryUtil(l - 1));
    }

    private int sumQueryUtil(int index)
    {
        return (this.sumQueryDoubleUtil(this.bit,index) * index) - this.sumQueryDoubleUtil(this.tempBit,index);
    }

    private int sumQueryDoubleUtil(int tree[],int index)
    {
        index++;int sum = 0;
        while(index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }

        return sum;
    }

    private boolean checkIndex(int a[],int s,int e)
    {
        if(s >= 0 && e >= 0 && s < a.length && e < a.length && s <= e)
            return true;
        return false;
    }
    private boolean checkIndex(int a[],int index)
    {
        if(index >= 0 && index < a.length)
            return true;
        return false;
    }

    private void throwSingleException(int index)
    {
        throw new ArrayIndexOutOfBoundsException("Index :  " + index + " for array length : " + this.inputArr.length);
    }

    private void throwDoubleException(int input[],int s,int e)
    {
        throw new ArrayIndexOutOfBoundsException("Index Start : " + s + " End : " + e + " for array length : " + input.length);
    }

    public int[] getBit() {
        return bit;
    }

    public int[] getTempBit() {
        return tempBit;
    }

    public int[] getInputArr() {
        return inputArr;
    }

    public void setInputArr(int[] input) {
        this.inputArr = input;
        this.treeSize = input.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
        buildTree();
    }

    public void setInputArr(int[] input,int s,int e) {
        if(!checkIndex(input,s,e))
            this.throwDoubleException(input,s,e);

        this.inputArr = Arrays.copyOfRange(inputArr,s,e + 1);
        this.treeSize = inputArr.length;
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
        buildTree();
    }

    public int getTreeSize() {
        return treeSize;
    }

    public void clear()
    {
        this.MAX_SIZE = treeSize + 10;
        this.bit = new int[MAX_SIZE];
        this.tempBit = new int[MAX_SIZE];
        buildTree();
    }
}
