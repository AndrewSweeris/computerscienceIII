import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
   private double[] ary;
   private double mean;
   private double stddev;
   private double confidenceLo;
   private double confidenceHigh;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
      if (n<=0 || trials <= 0) throw new IllegalArgumentException();
      trials = trials;
      ary = new double[trials];
      for (int i = 0; i < trials; i++) {
         Percolation p = new Percolation(n);
         while (!p.percolates()) {
            p.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
         }
         ary[i] = p.numberOfOpenSites()/Math.pow(n,2);
      }
      mean = mean();
      stddev = stddev();
      confidenceLo = confidenceLo();
      confidenceHigh = confidenceHi();
   }
   public double mean()                          // sample mean of percolation threshold
   {
      return StdStats.mean(ary);
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
      return StdStats.stddev(ary);
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
      return mean - ((1.96 * stddev) / Math.sqrt(ary.length));
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
      return mean + ((1.96 * stddev) / Math.sqrt(ary.length));
   }
   public static void main(String[] args)    // test client (described below)
   {
    int n = Integer.parseInt(args[0]);         // n-by-n percolation system
    int T = Integer.parseInt(args[1]);		 	// T trials
    PercolationStats ps = new PercolationStats(n,T);
    System.out.println("mean\t\t\t\t\t= " + ps.mean);
      System.out.println("stddev\t\t\t\t\t= " + ps.stddev);
      System.out.println("95% confidence interval = [" + ps.confidenceLo + ", " + ps.confidenceHigh + "]");
   }
}