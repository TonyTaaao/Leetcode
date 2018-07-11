class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        
        for (int layer = 0; layer < n / 2; layer++) {
            //j for column, i for row
            for (int j = layer; j < n - 1 - layer; j++) res.add(matrix[layer][j]);
            for (int i = layer; i < m - 1 - layer; i++) res.add(matrix[i][n - 1 - layer]);
            for (int j = n - 1 - layer; j > layer; j--) res.add(matrix[m - 1 - layer][j]);
            for (int i = m - 1 - layer; i > layer; i--) res.add(matrix[i][layer]);
        }
        
        if (m % 2 == 1) {
            int mid = m / 2; //mid layer
            for (int j = mid; j <= n - 1 - mid; j++) res.add(matrix[mid][j]);
        }
        
        return res;
    }
}




class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        if (n == 0) return res;
        
        for (int layer = 0; layer <= (n - 1) / 2; layer++) {
            //j for column, i for row
            for (int j = layer; j <= n - 1 - layer; j++) res.add(matrix[layer][j]);
            //if there is only 1 row left, no need to rotate and add since there is no more element, we can stop here
            if (m - 2 * layer == 1) break;
            //otherwise, we have 4 sides to add to res
            for (int i = layer + 1; i < m - 1 - layer; i++) res.add(matrix[i][n - 1 - layer]);
            for (int j = n - 1 - layer; j > layer; j--) res.add(matrix[m - 1 - layer][j]);
            for (int i = m - 1 - layer; i > layer; i--) res.add(matrix[i][layer]);
        }
        
        return res;
    }
}