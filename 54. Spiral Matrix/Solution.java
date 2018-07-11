//beats 64.92% of java submissions
//Time Complexity = O(m*n), visit every single cell in 2D array just once
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        
        for (int layer = 0; layer < n / 2 && (m - 2*layer) > 1; layer++) {
            //j for column, i for row
            for (int j = layer; j < n - 1 - layer; j++) res.add(matrix[layer][j]);
            for (int i = layer; i < m - 1 - layer; i++) res.add(matrix[i][n - 1 - layer]);
            for (int j = n - 1 - layer; j > layer; j--) res.add(matrix[m - 1 - layer][j]);
            for (int i = m - 1 - layer; i > layer; i--) res.add(matrix[i][layer]);
        }
        
        //special case: 1 row layer
        if (m <= n && m % 2 == 1) {
            int mid = m / 2; //mid layer
            for (int j = mid; j <= n - 1 - mid; j++) res.add(matrix[mid][j]);
        } 
        //special case: 1 column layer
        else if (m > n && n % 2 == 1) {
            int mid = n / 2;
            for (int i = mid; i <= m - 1 - mid; i++) res.add(matrix[i][mid]);
        }
        
        return res;
    }
}



//Version2, same idea, check special case (1 row layer OR 1 coln layer) within
//the loop
//beats 64.92%
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        
        for (int layer = 0; layer <= (n - 1) / 2; layer++) {
            //j for column, i for row
            if (m - 2* layer == 0 || n - 2*layer == 0) break;
            else if (m - 2 * layer == 1) {
                for (int j = layer; j <= n - 1 - layer; j++) res.add(matrix[layer][j]); 
                break;
            } else if (n - 2*layer == 1) {
                for (int i = layer; i <= m - 1 - layer; i++) res.add(matrix[i][layer]);
                break;
            }
            
            for (int j = layer; j < n - 1 - layer; j++) res.add(matrix[layer][j]);
            for (int i = layer; i < m - 1 - layer; i++) res.add(matrix[i][n - 1 - layer]);
            for (int j = n - 1 - layer; j > layer; j--) res.add(matrix[m - 1 - layer][j]);
            for (int i = m - 1 - layer; i > layer; i--) res.add(matrix[i][layer]);
        }
        
        return res;
    }
}