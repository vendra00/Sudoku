public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        //Board Layout: 0 are used as blank values
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        System.out.println("Original Board");
        printBoard(board);

        if(solveBoard(board)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board");
        }

        printBoard(board);
    }

    /**
     * Method that will print for the user the output from the game
     * @param board represents the game board.
     */
    private static void printBoard(int[][] board) {

        for(int row = 0; row < GRID_SIZE; row++) {
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------------------------");
            }
            for(int column = 0; column < GRID_SIZE; column++) {
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(" " + board[row][column] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method that will validate if a number can be placed in the row
     * @param board represents the game board.
     * @param number represents the number to be validated.
     * @param row represents a row of the board.
     * @return will return true or false if It's valid or not.
     */
    private static boolean isNumberInRow(int[][] board, int number, int row) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i] == number) {
                return  true;
            }
        }
        return false;
    }

    /**
     * Method that will validate if a number can be placed in the column
     * @param board represents the game board.
     * @param number represents the number to be validated.
     * @param column represents a column of the board.
     * @return will return true or false if It's valid or not.
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[i][column] == number) {
                return  true;
            }
        }
        return false;
    }

    /**
     * Method that will check if the number can be placed in the box number from the sudoku board (3x3)
     * @param board represents the game board.
     * @param number represents the number to be validated.
     * @param row represents a row of the board.
     * @param column represents a column of the board.
     * @return will return true or false if It's valid or not.
     */
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {

        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j< localBoxColumn + 3; j++) {
                if(board[i][j] == number) {
                    return  true;
                }
            }
        }
        return false;
    }

    /**
     * Method that will check if it's a valid placement in the board.
     * @param board represents the game board.
     * @param number represents the number to be validated.
     * @param row represents a row of the board.
     * @param column represents a column of the board.
     * @return will return true or false if It's valid or not.
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row)
                && !isNumberInColumn(board, number, column)
                && !isNumberInBox(board, number, row, column);
    }

    /**
     * Method that will solve the game with a given board[][] using recursion in itself
     * @param board represents the game board.
     * @return will return true or false in case if the board can be solved or not
     */
    private static boolean solveBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++) {
            for(int column = 0; column < GRID_SIZE; column++) {
                //If it finds a blanc spot
                if(board[row][column] == 0) {
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        //If it's a valid placement it will add the number to the blanc spot
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;
                            //If it can be placed, and it's correct. Else, it will be set to zero again.
                            if (solveBoard(board)) {
                               return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return  true;
    }
}
