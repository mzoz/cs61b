package hw2;
import java.lang.IllegalArgumentException;
import static edu.princeton.cs.introcs.StdRandom.uniform;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t;
    private double[] results;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <=0) throw new IllegalArgumentException();
        t = T;
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int x = uniform(N);
                int y = uniform(N);
                p.open(x, y);
            }
            results[i] = (double) p.numberOfOpenSites() / Math.pow(N, 2);
        }
    }
    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        double u = mean();
        double s = stddev();
        return u - 1.96*s / Math.sqrt(t);
    }

    public double confidenceHigh() {
        double u = mean();
        double s = stddev();
        return u + 1.96*s / Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationStats ps =
                new PercolationStats(100, 1000, new PercolationFactory());
        double cl = ps.confidenceLow();
        double ch = ps.confidenceHigh();
        System.out.printf("est: [%f, %f]\n", cl, ch);
    }
}