//beats 92.70%
/*
My idea is simple: store states of each row in the first of that row, and store 
states of each column in the first of that column. Because the state of row0 and 
the state of column0 would occupy the same cell, I let it be the state of row0, 
and use another variable "col0" for column0. In the first phase, use matrix 
elements to set states in a top-down way. In the second phase, use states to set 
matrix elements in a bottom-up way.
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;
        
        int col0 = 1;
        for (int i = 0; i < m; i++) {
            //if j == 0-->stores in col0
            if (matrix[i][0] == 0) col0 = 0;
            //Note that column j starts from 1, since col0 stores info for column 0
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        
        /*
        //Wrong
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
        */
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
        
    }
}