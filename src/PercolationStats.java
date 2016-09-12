import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final int experimentsCount;
	private final double perThreshold[];

	public PercolationStats(int n, int trials) {
		experimentsCount = trials;
		perThreshold = new double[trials];
		int openSitesCount = 0;
		// perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Grid Size :: n and number of trials :: trials must be greater than 0");
		}

		Percolation pr = null;

		for (int index = 0; index < trials; index++) {
			openSitesCount = 0;
			pr = new Percolation(n);
			while (true) {
				pr.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
				openSitesCount++;
				if (pr.percolates()) {
					break;
				}
			}

			perThreshold[index] = (double) openSitesCount / (n * n);
		}
	}

	public double mean() {
		return StdStats.mean(perThreshold);
	}

	public double stddev() {
		return StdStats.stddev(perThreshold);
	}

	public double confidenceLo() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}

	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}

	public static void main(String[] args) {
		PercolationStats pstats = new PercolationStats(20, 50);
		System.out.println("mean :: "+pstats.mean());
		System.out.println("stddev :: "+pstats.stddev());
		System.out.println("Confidence interval :: "+ pstats.confidenceLo() + " ,  " + pstats.confidenceHi());
		
		pstats = new PercolationStats(20, 50);
		System.out.println("mean :: "+pstats.mean());
		System.out.println("stddev :: "+pstats.stddev());
		System.out.println("Confidence interval :: "+ pstats.confidenceLo() + " ,  " + pstats.confidenceHi());
		
		pstats = new PercolationStats(20, 50);
		System.out.println("mean :: "+pstats.mean());
		System.out.println("stddev :: "+pstats.stddev());
		System.out.println("Confidence interval :: "+ pstats.confidenceLo() + " ,  " + pstats.confidenceHi());

	}
}