// beats 91.46%
/*
Time Complexity Analysis:
the main method here is dfs(). Within dfs(), we iterate through each row -->O(n), and for each
row, we perform isValid(), which takes O(n^2) time due to two for loops. Within isValid(), 
we perform dfs on the next level, which is T(n-1).

So T(n) = O(n) * O(n^2) * T(n-1) = O(n^3) * T(n-1)
replacing n with n-1 and we get: T(n-1) = O(n^3) * T(n-2), ...
--> T(n) = (O(n^3))^n = O(n^3n)
*/
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new LinkedList<List<String>>();
        dfs(board, 0, res);
        return res;
    }
    
    private void dfs(char[][] board, int col, List<List<String>> res) {
        int n = board.length;
        if (col == n) {
            res.add(constructList(board));
            return;
        }
        
        // go through each cell(row) in current column, if placing 'Q' at that cell is valid, we continue dfs to the next column
        // once all columns from index 0 to (n-1) have been placed a VALID 'Q', we can add the board to res
        for (int i = 0; i < n; i++) {
            if (isValid(board, i, col)) {
                board[i][col] = 'Q';
                dfs(board, col + 1, res);
                board[i][col] = '.'; // After dfs search at current cell is done, change it back to empty, because
                // if we don't and if there are multiple solutions, all the cells that has been marked as Queen in the
                // previous solution would also appear in current solution, resulting in more than 8 Queens in current solution
            }
        }
    }
    
    // checks whether placing a Queen at board[row][col] is valid
    // if there are other Queens on the same row, or Queens on the same diagonal, return false
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'Q' && (i == row || row - i == col - j || row - i == j - col))
                    return false;
            }
        }
        return true;
    }
    
    private List<String> constructList(char[][] board) {
        List<String> list = new LinkedList<>();
        int n = board.length;
        for (int i = 0; i < n; i++) {
            String row = new String(board[i]); // or String.valueOf(board[i]);
            list.add(row);
        }
        return list;
    }
}

