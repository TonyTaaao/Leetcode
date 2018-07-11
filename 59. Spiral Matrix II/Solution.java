//Time Complexity = O(N^2), visit each cell in the NxN input array exactly once
//beats 100% of java submissions
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        for (int layer = 0; layer < n / 2; layer++) {
            //j for column, i for row
            for (int j = layer; j < n - 1 - layer; j++) res[layer][j] = num++;
            for (int i = layer; i < n - 1 - layer; i++) res[i][n - 1 - layer] = num++;
            //Note here that j--, not j++, since j goes from last to first
            for (int j = n - 1 - layer; j > layer; j--) res[n - 1 - layer][j] = num++;
            for (int i = n - 1 - layer; i > layer; i--) res[i][layer] = num++;
        }
        if (n % 2 == 1) {
            //(n+1)/2 - 1 = (n-1)/2
            int center = (n - 1) / 2;
            res[center][center] = num;
        }
        return res;
    }
}