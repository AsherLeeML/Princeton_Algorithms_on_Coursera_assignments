import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;

    private final WeightedQuickUnionUF uf;

    private byte[] gridState;
    private boolean percolate;
    private int numberOfOpenedSites = 0;
    private final int virtualTop;


    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();
        this.n = n;
        gridState = new byte[n * n + 1];
        for (int i = 0; i <= n * n; i++) {
            gridState[i] = 0;
        }
        uf = new WeightedQuickUnionUF(n * n + 1);
        virtualTop = n * n;
        this.percolate = false;
    }

    private int xyTo1D(int row, int col) {
        return n * (row - 1) + col - 1;
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            int p = xyTo1D(row, col);
            numberOfOpenedSites++;
            gridState[p] = 1;
            if (row == 1) {
                uf.union(p, virtualTop);
            }
            if (row == n) {
                gridState[p] = 2;
            }
            // up
            if (row > 1 && gridState[p - n] > 0) {

                int pUp = uf.find(p - n);
                int pCur = uf.find(p);
                uf.union(p, p - n);
                if (gridState[pUp] == 2
                        || gridState[pCur] == 2) {
                    gridState[pUp] = 2;
                    gridState[pCur] = 2;
                }
            }
            // down
            if (row < n && gridState[p + n] > 0) {

                int pDown = uf.find(p + n);
                int pCur = uf.find(p);
                uf.union(p, p + n);
                if (gridState[pDown] == 2
                        || gridState[pCur] == 2) {
                    gridState[pDown] = 2;
                    gridState[pCur] = 2;
                }
            }
            // right
            if (col < n && gridState[p + 1] > 0) {

                int pRight = uf.find(p + 1);
                int pCur = uf.find(p);
                uf.union(p, p + 1);
                if (gridState[pRight] == 2
                        || gridState[pCur] == 2) {
                    gridState[pRight] = 2;
                    gridState[pCur] = 2;
                }
            }
            // left
            if (col > 1 && gridState[p - 1] > 0) {

                int pLeft = uf.find(p - 1);
                int pCur = uf.find(p);
                uf.union(p, p - 1);
                if (gridState[pLeft] == 2
                        || gridState[pCur] == 2) {
                    gridState[pLeft] = 2;
                    gridState[pCur] = 2;
                }
            }

            if (gridState[uf.find(virtualTop)] == 2)
                this.percolate = true;
        }
    }


    public boolean isOpen(int row, int col) {
        coordCheck(row, col);
        return gridState[xyTo1D(row, col)] > 0;
    }

    public boolean isFull(int row, int col) {
        coordCheck(row, col);
        return uf.connected(xyTo1D(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return numberOfOpenedSites;
    }

    public boolean percolates() {
        return percolate;
    }

    private void coordCheck(int row, int col) {
        if (row > n || col > n || row <= 0 || col <= 0)
            throw new java.lang.IllegalArgumentException();
    }

    private boolean connected(int p1, int p2, int q1, int q2) {
        int p = xyTo1D(p1, p2);
        int q = xyTo1D(q1, q2);
        return uf.connected(p, q);
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(2);
        test.open(1, 2);
        test.open(1, 1);
        System.out.println(test.connected(1, 2, 1, 1));
    }
}

