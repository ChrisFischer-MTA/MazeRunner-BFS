package mazerunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
Second write of the BFS class for this project.
Christopher R. Fischer - 10/15/16
Junior in High School
Check license/usage terms @ christopherrfischer.me.
*/

public class BFS {

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
                switch (parsedMaze[m.y][m.x]) {
                    case 'F':
                // This is the finish, act accordingly.
                        System.out.println("Move found!");
                // Let's go back and highlight the route to get to the finish, and be sure to clear the stack of all the moves.
                        highlightPath(m);
                        moves.clear();
                        break;
                // Wall or previous space, do nothing.
                    case 'X':
                    case 'U':
                        break;
                // Moveable space!
                    default:
                        // Mark it as "has been here before".
                        parsedMaze[m.y][m.x] = 'U';
                        // Add all the potenital moves.
                        moves.addAll(m.getPotentialMoves());
                        break;
                }
            }
        }
        return parsedMaze;
    }

    public static void highlightPath(move m) {
        // Get previous moves.
        ArrayList<move> previous = m.getPrev();
        // I love the half-recursion(ness) of this solution :)

        // Loop through and highlight each move.
        for (int i = 0; i < previous.size(); i++) {
            parsedMaze[previous.get(i).y][previous.get(i).x] = 'L';
            
            // We can print out the path for fun, but this is disabled in the final release.
            // System.out.println(previous.get(i).y+ " " +previous.get(i).x);
        }
    }

    public static boolean endPointsExist() {
        // I know I can eliminate this super easy, but this is simple.
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
