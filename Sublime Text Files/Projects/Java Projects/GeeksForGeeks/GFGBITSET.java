import java.util.*;

class GFGBITSET
{
	static final int MOD = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0)
		{
			int n = sc.nextInt();
			int a[] = new int[n];
			for(int x = 0;x < n;x++)
				a[x] = sc.nextInt();
			System.out.println(bitSet(a));
		}
	}
	public static int maxSubarrayXOR(int set[], int n)
    {
        int index = 0;
        for(int x = 31;x >= 0;x--){
            int maxIdx = index;
            int maxEle = Integer.MIN_VALUE;
            
            for(int j = index;j < n;j++)
                if((set[j] & (1 << x)) != 0 && set[j] > maxEle)
                {
                    maxEle = set[j];
                    maxIdx = j;
                }
            if(maxEle == -2147483648)
                continue;
            
            int temp = set[index];
            set[index] = set[maxIdx];
            set[maxIdx] = temp;
            maxIdx = index;
            
            for(int j = 0;j < n;j++)
                if(j != maxIdx && (set[j] & (1 << x)) != 0)
                    set[j] = set[j] ^ set[maxIdx];
            
            index++;
        }
         
        int res = 0;
        for(int x = 0;x < n;x++)
            res ^= set[x];
        return res;
    }
	public static String bitSet(int a[])
	{
		int index = 0,n = a.length;
		for(int i = 31;i >= 0;i--)
		{
			int maxEle = Integer.MIN_VALUE;
			int maxIdx = index;
			for(int j = index;j < n;j++)
				if((a[j] & (1 << i)) != 0 && a[j] > maxEle){
					maxEle = a[j];
					maxIdx = j;
				}
			if(maxEle == -2147483648)	
				continue;

			int temp = a[index];
			a[index] = a[maxIdx];
			a[maxIdx] = temp;
			maxIdx = index;

			for(int j = 0;j < n;j++)
				if(j != maxIdx && (a[j] & (1 << i)) != 0)
					a[j] = a[j] ^ a[maxIdx];
			index++;		
		}

		int ans = 0;
		for(int x =0 ;x < n;x++)
			ans ^= a[x];
		return ans + "";
	}
	public static long getPower(long n)
	{
		int p = 0;long num = 0;
		while(num <= n)
			num = (long)Math.pow(2,p++);
		return num / 2;
	}	
}