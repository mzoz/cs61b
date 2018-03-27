package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Please enter a seed");
//            return;
//        }

//        int seed = Integer.parseInt(args[0]);
        long seed = System.currentTimeMillis();
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        gameOver = false;
        playerTurn = false;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        rand = new Random(seed);
    }

    public void startGame() {
        int n = 0;
        while (!gameOver) {
            n++;
            drawRound(n);
            String computer = generateRandomString(n);
            drawStr(n, computer);
            drawInput(n, computer);
            drawResult();
        }
        drawClr();
        StdDraw.text(width/2, height/2, "You passed " + (n-1) + " rounds.");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    //===========================================================
    //===========================================================

    public void drawRound(int n) {
        drawClr();
        StdDraw.text(width/2, height/2, "Round " + n);
        StdDraw.show();
        StdDraw.pause(1000);
    }

    public void drawClr() {
        StdDraw.clear(Color.black);
    }

    //===========================================================
    //===========================================================

    public void drawStr(int n, String computer) {
        drawClr();
        playerTurn = false;
        for (char c : computer.toCharArray()) {
            drawFrame(n, playerTurn);
            StdDraw.text(width/2, height/2, String.valueOf(c));
            StdDraw.show();
            StdDraw.pause(800);
            drawClr();
            drawFrame(n, playerTurn);
            StdDraw.show();
            StdDraw.pause(500);
            drawClr();
        }
    }

    public String generateRandomString(int n) {
        StringBuilder s = new StringBuilder();
        while (s.length() < n) {
            s.append(CHARACTERS[rand.nextInt(CHARACTERS.length)]);
        }
        return s.toString();
    }

    public void drawFrame(int n, boolean playerTurn) {
        Font big = StdDraw.getFont();
        Font font = new Font("Courier", Font.BOLD, 20);
        StdDraw.setFont(font);

        Color color = StdDraw.getPenColor();
        StdDraw.setPenColor(Color.orange);
        StdDraw.textLeft(1, height-1, "Round " + n);

        if (!playerTurn) {
            StdDraw.setPenColor(Color.red);
            StdDraw.text(width/2, height-1, "Watch!");
        } else {
            StdDraw.setPenColor(Color.green);
            StdDraw.text(width/2, height-1, "Please type: ");
        }

        StdDraw.setFont(big);
        StdDraw.setPenColor(color);
    }

    //===========================================================
    //===========================================================

    public void drawInput(int n, String computer) {
        drawClr();
        String user = "";
        playerTurn = true;
        while (user.length() < n) {
            drawClr();
            if (StdDraw.hasNextKeyTyped()) {
                user += StdDraw.nextKeyTyped();
            }
            drawFrame(n, playerTurn);
            StdDraw.text(width/2, height/2, user);
            StdDraw.show();
            if (user.length() == n) {
                StdDraw.text(width/2, height/4, "Let's see...");
                StdDraw.show();
                StdDraw.pause(1500);
            }
        }
        if (!user.equals(computer)) {
            gameOver = true;
        }
    }

    //===========================================================
    //===========================================================

    public void drawResult() {
        drawClr();
        if (gameOver) {
            StdDraw.text(width/2, height/2, "Wrong!");
        } else {
            StdDraw.text(width/2, height/2, "Correct!");
        }
        StdDraw.show();
        StdDraw.pause(1500);
    }




}
