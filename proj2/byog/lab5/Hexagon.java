package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Hexagon {
    private static final int n = 3;
    private static final int offX = 2;
    private static final int offY = 2;
    private static final int w = 50;
    private static final int h = 50;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(w, h, offX, offY);

        int width = 3 * n -2;
        int height = 2 * n;

        TETile[][] hex = new TETile[width][height];

        int dots = 0;
        int i = 0;
        int half = width / 2;
        for (TETile[] column : hex) {
            if (i < half) {
                dots += 2;
                drawLine(column, dots);
            } else if (i == half) {
                dots += (width % 2) * 2;
                drawLine(column, dots);
            } else {
                dots -= 2;
                drawLine(column, dots);
            }
            i += 1;
        }

        ter.renderFrame(hex);
    }

    private static void drawLine(TETile[] column, int dots) {
        if (dots <= column.length) {
            int space = (column.length - dots) / 2;
            for (int i = 0; i < column.length; i++) {
                if (i < space) {
                    column[i] = Tileset.NOTHING;
                } else if (i < space + dots) {
                    column[i] = Tileset.HEX;
                } else {
                    column[i] = Tileset.NOTHING;
                }
            }
        } else {
            for (int i = 0; i < column.length; i++) {
                column[i] = Tileset.HEX;
            }
        }
    }
}
