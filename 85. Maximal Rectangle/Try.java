class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (n == 0) return 0;
        
        int maxarea = 0;
        int[][] area = new int[m][n];
        // vertical count
        for (int j = 0; j < n; j++) {
            area[0][j] = matrix[0][j] == '1' ? 1 : 0;
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] == '1') {
                    if (matrix[i - 1][j] == '1')
                        area[i][j] = area[i - 1][j] + 1;
                    else
                        area[i][j] = 1;
                }
                maxarea = Math.max(maxarea, area[i][j]);
            }
        }
        
        // horizontal count
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (area[i][j] > 0) {
                    if (area[i][j - 1] >= area[i][j]) 
                        area[i][j] += a
                }
            }
        }
    }
}




class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length; // length
        if (m == 0) return 0;
        int n = matrix[0].length; // width
        if (n == 0) return 0;
        
        int[][] leftLength = new int[m][n];
        int[][] leftWidth = new int[m][n];
        int[][] upLength = new int[m][n];
        int[][] upWidth = new int[m][n];
        int maxarea = 0;
        
        // initialize
        if (matrix[0][0] == '1') {
            leftLength[0][0] = leftWidth[0][0] = upLength[0][0] = upWidth[0][0] = 1;
            maxarea = 1;
        }
        
        // first column
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == '1') {
                upLength[i][0] = leftWidth[i][0] = upLength[i - 1][0] + 1;
                upWidth[i][0] = leftLength[i][0] = 1;
                int area = upLength[i][0] * upWidth[i][0];
                maxarea = Math.max(maxarea, area);
            }
        }
        // first row
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == '1') {
                leftLength[0][j] = upWidth[0][j] = leftLength[0][j - 1] + 1;
                leftWidth[0][j] = upLength[0][j] = 1;
                int area = leftLength[0][j] * leftWidth[0][j];
                maxarea = Math.max(maxarea, area);
            }
        }
        
        // matrix traversal
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int area = 0;
                if (matrix[i][j] == '1') {
                    // up is '0', left is '0'
                    if (matrix[i - 1][j] == '0' && matrix[i][j - 1] == '0') {
                        leftLength[i][j] = leftWidth[i][j] = upLength[i][j] = upWidth[i][j] = 1;
                        area = 1;
                    } 
                    // up is '0', left is '1'
                    else if (matrix[i - 1][j] == '0' && matrix[i][j - 1] == '1') {
                        leftLength[i][j] = leftLength[i][j - 1] + 1;
                        leftWidth[i][j] = upLength[i - 1][j] = upWidth[i - 1][j] = 1;
                        area = leftLength[i][j];
                    }
                    // up is '1', left is '0'
                    else if (matrix[i - 1][j] == '1' && matrix[i][j - 1] == '0') {
                        upWidth[i][j] = upWidth[i - 1][j] + 1;
                        upLength[i - 1][j] = leftLength[i][j] = leftWidth[i - 1][j] = 1;
                        area = upWidth[i][j];
                    } 
                    // up is '1', left is '1'
                    else {
                        leftLength[i][j] = leftLength[i - 1][j] + 1;
                        leftWidth[i][j] = leftWidth[i - 1][j];
                        upWidth[i][j] = upWidth[i][j - 1] + 1;
                        upLength[i][j] = upLength[i][j - 1];
                        area = Math.max(leftLength[i][j] * leftWidth[i][j], upWidth[i][j] * upLength[i][j]);
                    }
                }
                maxarea = Math.max(maxarea, area);
            }
        }
        
        // test 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(leftLength[i][j] + " ");
            }
            System.out.println();
        }
        
        return maxarea;
    }
}