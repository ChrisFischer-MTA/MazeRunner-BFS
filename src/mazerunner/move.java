package mazerunner;

import java.util.ArrayList;

public class move extends Object {

    public int y;
    public int x;
    ArrayList<move> prev = new ArrayList();

    public move(int y, int x) {
        //System.out.println("Default constructor used!");
        this.y = y;
        this.x = x;
    }

    public move(int y, int x, ArrayList<move> previousmoves) {
        this.y = y;
        this.x = x;
        previousmoves.add(new move(y, x));
        prev.addAll(previousmoves);
       // System.out.println(x+" "+y);
    }

    public ArrayList<move> getPrev() {
        return prev;
    }

    public ArrayList<move> getPotentialMoves() {
        ArrayList<move> moves = new ArrayList();
        if (prev.isEmpty()) {
            prev.add(new move(y, x));
        }
        if (BFS_new.isMoveValid(y + 1, x)) {
            moves.add(new move(y + 1, x, prev));
        }
        if (BFS_new.isMoveValid(y - 1, x)) {
            moves.add(new move(y - 1, x, prev));
        }
        if (BFS_new.isMoveValid(y, x - 1)) {
            moves.add(new move(y, x + 1, prev));
        }
        if (BFS_new.isMoveValid(y, x + 1)) {
            moves.add(new move(y, x - 1, prev));
        }
    return moves;
    }
}
