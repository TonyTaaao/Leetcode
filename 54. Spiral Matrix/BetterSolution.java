//beats 64.92%
//clearer logic, no extra case for odd number of rows/cols
//Maintain 4 variables: rowStart, rowEnd, colStart, colEnd
//Keep adding to list as long as rowStart <= rowEnd && colStart <= colEnd
//Layer By Layer Approach
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        
        int rowStart = 0, rowEnd = m - 1;
        int colStart = 0, colEnd = n - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            //j for col, i for row
            for (int j = colStart; j <= colEnd; j++) res.add(matrix[rowStart][j]);
            for (int i = rowStart + 1; i <= rowEnd; i++) res.add(matrix[i][colEnd]);
            //if there are four edges (not a single row/column)
            if (rowStart < rowEnd && colStart < colEnd) {
                for (int j = colEnd - 1; j >= colStart; j--) res.add(matrix[rowEnd][j]);
                for (int i = rowEnd - 1; i > rowStart; i--) res.add(matrix[i][colStart]);
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        
        return res;
    }
}