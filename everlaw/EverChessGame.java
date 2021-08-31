import java.lang.StringBuilder;

public class EverChessGame {

    /**
     * A grid represents the board and pieces. A 1 has a pawn, a 0 is empty space
     */
    private int[][] grid;

    protected EverChessGame() {
        grid = new int[8][8];
        for (int i = 0; i < 8; i++) {
            grid[1][i] = 1;
            grid[6][i] = 1;
        }
    }

    /**
     * Print the board in console format
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("----------------\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                s.append(grid[i][j]);
                s.append(" ");
            }
            s.append("\n");
        }
        s.append("----------------\n");
        return s.toString();
    }

    public static void main(String[] args) {
        EverChessGame game = new EverChessGame();
        System.out.println(game.toString());
    }

}