// beats 100%
/*
The complexity in Solution.java is O(n^3n) because of isValid() method. The time complexity 
of isValid() can be improved to constant through memorization, making the total time complexity 
O(n^n). Here is my solution that does that.

We used three memoization array, rows[], diag1[], and diag2[] to store whether a particular
row or diagonal is already occupied.
rows[i] stores whether the ith row is occupied.
diag1[] and diag2[] --> see diagonal explanation.ipg

Time Complexity T(n) Equation:
So T(n) = O(n) * O(n^2) * T(n-1), the O(n^2) here is the time complexity of isValid()
now that it is optimized to constant, we have T(n) = O(n) * T(n-1)
--> T(n) = (O(n))^n = O(n^n)
*/
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        boolean[] rows = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        List<List<String>> res = new LinkedList<List<String>>();
        dfs(board, 0, res, rows, diag1, diag2);
        return res;
    }
    
    private void dfs(char[][] board, int colIndex, List<List<String>> res,
                     boolean[] rows, boolean[] diag1, boolean[] diag2) {
        int n = board.length;
        if (colIndex == n) {
            res.add(constructList(board));
            return;
        }
        
        // go through each cell in current column, if placing 'Q' at that cell is valid, we continue dfs to the next column
        // once all columns from index 0 to (n-1) have been placed a VALID 'Q', we can add the board to res
        for (int i = 0; i < n; i++) {
            // if there is another queen on the same row/diagonal, skip current cell
            if (rows[i] || diag1[colIndex - i + n - 1] || diag2[colIndex + i]) {
                continue;
            } else {
                board[i][colIndex] = 'Q';
                rows[i] = true;
                diag1[colIndex - i + n - 1] = true;
                diag2[colIndex + i] = true;
                dfs(board, colIndex + 1, res, rows, diag1, diag2);
                board[i][colIndex] = '.';
                rows[i] = false;
                diag1[colIndex - i + n - 1] = false;
                diag2[colIndex + i] = false;
            }
        }
    }
    
    private List<String> constructList(char[][] board) {
        List<String> list = new LinkedList<>();
        int n = board.length;
        for (int i = 0; i < n; i++) {
            String row = String.valueOf(board[i]);
            list.add(row);
        }
        return list;
    }
}

