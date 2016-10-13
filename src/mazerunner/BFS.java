package mazerunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static int sy;
    static int sx;
    static Queue<String> moves = new LinkedList<String>();
    static ArrayList<String> moveArray = new ArrayList();
    static char[][] translated;

    public static char[][] runBFS(char[][] trans) {
        translated = trans;
        findFinish(translated);
        findStart(translated);
        move(sy, sx);
        while (!moves.isEmpty()) {
            String move = moves.remove();
            //System.out.println(move);
            String[] x = move.split(",");
            int zy = Integer.parseInt(x[0]);
            int zx = Integer.parseInt(x[1]);
            //System.out.println();
            //System.out.println(move.length());
            //System.out.println(move);
            //System.out.println(translated[zy][zx]);
            if (!moveArray.contains(move)) {
                moveArray.add(move);
                move(zy, zx);
            } else {

            }
            // Return modified array

        }
        return translated;
    }

    public static void findStart(char[][] translated) {
        for (int i = 0; i < translated.length; i++) {
            for (int j = 0; j < translated[0].length; j++) {
                if (translated[i][j] == 'S') {
                    sy = i;
                    sx = j;
                }
            }
        }
    }

    public static void findFinish(char[][] translated) {
        for (int i = 0; i < translated.length; i++) {
            for (int j = 0; j < translated[0].length; j++) {
                if (translated[i][j] == 'F') {
                    System.out.println(i + " " + j);
                }
            }
        }
    }

    public static void move(int y, int x) {
        if (translated[y][x] == 'F') {
            System.out.println("Finish - " + y + "" + x);
            moves.clear();
        } else if (translated[y][x] == 'S') {
            genMove(y, x);
        } else if (translated[y][x] == '.') {
            translated[y][x] = 'U';
            genMove(y, x);
        } else {
// Assume its a wall
        }
    }

    public static void genMove(int y, int x) {
        if (isValid(y + 1, x)) {
            moves.add("" + (y + 1) + ',' + x);
        }
        if (isValid(y - 1, x)) {
            moves.add("" + (y - 1) + ',' + x);
        }
        if (isValid(y, x - 1)) {
            moves.add("" + y + ',' + (x - 1));
        }
        if (isValid(y, x + 1)) {
            moves.add("" + y + ',' + (x + 1));
        }
    }

    public static boolean isValid(int y, int x) {
        if (y < 0) {
            return false;
        }

        if (x < 0) {
            return false;
        }

        if (y > translated.length - 1) {
            return false;
        }

        if (x > translated[0].length - 1) {
            return false;
        } //if(translated[y][x] == '.')
        else {
            return true;
        }
    }
}
