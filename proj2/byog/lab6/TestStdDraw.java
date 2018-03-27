package byog.lab6;

import com.oracle.net.Sdp;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;

public class TestStdDraw {
    public static void main(String[] args) {
        StdDraw.setCanvasSize();
//        StdDraw.setScale(0.1, 1.1);
//        StdDraw.filledCircle(0.5, 0.5, 0.5);
//        Font font = new Font("Courier", Font.BOLD, 50);
//        StdDraw.setFont(font);
//        StdDraw.text(0.5, 0.5, "Yeah!");

        /* moving balls */
        StdDraw.setScale(-2, 2);
        StdDraw.enableDoubleBuffering();

        for (double t = 0.0; true; t += 0.02) {
            double x = Math.sin(t);
            double y = Math.cos(t);
            StdDraw.clear(StdDraw.LIGHT_GRAY);
            StdDraw.filledCircle(x, y, 0.05);
            StdDraw.filledCircle(-x, -y, 0.05);
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
