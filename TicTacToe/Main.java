package TicTacToe;

import java.util.Scanner;

enum Player{
    A, B
}

enum Symbol {
    X, O
}

class Board {
    Symbol grid[][];
    int dimensions;

    Board(int dimensions) {
        this.dimensions = dimensions;
        grid = new Symbol[dimensions][dimensions];
    }

    void printBoard() {
        System.out.println("Current configuration is: ");
        for(int i=0;i<dimensions;i++) {
            for(int j=0;j<dimensions;j++) {
                if(grid[i][j] == Symbol.O) System.out.print("\t0\t");
                else if(grid[i][j] == null) System.out.print("\t?\t");
                else System.out.print("\tX\t");
            }
            System.out.println();
        }
    }

    void addSymbol(Player currentPlayer, int row, int col) {
        grid[row][col] = currentPlayer == Player.A ? Symbol.X : Symbol.O;
        printBoard();
    }

    void flushBoard() {
        for(int i=0;i<dimensions;i++)
            for(int j=0;j<dimensions;j++)
                grid[i][j] = null;
    }

}

class TicTacToeManager {
    Board board;
    int dimensions;
    boolean gameWinner;
    Player currentPlayer;
    int remainingMoves;

    TicTacToeManager(int dimensions) {
        remainingMoves = (dimensions*dimensions);
        this.dimensions = dimensions;
        board = new Board(dimensions);
        gameWinner = false;
        currentPlayer = Player.A;
    }

    boolean isPlayerWinner(int row, int col) {
        Symbol symbolRef = (currentPlayer == Player.A) ? Symbol.X : Symbol.O;

        int count = 0;
        for(int j=0;j<dimensions;j++)
            if(board.grid[row][j] == symbolRef)
                count++;
        if(count == dimensions)
            return gameWinner = true;
        count = 0;
        for(int i=0;i<dimensions;i++)
            if(board.grid[i][col] == symbolRef)
                count++;
        if(count == dimensions)
            return gameWinner = true;
        count = 0;

        for(int i=0,j=0;i<dimensions && j<dimensions;i++,j++)
            if(board.grid[i][col] == symbolRef)
                count++;
        if(count == dimensions)
            return gameWinner = true;
        count = 0;

        for(int i=0,j=dimensions-1;i<dimensions && j>=0;i++,j--)
            if(board.grid[i][col] == symbolRef)
                count++;
        if(count == dimensions)
            return gameWinner = true;
        
        return false;
    }

    boolean isInvalidMove(int row, int col) {
        if( row < 0 || row >= dimensions || col < 0 || col >= dimensions ||
            board.grid[row][col] != null) {
            System.out.println("Invalid move. Enter again.");
            return true;
        }
        return false;
    }


    void startGame() {
            Scanner sc = new Scanner(System.in);
        while(true && remainingMoves-->0) {
            System.out.println("Current turn for Player: " + currentPlayer);
            int r, c;

            do {
                System.out.println("Enter the row: ");
                r = sc.nextInt();
                System.out.println("Enter the column: ");
                c = sc.nextInt();
            } while (isInvalidMove(r, c));

            board.addSymbol(currentPlayer, r, c);
            if(isPlayerWinner(r, c))
                break;
            currentPlayer = (currentPlayer == Player.A) ? Player.B : Player.A;
        }

        if(gameWinner == true) {
            System.out.println("Congrats! Player: " + currentPlayer + " has won the game!");
        } else System.out.println("The game has ended with a draw.");
        System.out.println("Game over.");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter Y to play the game or any other character to quit.");
            char choice = sc.next().charAt(0);
            if(choice == 'Y' || choice == 'y') {
                System.out.println("Enter the dimensions of the board: ");
                int dimensions = Math.max(sc.nextInt(), 2);
                TicTacToeManager manager = new TicTacToeManager(dimensions);
                manager.startGame();
            } else {
                System.out.println("Choice was: " + choice);
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
