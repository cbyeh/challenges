import java.lang.StringBuilder;
import java.util.HashSet;
import java.util.Scanner;
import static java.lang.Math.abs;

public class ChessGame {

    // A grid represents the board and pieces. A piece is B or W, and X is empty
    private Character[][] grid;
    // Current turn, black or white
    private char currentTurn;
    // Pawns that have captured in a turn
    private HashSet<Character> captured; // TODO: Use pair of indexes instead
    private boolean hasJustCaptured;

    /**
     * Constructor to initialize board to starting positions
     */
    protected ChessGame() {
        grid = new Character[8][8];
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
        captured = new HashSet<>();
        hasJustCaptured = false;
    }

    /**
     * Given a row, column, and whether it is Black or White's turn Return false if
     * the character chosen at (row, col) is empty or not the correct turn. col is
     * between a-h and row is between 1-8. Return true if valid move and can be
     * moved
     */
    public boolean movePiece(int startRow, char startCol, int endRow, int endCol, char turn) {
        int r1 = startRow - 1;
        int c1 = startCol - 'a';
        // Check if valid starting position
        if (c1 > 8 || c1 < 0 || r1 > 8 || r1 < 0 || grid[r1][c1] == 'X' || grid[r1][c1] != turn) {
            return false;
        }
        int r2 = endRow - 1;
        int c2 = endCol - 'a';
        // Check if valid ending position
        if (c2 > 8 || c2 < 0 || r2 > 8 || r2 < 0) {
            return false;
        }
        // Check if valid moves
        if ((turn == 'W' && r1 == 1 && abs(r1 - r2) > 2) // Check valid starting move for W
                || (turn == 'W' && r1 == 1 && c1 != c2)) {
            return false;
        } else if ((turn == 'B' && r1 == 6 && abs(r1 - r2) > 2) // Check valid starting move for B
                || (turn == 'B' && r1 == 6 && c1 != c2)) {
            return false;
        } else if ((turn == 'W' && r1 != 1 && Math.abs(startRow - endRow) > 1)
                || (turn == 'B' && r1 != 6 && Math.abs(startRow - endRow) > 1)) {
            return false;
        }
        // Perform move and return true. If captured, set hasJustCaptured to true
        if ((turn == 'W' && grid[r2][c2] == 'B') || turn == 'B' && grid[r2][c2] == 'W') {
            hasJustCaptured = true;
            captured.add(turn);
        } else {
            captured.clear();
            hasJustCaptured = false;
        }
        grid[r1][c2] = 'X';
        grid[r2][c2] = turn;
        return true;
    }

    /**
     * Check if a user has won
     */
    private boolean someoneHasWon() {
        for (int i = 0; i < 8; i++) {
            if (grid[0][i] == 'B' || grid[7][i] == 'W') {
                return true;
            }
        }
        return false;
    }

    /**
     * Print the board in console format
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("------------------\n");
        for (int i = grid.length - 1; i >= 0; i--) {
            s.append(i + 1);
            s.append("  ");
            for (int j = 0; j < grid[i].length; j++) {
                s.append(grid[i][j]);
                s.append(" ");
            }
            s.append("\n");
        }
        s.append("   a b c d e f g h\n");
        s.append("------------------\n");
        return s.toString();
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        System.out.println(game.toString());
        System.out.println("Welcome to chess. Select moves with rows 1-8 and columns a-h");
        Scanner scanner = new Scanner(System.in);
        while (!game.someoneHasWon()) {
            try {
                System.out.print("Enter starting row: ");
                int r1 = Integer.valueOf(scanner.nextLine());
                System.out.print("Enter starting column: ");
                char c1 = scanner.nextLine().charAt(0);
                System.out.print("Enter ending row: ");
                int r2 = Integer.valueOf(scanner.nextLine());
                System.out.print("Enter ending column: ");
                char c2 = scanner.nextLine().charAt(0);
                if (!game.movePiece(r1, c1, r2, c2, game.currentTurn)) {
                    System.out.println("Invalid move. Try again");
                    continue;
                }
                if (!game.hasJustCaptured) {
                    if (game.currentTurn == 'W') {
                        game.currentTurn = 'B';
                    } else {
                        game.currentTurn = 'W';
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
            }
            System.out.println(game.toString());
        }
        scanner.close();
    }

}