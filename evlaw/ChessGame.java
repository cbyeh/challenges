import java.lang.StringBuilder;

public class ChessGame {

    // A grid represents the board and pieces. A piece is B or W, and X is empty
    private char[][] grid;
    // Current turn, black or white
    private char currentTurn;

    /**
     * Constructor to initialize board to starting positions
     */
    protected ChessGame() {
        grid = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    grid[i][j] = 'W'; // White
                } else if (i == 6) {
                    grid[i][j] = 'B'; // Black
                } else {
                    grid[i][j] = 'X';
                }
            }
        }
        currentTurn = 'W'; // White goes first
    }

    /**
     * Given a row, column, and whether it is Black or White's turn Return false if
     * the character chosen at (row, col) is empty or not the correct turn. col is
     * between a-h and row is between 1-8. Return true if valid move and can be
     * moved
     */
    public boolean movePiece(int startRow, char startCol, int endRow, int endCol, char turn) {
        int row = startRow - 1;
        int col = startCol - 'a';
        // Check if valid starting position
        if (col > 8 || col < 0 || row > 8 || row < 0 || grid[row][col] == 'X' || grid[row][col] != turn) {
            return false;
        }
        // Check if valid move
        return true;
    }

    /**
     * Start a game until a user wins
     */
    public void playGame() {

    }

    /**
     * Print the board in console format
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("----------------\n");
        for (int i = grid.length - 1; i >= 0; i--) {
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
        ChessGame game = new ChessGame();
        System.out.println(game.toString());
    }

}