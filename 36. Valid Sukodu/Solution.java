class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> row = new HashSet<>();
            HashSet<Character> column = new HashSet<>();
            HashSet<Character> grid = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                //hashset.add() will return false if duplicate exists
                if (board[i][j] != '.' && !row.add(board[i][j])) return false;
                if (board[j][i] != '.' && !column.add(board[j][i])) return false;
                int gridRowIndex = 3*(i/3), gridColIndex = 3*(i%3);
                int boxRowIndex = gridRowIndex + j/3, boxColIndex = gridColIndex + j%3;
                if (board[boxRowIndex][boxColIndex] != '.' &&
                   !grid.add(board[boxRowIndex][boxColIndex])) return false;
            }
        }
        return true;
    }
}