package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean found = false;
    private Queue<Integer> q;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        q = new Queue<>();
    }

    private void bfs() {
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int w = q.dequeue();
            if (w == t) {
                return;
            }
            for (int u : maze.adj(w)) {
                if (!marked[u]) {
                    edgeTo[u] = w;
                    distTo[u] = distTo[w] + 1;
                    marked[u] = true;
                    announce();
                    q.enqueue(u);
                }
            }
        }
    }

    @Override
    public void solve() {
        bfs();
    }
}

