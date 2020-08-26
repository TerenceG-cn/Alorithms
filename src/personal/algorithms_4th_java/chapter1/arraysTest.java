package personal.algorithms_4th_java.chapter1;

public class arraysTest {

	public static boolean[][] createN(int N){
		boolean[][] nn=new boolean[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(Euclid.gcd(i,j)==1)
					nn[i][j]=true;
				else nn[i][j]=false;
		return nn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean[][] na=createN(100);
		System.out.println(na[27][3]);
	}

}
