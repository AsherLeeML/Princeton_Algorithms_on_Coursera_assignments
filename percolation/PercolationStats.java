import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
        double[] thresholdTrials = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation trial = new Percolation(n);
            int runs = 0;
            while (!trial.percolates()) {
                int row, col;
                do {
                    col = StdRandom.uniform(1, n + 1);
                    row = StdRandom.uniform(1, n + 1);
                } while (trial.isOpen(row, col));
                trial.open(row, col);
                runs++;
            }
            thresholdTrials[i] = runs / (double) (n * n);

        }
        mean = StdStats.mean(thresholdTrials);
        stddev = StdStats.stddev(thresholdTrials);
        double confidenceFraction = (1.96 * stddev) / Math.sqrt(trials);
        confidenceLo = mean - confidenceFraction;
        confidenceHi = mean + confidenceFraction;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, t);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println(
                "95% confidence interval = [" + stats.confidenceLo() + "," + stats.confidenceHi()
                        + "]");
    }
}
