/*
 Backtracking Solution:

 Try 1 through 9 for each cell. The time complexity should be 9 ^ m (m represents the number 
 of blanks to be filled in), since each blank can have 9 choices.
*/
// beats 82.86%
public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solvable(board);
    }
    
    // returns whether current board is solvable
    private boolean solvable(char[][] board) {
        int n = board.length; // board is an n*n square
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') { // if we meet an empty cell
                    for (char c = '1'; c <= '9'; c++) { // for each empty cell, try 1-9
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c; // if current number c is valid, put c at this cell
                            
                            if (solvable(board))
                                return true; // if it's the solution, return true
                            else
                                board[i][j] = '.'; // otherwise, c is not the right number to be put at board[i][j]
                        }
                    }
                    return false; // if none of the numbers from 1 to 9 at this cell could make the sudoku solvable, return false
                }
            }
        }
        return true; // if all cells are non-empty, the sudoku is solved, return true
    }
    
    // returns whether character c can be put at board[row][col] and form a valid matrix along
    // its row, column, and the 3x3 sub-box
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c) return false;
            if (board[row][i] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}

