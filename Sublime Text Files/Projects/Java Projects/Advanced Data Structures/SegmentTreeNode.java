import java.util.*;
import java.lang.*;
import java.io.*;

class SegmentTreeNode
{
	int min,max,sum,index;
	SegmentTreeNode(){

	}
	SegmentTreeNode(int min,int max,int sum,int index){
		this.min = min;
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

	@Override
	public String toString() {
		return "SegmentTreeNode [min=" + this.min + ", max=" + this.max + ", sum=" + this.sum + ", index="
				+ this.index + "]";
	}
}