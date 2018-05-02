package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Percolation {
    private WeightedQuickUnionUF system;
    private boolean[][] sites;
    private int n;
    private boolean p;
    private final int N;
    private final int TOP;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("** illegal size input **");
        n = 0;
        TOP = 0;
        this.N = N;
        system = new WeightedQuickUnionUF(N*N + 1);
        sites = new boolean[N][N];
    }

    private int two2one(int x, int y) {
        int n = N*x + y;
        int offset = 1;
        return n + offset;
    }

    private boolean valid(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < N);
    }

    private void validate(int x, int y) {
        if (!valid(x, y))
            throw new IndexOutOfBoundsException("** site position error **");
    }

    public void open(int row, int col) {
        validate(row, col);
        if (sites[row][col])
            return;
        sites[row][col] = true;
        n += 1;
        update(row, col);
    }

    private void update(int x, int y) {
        int pos = two2one(x, y);
        if (x == 0) system.union(two2one(x, y), TOP);

        if (valid(x-1, y) && isOpen(x-1, y))
            system.union(two2one(x-1, y), pos);
        if (valid(x+1, y) && isOpen(x+1, y))
            system.union(two2one(x+1, y), pos);
        if (valid(x, y-1) && isOpen(x, y-1))
            system.union(two2one(x, y-1), pos);
        if (valid(x, y+1) && isOpen(x, y+1))
            system.union(two2one(x, y+1), pos);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        return system.connected(TOP, two2one(row, col));
    }

    public int numberOfOpenSites() {
        return n;
    }

    public boolean percolates() {
        if (p) return true;
        for (int i = 0; i < N; i++) {
            if (system.connected(two2one(N-1, i), TOP))
                return true;
        }
        return false;
    }
}
