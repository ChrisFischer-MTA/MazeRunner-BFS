package mazerunner;

/*
First write of the move class for this project.
It's really not needed for practical purposes, but
it's easier to read with this here.
Christopher R. Fischer - 10/15/16
Junior in High School
Check license/usage terms @ christopherrfischer.me.
*/

import java.util.ArrayList;

public class move extends Object {

    public int y;
    public int x;
    ArrayList<move> prev = new ArrayList();

    public move(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public move(int y, int x, ArrayList<move> previousmoves) {
        super();
        previousmoves.add(new move(y, x));
        prev.addAll(previousmoves);
    }

    public ArrayList<move> getPrev() {
        return prev;
    }

    public ArrayList<move> getPotentialMoves() {
        // See what moves are valid.
        ArrayList<move> moves = new ArrayList();

        // This was put here for debugging purposes. It's purely cosmetic, so
        // I'll be leaving it.
        if (prev.isEmpty()) {
            prev.add(new move(y, x));
        }

        // Checks if a space in relation to our point is valid, and if so it adds it to the queue.
        
        // Check right
        if (BFS.isMoveValid(y, x - 1)) {
            moves.add(new move(y, x + 1, prev));
        }

        // Check left
        if (BFS.isMoveValid(y, x + 1)) {
            moves.add(new move(y, x - 1, prev));
        }

        // Check above
        if (BFS.isMoveValid(y + 1, x)) {
            moves.add(new move(y + 1, x, prev));
        }

        // Check below
        if (BFS.isMoveValid(y - 1, x)) {
            moves.add(new move(y - 1, x, prev));
        }

        return moves;
    }
}
