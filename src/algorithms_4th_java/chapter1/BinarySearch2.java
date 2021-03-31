package algorithms_4th_java.chapter1;
//递归实现二分查找,1.1.22
public class BinarySearch2 {
	
	public static int rank(int key,int a[]) {
		int lo=0;
		int hi=a.length-1;
		while(lo<=hi) {
			int mid=lo+(hi-lo)/2;
			if(key<a[mid]) hi=mid-1;;
			if(key>a[mid]) lo=mid+1;
			else return mid;
		}
		return -1;
	}
	public static int rank2(int key,int a[],int lo,int hi,int len) { 
		len++;
		System.out.println("lo:"+lo+" hi:"+hi+" len:"+len);
		int mid=lo+(hi-lo)/2;
		if (lo>hi) return -1;
		if(key<a[mid]) return rank2(key, a, lo, mid-1,len);
		if(key>a[mid]) return rank2(key, a, mid+1,hi,len);
		return mid;
	}
	
	public static void main(String[] args) {
		int nums[]={1,2,3,5,6,33,56,442,44455};
		int key=5;int len=0;
		System.out.println(rank2(key, nums, 0, nums.length-1,0));
	}

}
