package personal.algorithms_4th_java.chapter1;
//1.1.35 模拟掷色子
import java.math.BigDecimal;

import edu.princeton.cs.algs4.StdRandom;


public class Sides {

    private static int SIDES = 6;
    
    private double[] dist = new double[2*SIDES + 1];//可能出现的两个骰子之和
    
    public double[] getDist() {
        return dist;
    }

    public void setDist(double[] dist) {
        this.dist = dist;
    }

    public void probability() {//每种两个骰子之和出现的可能性
        for (int i = 1; i <= SIDES; i++) {
           for (int j = 1; j <= SIDES; j++) {
               dist[i + j] += 1.0;
           } 
        }
        for (int k = 2; k <= 2*SIDES; k++) {
            dist[k] /= 36.0;
        }
    }
    
    public void print() {//打印
        for (int i = 0; i < dist.length; i++) {
            System.out.println(
                    String.format("Probability of [%d] is: %f", i, dist[i]));
        }
    }
    
    public static class Emulator {//模仿掷色子的结果
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
                int a = StdRandom.uniform(1, 7);//随机数1~6
                int b = StdRandom.uniform(1, 7);
                dist[a + b] += 1.0;
            }
            for (int k = 2; k <= 2*SIDES; k++) {
                dist[k] /= N;
            }
        }        
        
        public int n(Sides sides) {
            for (int i = 1; i <= 100; i++) {
                this.setN(new Double(Math.pow(10, i)).intValue() * this.N);//以10倍递增N值，直到吻合数据
                this.emulator();
                boolean appr = true;
                for (int k = 2; k <= 2*SIDES; k++) {
                    double s = this.getDist()[k];
                    BigDecimal bs = new BigDecimal(s);
                    double s1 = bs.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();//精确到小数点后三位
                    
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