import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
//    double a  = Math.sqrt();
    private int N;
    private int trials;
    private double mean;
    private double stddev;
    private double  double_numbers_of_opensite;
    private double confidenceHi;
    private double confidenceLo;
    public double mean()
    {
        return mean;
    }
    public double stddev()
    {
        return  stddev;
    }
    public double confidenceHi(){return confidenceHi;}
    public double confidenceLo(){return confidenceLo;}
    public  PercolationStats(int N,int trials) {
        this.N=N;
        validate_N(N);
        validate_T(trials);
        this.confidenceHi=0;
        this.trials = trials;
        this.mean = 0;
        this.stddev = 0;
        double[] data=new double[trials];
        for(int t=0;t<trials;t++)
        {
            Percolation perco = new Percolation(N);
            int r1;
            int[] r2 = new int[2];
            int buffer;
            outer:
            while (true) {
                r1 = StdRandom.uniformInt(N * N);
                r2 = maps1d_to_2d(r1);
                perco.open(r2[0], r2[1]);
                if (perco.percolates()) {
                    break ;
                }
            }

            this.double_numbers_of_opensite= perco.numberOfOpenSites();
            data[t]=double_numbers_of_opensite/(N*N);
        }
        this.mean=StdStats.mean(data);
        this.stddev=StdStats.stddev(data);
        this.confidenceHi= this.mean +((1.96*this.stddev)/Math.sqrt(trials));
        this.confidenceLo=this.mean-((1.96*this.stddev)/Math.sqrt(trials));
    }

    private int maps2d_to_1d(int x,int y)
    {
        int result=N*(x-1)+(y-1);
        return result;
    }
    private int[] maps1d_to_2d(int result)
    {
        int[] a=new int[2];
        a[0]=(result/(N))+1;
        a[1]=(result%(N))+1;
        return  a;
    }



    public static void main(String[] args)
    {
            int trials=Integer.parseInt(args[1]);
            int N= Integer.parseInt(args[0]);
          PercolationStats result =new PercolationStats(N,trials);
        StdOut.printf("mean                     = %f\n",result.mean);
        StdOut.printf("stddev                   = %f\n",result.stddev);
        StdOut.printf("95%% confidence interval  =[%f,%f]\n", result.confidenceLo,result.confidenceHi);
//        System.out.printf("95% confidence interval  = %f\n", 9.0);

    }
    private void validate_N(int index) {

        if (index <= 0){
            throw new IllegalArgumentException("index " + N + " is not correct");
        }
    }
    private void validate_T(int T) {

        if (T <= 0){
            throw new IllegalArgumentException("numbers of trials " + T + " is not correct");
        }
    }

}



