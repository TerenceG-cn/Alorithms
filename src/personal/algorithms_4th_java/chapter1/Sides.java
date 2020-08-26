package personal.algorithms_4th_java.chapter1;
//1.1.35 ģ����ɫ��
import java.math.BigDecimal;

import edu.princeton.cs.algs4.StdRandom;


public class Sides {

    private static int SIDES = 6;
    
    private double[] dist = new double[2*SIDES + 1];//���ܳ��ֵ���������֮��
    
    public double[] getDist() {
        return dist;
    }

    public void setDist(double[] dist) {
        this.dist = dist;
    }

    public void probability() {//ÿ����������֮�ͳ��ֵĿ�����
        for (int i = 1; i <= SIDES; i++) {
           for (int j = 1; j <= SIDES; j++) {
               dist[i + j] += 1.0;
           } 
        }
        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= 36.0;
        }
    }
    
    public void print() {//��ӡ
        for (int i = 0; i < dist.length; i++) {
            System.out.println(
                    String.format("Probability of [%d] is: %f", i, dist[i]));
        }
    }
    
    public static class Emulator {//ģ����ɫ�ӵĽ��
        private int N = 100;
        
        private double[] dist = new double[2*SIDES + 1];
        
        public int getN() {
            return N;
        }

        public void setN(int n) {
            N = n;
        }

        public double[] getDist() {
            return dist;
        }

        public void setDist(double[] dist) {
            this.dist = dist;
        }

        public void emulator() {
            for (int i = 0; i < N; i++) {
                int a = StdRandom.uniform(1, 7);//�����1~6
                int b = StdRandom.uniform(1, 7);
                dist[a + b] += 1.0;
            }
            for (int k = 2; k <= 2*SIDES; k++) {
                dist[k] /= N;
            }
        }        
        
        public int n(Sides sides) {
            for (int i = 1; i <= 100; i++) {
                this.setN(new Double(Math.pow(10, i)).intValue() * this.N);//��10������Nֵ��ֱ���Ǻ�����
                this.emulator();
                boolean appr = true;
                for (int k = 2; k <= 2*SIDES; k++) {
                    double s = this.getDist()[k];
                    BigDecimal bs = new BigDecimal(s);
                    double s1 = bs.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();//��ȷ��С�������λ
                    
                    double t = sides.getDist()[k];
                    BigDecimal bt = new BigDecimal(t);
                    double t1 = bt.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                    if (s1 != t1) {
                        appr = false;
                        break;
                    }
                }
                if (appr) {
                    return this.getN();   
                }
            }
            return 0;
        }
        
        public void print() {
            for (int i = 0; i < dist.length; i++) {
                System.out.println(
                        String.format("Probability of [%d] is: %f", i, dist[i]));
            }
        }
        
        
    }
    
    public static void main(String[] args) {
        Sides sides = new Sides();
        sides.probability();

        Emulator e = new Emulator();
        int N = e.n(sides);
        System.out.println(String.format("The N is: %d", N));
        System.out.println("Actual: ");
        sides.print();
        System.out.println("Experiment: ");
        e.print();
    }

}