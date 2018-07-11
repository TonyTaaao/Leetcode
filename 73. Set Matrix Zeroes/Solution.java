//Time Complexity = O(M*N), iterate through every cell in the M*N matrix
//Space Complexity = O(M+N), due to rows[] and cols[]
//Not the best solution, best solution should use constant space
//beats 92.70%
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        
        boolean[] rows = new boolean[m]; //if row i has zero, mark rows[i] as true
        boolean[] cols = new boolean[n]; //if col j has zero, mark cols[j] as true
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        
        //if row i has one or more 0, set the entire row to zero
        for (int i = 0; i < m; i++) {
            if (rows[i]) {
                for (int j = 0; j < n; j++) matrix[i][j] = 0;
            }
        }
        
        //if col j has one or more 0, set the entire col to zero
        for (int j = 0; j < n; j++) {
            if (cols[j]) {
                for (int i = 0; i < m; i++) matrix[i][j] = 0;
            }
        }
    }
}



//Version2, same idea, shorter by combing line 24-35 into 1 for loop
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        
        boolean[] rows = new boolean[m]; //if row i has zero, mark rows[i] as true
        boolean[] cols = new boolean[n]; //if col j has zero, mark cols[j] as true
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        
        //for a given cell matrix[i][j], if its row (row i) has zero OR 
        //its column (col j) contains zero, set matrix[i][j] to zero
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || cols[j]) matrix[i][j] = 0;
            }
        }
    }
}