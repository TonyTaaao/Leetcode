//Time Complexity = N + (N-2) + (N-4) + (N-6) + ... + 2 = (N+2)*(N/2)/2=O(N^2/4 + N/2) ~ O(N^2)
//runtime beats 100% of java submissions
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n < 2) return;
        //iterate through first half of all rows
        for (int i = 0; i <= (n - 1) / 2; i++) {
            //rotate every grid in row i clockwise
            for (int j = i; j < n - 1 - i; j++) {
                int upperleft = matrix[i][j];
                int upperright = matrix[j][n - 1 - i];
                int lowerleft = matrix[n - 1 - j][i];
                int lowerright = matrix[n - 1 - i][n - 1 - j];
                matrix[j][n - 1 - i] = upperleft;
                matrix[n - 1 - i][n - 1 - j] = upperright;
                matrix[n - 1 - j][i] = lowerright;
                matrix[i][j] = lowerleft;
            }
        }
    }
}



//Best version:
//Same idea, more concise, no need to keep four variables, just need one "temp"
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int temp =matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}



//Another version, 取矩阵的四分之一
//beats 100%
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        //i and j 一个取(n + 1) / 2，一个取n / 2即可
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp =matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}