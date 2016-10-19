package mazerunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_new {

    // Queue holds up coming moves.
    static Queue<move> moves = new LinkedList();

    // Stores movable space.
    static char[][] parsedMaze;

    // Important Points
    static int starty;
    static int startx;

    public static char[][] runBFS(char[][] trans) {
        parsedMaze = trans;
        // Make sure that a start and finish exist.
        if (!endPointsExist()) {
            System.out.println("Missing an endpoint.");
            return parsedMaze;
        }

        // Start first.
        move move = new move(starty, startx);

        // Here for debugging.
        System.out.println(move.getPotentialMoves().size());

        // Assign the starting moves to the queue.
        moves.addAll(move.getPotentialMoves());

        // Main loop!
        while (!moves.isEmpty()) {
            move m = moves.poll();
            if (isMoveValid(m.y, m.x)) {
                if (parsedMaze[m.y][m.x] == 'F') {
                    System.out.println("Move found!");
                    pathHightlight(m);
                    moves.clear();
                } else if (parsedMaze[m.y][m.x] == 'X' || parsedMaze[m.y][m.x] == 'U') {
                    // Wall or previous space, do nothing.
                } else {
                   // System.out.println(m.y + " " + m.x + "-" + parsedMaze[m.y][m.x]);
                    parsedMaze[m.y][m.x] = 'U';
                    moves.addAll(m.getPotentialMoves());
                    //System.out.println(m.prev.size());
                }
            }
        }
        return parsedMaze;
    }

    public static void pathHightlight(move m) {
        // Get previous moves.
        ArrayList<move> previous = m.getPrev();

        // Loop through and highlight each move.
        for (int i = 0; i < previous.size(); i++) {
            parsedMaze[previous.get(i).y][previous.get(i).x] = 'L';
            System.out.println(previous.get(i).y+ " " +previous.get(i).x);
        }
    }

    public static boolean endPointsExist() {
        return findFinish(parsedMaze) && findStart(parsedMaze);
    }

    public static boolean findStart(char[][] translated) {
        for (int i = 0; i < translated.length; i++) {
            for (int j = 0; j < translated[0].length; j++) {
                if (translated[i][j] == 'S') {
                    starty = i;
                    startx = j;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean findFinish(char[][] translated) {
        for (int i = 0; i < translated.length; i++) {
            for (int j = 0; j < translated[0].length; j++) {
                if (translated[i][j] == 'F') {
                    return true;

                }
            }
        }
        return false;
    }

    public static boolean isMoveValid(int y, int x) {
        if (y < 0) {
            return false;
        }

        if (x < 0) {
            return false;
        }

        if (y > parsedMaze.length - 1) {
            return false;
        }

        if (x > parsedMaze[0].length - 1) {
            return false;
        }
        if (parsedMaze[y][x] == '.' || parsedMaze[y][x] == 'U' || parsedMaze[y][x] == 'F') {
            return true;
        } else {
            return false;
        }
    }
}
