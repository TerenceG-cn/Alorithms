package algorithms_4th_java.chapter1;
//斐波拉契数列非递归求法,1.1.19
public class Fibonacci {
	int fb[]=new int[100];
	public static void F(int N,int fb[])
	{
		if (N==0) fb[N]=0;
		else if (N==1) fb[N]=1;
		else fb[N]=fb[N-1]+fb[N-2];
	}
	public static void main(String[] args) {
		int fb[]=new int[100];
		for(int N=0;N<100;N++) {
			F(N, fb);
			System.out.println(N+" "+fb[N]);
		}
	}
}
