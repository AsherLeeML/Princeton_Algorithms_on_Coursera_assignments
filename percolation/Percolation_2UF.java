import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation_2UF {
    private final int virtualTop;
    private final int virtualBottom;
    private final int n;

    private final WeightedQuickUnionUF base;
    private final WeightedQuickUnionUF ufForBackWash;


    private boolean[][] grid;
    private int numberOfOpenedSites = 0;


    public Percolation_2UF(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        this.n = n;
        grid = new boolean[n + 1][n + 1];
        // [1-N][1-N]
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++) {
                grid[i][j] = false;
            }
        base = new WeightedQuickUnionUF(n * n + 2);
        ufForBackWash = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = 0;            // an independent node to connect all full nodes.
        virtualBottom = n * n + 1; // to deal with backwash

    }

    private int xyTo1D(int row, int col) {
        return n * (row - 1) + col;
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int p = xyTo1D(row, col);
            if (row == 1) {
                base.union(p, virtualTop);
                ufForBackWash.union(p, virtualTop);
            }
            if (row == n) {
                base.union(p, virtualBottom);
            }
            open(p, row, col);
        }
    }

    private void open(int p, int row, int col) {
        grid[row][col] = true;
        numberOfOpenedSites++;
        if (row > 1 && isOpen(row - 1, col)) {
            base.union(p - n, p);
            ufForBackWash.union(p - n, p);
        }
        if (row < this.n && isOpen(row + 1, col)) {
            base.union(p + n, p);
            ufForBackWash.union(p + n, p);
        }
        if (col < this.n && isOpen(row, col + 1)) {
            base.union(p + 1, p);
            ufForBackWash.union(p + 1, p);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            base.union(p - 1, p);
            ufForBackWash.union(p - 1, p);
        }
    }


    public boolean isOpen(int row, int col) {
        coordCheck(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            int p = xyTo1D(row, col);
            return ufForBackWash.connected(p, virtualTop);
        }
        else
            return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    public boolean percolates() {
        return base.connected(virtualTop, virtualBottom);
    }

    private void coordCheck(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new java.lang.IllegalArgumentException();
    }

    private boolean connected(int p1, int p2, int q1, int q2) {
        int p = xyTo1D(p1, p2);
        int q = xyTo1D(q1, q2);
        return base.connected(p, q);
    }

    public static void main(String[] args) {
        Percolation_2UF test = new Percolation_2UF(2);
        test.open(1, 2);
        test.open(1, 1);
        System.out.println(test.connected(1, 2, 1, 1));
    }
}

