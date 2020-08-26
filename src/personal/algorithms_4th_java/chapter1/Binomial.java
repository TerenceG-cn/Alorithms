package personal.algorithms_4th_java.chapter1;


//二项分布，1.1.26
public class Binomial {
	private static int count=0;//记录调用次数
	/*递归
	public static double binomial(int N,int k,double p)
	{
		count++;
		if(N==0&&k==0) return 1.0;
		if(N<0||k<0) return 0.0;
		return (1.0-p)*binomial(N-1, k, p)+p*binomial(N-1, k-1, p);
	}
	*/
	
	/*改进的递归
	private static double [][] M;
	public static double binomial(int N,int k,double p)
	{
		count++;
		if(N==0&&k==0) return 1.0;
		if(N<0||k<0) return 0.0;
		if(M[N][k]==-1) M[N][k]=(1.0-p)*binomial(N-1, k, p)+p*binomial(N-1, k-1, p);
		return M[N][k];
	}
	public static double binomial2(int N,int k,double p)
	{	
		M=new double[N+1][k+1];
		for(int i=0;i<N+1;i++)
			for(int j=0;j<k+1;j++)
				M[i][j]=-1;
		return binomial(N, k, p);
	}
	*/
	
    //计算组合数
    public static double combination(double N, double k)
    {
        double min = k;
        double max = N-k;
        double t = 0;
 
        double NN=1;
        double kk=1;
        
        if(min>max){
            t=min;
            min = max;
            max=t;
        }
        
        while(N>max){//分母中较大的那部分阶乘约分不用计算 NN=(n-k+1)(n-k+2)...(n-1)n
            NN=NN*N;
            N--;
        }
        
        while(min>0){//计算较小那部分的阶乘	kk=k!
            kk=kk*min;
            min--;
        }
        
        return NN/kk;
    }
    
    //计算二项分布值
    public static double binomial3(int N,int k,double p)
    {
        double a=1;
        double b=1;
        
        double c =combination(N,k);
        
        while((N-k)>0){  //计算(1-p)的(N-k)次方       
            a=a*(1-p);
            N--;
        }
        
        while(k>0){  //计算p的k次方    
            b=b*p;
            k--;
        }
        
        return c*a*b;
    }

	
	public static void main(String[] args) {
		System.out.println(binomial3(20, 10, 0.25));
		System.out.println("调用次数："+count);
	}
	
}
