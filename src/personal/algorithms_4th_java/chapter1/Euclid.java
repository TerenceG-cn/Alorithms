package personal.algorithms_4th_java.chapter1;
//ŷ������㷨�����Լ����
public class Euclid {

	public static int gcd(int p,int q) {
		//System.out.println("p:"+p+" q:"+q);
		if(q==0) return p;
		int r=p%q;
		return gcd(q, r);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(1111111, 1234567));
	}

}
