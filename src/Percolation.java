import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final int[][] grid;
	private final int gridSize;
	private final int wqufArraySize;
	private final WeightedQuickUnionUF weightedQuickUnionUF;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Invalid value for n :: " + n);
		}
		grid = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				grid[i][j] = -1;
			}
		}

		gridSize = n;
		wqufArraySize = (n) * (n) + 2;
		weightedQuickUnionUF = new WeightedQuickUnionUF(wqufArraySize);

	}

	public void open(final int i, final int j) {
		if (!validateIndices(i, j)) {
			throw new IndexOutOfBoundsException(String.format(
					"Value of i = %d or j = %d are not within the range 1 and %d inclusive", i, j, gridSize));
		}

		if (!isOpen(i, j)) {
			if (i == 1) {
				weightedQuickUnionUF.union(xyTo1D(i, j), wqufArraySize - 2);
			} 
			
			if (i == gridSize) {
				weightedQuickUnionUF.union(xyTo1D(i, j), wqufArraySize - 1);
			}
			grid[i][j] = 0;
			if (validateIndices(i - 1, j) && isOpen(i - 1, j)) {
				weightedQuickUnionUF.union(xyTo1D(i, j), xyTo1D(i - 1, j));
			}

			if (validateIndices(i, j - 1) && isOpen(i, j - 1)) {
				weightedQuickUnionUF.union(xyTo1D(i, j), xyTo1D(i, j - 1));
			}

			if (validateIndices(i, j + 1) && isOpen(i, j + 1)) {
				weightedQuickUnionUF.union(xyTo1D(i, j), xyTo1D(i, j + 1));
			}

			if (validateIndices(i + 1, j) && isOpen(i + 1, j)) {
				weightedQuickUnionUF.union(xyTo1D(i, j), xyTo1D(i + 1, j));
			}
		}
	}

	private int xyTo1D(int i, int j) {
		return (i - 1) * gridSize + (j - 1);
	}

	private boolean validateIndices(int i, int j) {
		return !(i < 1 || i > gridSize || j < 1 || j > gridSize);
	}

	public boolean isOpen(int i, int j) {
		if (!validateIndices(i, j)) {
			throw new IndexOutOfBoundsException(String.format(
					"Value of i = %d or j = %d are not within the range 1 and %d inclusive", i, j, gridSize));
		}

		return grid[i][j] == 0 ? true : false;

	}

	public boolean isFull(int i, int j) {
		if (!validateIndices(i, j)) {
			throw new IndexOutOfBoundsException(String.format(
					"Value of i = %d or j = %d are not within the range 1 and %d inclusive", i, j, gridSize));
		}

		return weightedQuickUnionUF.connected(xyTo1D(i, j), wqufArraySize - 2);

	}

	public boolean percolates() {
		return weightedQuickUnionUF.connected(wqufArraySize - 2, wqufArraySize - 1);
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(1);
		p.open(1, 1);
		System.out.println(p.percolates());
		System.out.println(p.isOpen(1, 1));
		
		
		
		
		
		 p = new Percolation(3);
		p.open(1, 1);
		p.open(1, 2);
		p.open(2, 2);
		System.out.println("perlocates ??" + p.percolates());
		p.open(2, 3);
		System.out.println("perlocates ??" + p.percolates());
		// p.open(3, 3);
		System.out.println("perlocates ??" + p.percolates());
		p.open(2, 1);
		System.out.println("perlocates ??" + p.percolates());
		p.open(3, 3);
		System.out.println("perlocates ??" + p.percolates());

		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(2, 2), p.xyTo1D(1, 2)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(2, 2), p.xyTo1D(1, 1)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(1, 2), p.xyTo1D(1, 1)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(1, 2), p.xyTo1D(2, 2)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(1, 1), p.xyTo1D(2, 2)));
		System.out.println(p.weightedQuickUnionUF.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 3)));

		System.out.println(p.percolates());

	}
}