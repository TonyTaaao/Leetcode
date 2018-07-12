// The apporoach is the same as Problem 51, see LC 51 for detailed explanation.
// don't need to actually place the queen.
// beats 100%
// Time Complexity = O(n^n)
// Space Complexity = O(n) due to memoization arrays.
class Solution {
    int count;
    
    public int totalNQueens(int n) {
        count = 0;
        boolean[] rows = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        dfs(0, n, rows, diag1, diag2);
        return count;
    }
    
    private void dfs(int col, int n, boolean[] rows, boolean[] diag1, boolean[] diag2) {
        if (col == n) {
            count++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            // if current row or either of the two diagonals that pass through current cell is already occupied by
            // another queen, current cell is invalid.
            if (rows[i] || diag1[col - i + n - 1] || diag2[col + i]) continue;
            
            rows[i] = true;
            diag1[col - i + n - 1] = true;
            diag2[col + i] = true;
            dfs(col + 1, n, rows, diag1, diag2);
            rows[i] = false;
            diag1[col - i + n - 1] = false;
            diag2[col + i] = false;
        }
    }
}



// Another version, same approach
// beats 39%
/**
 * don't need to actually place the queen in the 2D board,
 * instead, for each row, try to place a queen without violating
 * col/ diagonal1/ diagnol2.
 * trick: to detect whether 2 positions sit on the same diagnol:
 * if delta(col, row) equals, same diagnal1;
 * if sum(col, row) equals, same diagnal2.
 */
/*
 What does it mean for a collection to be final in Java?
 It means that its reference (of the collection) can't be changed.

 Difference between final and immutable Objects:

 final --> You cannot change the reference to the collection (Object). You can modify the 
 collection / Object the reference points to. You can still add elements to the collection.

 immutable --> You cannot modify the contents of the Collection / Object the reference 
 points to. You cannot add elements to the collection.
*/
class Solution {
    private final Set<Integer> occupiedCols = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();
    
   public int totalNQueens(int n) {
        return totalNQueensHelper(0, 0, n);
    }

    private int totalNQueensHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col))
                continue;
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1))
                continue;
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2))
                continue;
            // we can now place a queen here
            if (row == n-1)
                count++;
            else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                count = totalNQueensHelper(row+1, count, n);
                // recover
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }

        return count;
    }
}