package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int m = 2; // units of giant hexagon size
    private static final int n = 6; // units of small hexagon size
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object p) {
            if (p instanceof Pair) {
                return x == ((Pair) p).x && y == ((Pair) p).y;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int w = width(m, n);
        int h = height(m, n);
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);

        TETile[][] hexWorld = new TETile[w][h];
        List<Pair> grid = gridGenerator(m, n);
        drawDefault(hexWorld, w, h);
        draw(hexWorld, grid, n);

        ter.renderFrame(hexWorld);
    }

    private static int width(int m, int n) {
        return 4*m*n - n - 2*m;
    }

    private static int height(int m, int n) {
        return 4*m*n - 2*n;
    }



    /** return a grid of constituting hexagons' locale
     *  use recursion maybe?
     */
    private static List<Pair> gridGenerator(int m, int n) {
        List<Pair> grid = new ArrayList<>();

        int x0 = (m - 1) * (2*n - 1);
        int y0 = (m - 1) * (2*n);
        Pair o = new Pair(x0, y0);
        gridCalc(grid, o, n, m);
        return grid;
    }

    private static void gridCalc(List<Pair> grid, Pair centre, int n, int level) {
        if (level == 0) {
            return;
        }

        markPoint(grid, centre);

        int x = centre.x;
        int y = centre.y;

        Pair top = new Pair(x, y + 2*n);
        Pair bottom = new Pair(x, y - 2*n);
        Pair leftTop = new Pair(x - (2*n-1), y + n);
        Pair leftBottom = new Pair(x - (2*n-1), y - n);
        Pair rightTop = new Pair(x + (2*n-1), y + n);
        Pair rightBottom = new Pair(x + (2*n-1), y - n);

        gridCalc(grid, top, n, level - 1);
        gridCalc(grid, bottom, n, level - 1);
        gridCalc(grid, leftTop, n, level - 1);
        gridCalc(grid, leftBottom, n, level - 1);
        gridCalc(grid, rightTop, n, level - 1);
        gridCalc(grid, rightBottom, n, level - 1);
    }

    private static void markPoint(List<Pair> grid, Pair point) {
        if (!grid.contains(point)) {
            grid.add(point);
        }
    }



    /** Draw hexagons
     *
     */
    private static void drawDefault(TETile[][] world, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private static void draw(TETile[][] world, List<Pair> grid, int n) {
        for (Pair point : grid) {
            drawHex(world, point, n);
        }
    }

    private static void drawHex(TETile[][] world, Pair point, int n) {
        int w = 3 * n -2;
        int h = 2 * n;
        int offsetX = point.x;
        int offsetY = point.y;
        int half = w / 2;
        TETile t = randomTile();
        for (int i = 0, dots = 0; i < w; i++) {
            if (i < half) {
                dots += 2;
            } else if (i == half) {
                dots += (w % 2) * 2;
            } else {
                dots -= 2;
            }
            drawLine(world[i + offsetX], offsetY, h, dots, t);
        }
    }

    private static void drawLine(TETile[] col, int offsetY, int h, int dots, TETile t) {
        /* color variant */
        Random r = new Random(System.currentTimeMillis());
        //TETile tile = TETile.colorVariant(t, 50, 50, 50, new Random(System.currentTimeMillis()));
        if (dots <= h) {
            int space = (h - dots) / 2;
            for (int i = space; i < space + dots; i++) {
                TETile tile = TETile.colorVariant(t, 100, 100, 100, new Random(r.nextInt(50)));
                col[i + offsetY] = tile;
            }
        } else {
            for (int i = 0; i < h; i++) {
                TETile tile = TETile.colorVariant(t, 100, 100, 100, new Random(r.nextInt(50)));
                col[i + offsetY] = tile;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            case 4: return Tileset.WATER;
            case 5: return Tileset.FLOOR;
            case 6: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }
}
