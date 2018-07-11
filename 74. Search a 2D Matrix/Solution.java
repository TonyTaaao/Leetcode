class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length - 1;
        if (rows == -1) return false;
        int cols = matrix[0].length - 1;
        if (cols == -1) return false;
        
        //First, find which row target falls into.
        int start = 0, end = rows; //low and high
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == matrix[mid][cols]) return true;
            else if (target > matrix[mid][cols]) start = mid + 1;
            else end = mid - 1;
        }
        //start == the row target falls into
        if (start > rows) return false; //target > the largest number in this 2D matrix
        int[] row = matrix[start];
        
        //Next, perform binary search on this row.
        start = 0; end = cols;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == row[mid]) return true;
            else if (target > row[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }
}