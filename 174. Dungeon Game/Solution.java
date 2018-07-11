// DP solution
// O(mn) time, O(mn) space.
// beats 95.70%
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        
        // health[i][j] stores the minimum life points you need to get from dungeon(i, j) to the princess room
        // we build health[][] through dp
        int[][] health = new int[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                minimumHealth(dungeon, health, i, j);
            }
        }
        return health[0][0];
    }
    
    private void minimumHealth(int[][] dungeon, int[][] health, int i, int j) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        if (i == m && j == n)
            health[i][j] = dungeon[i][j] < 0 ? 1 - dungeon[i][j] : 1;
        else if (i == m)
            health[i][j] = dungeon[i][j] < health[i][j + 1] ? health[i][j + 1] - dungeon[i][j] : 1;
        else if (j == n)
            health[i][j] = dungeon[i][j] < health[i + 1][j] ? health[i + 1][j] - dungeon[i][j] : 1;
        else {
            int min = Math.min(health[i][j + 1], health[i + 1][j]);
            health[i][j] = dungeon[i][j] < min ? min - dungeon[i][j] : 1;
        }
    }
}



// Optimization: we can convert dp array into 1D
// health[i][j] only depends on health[i][j + 1] and health[i + 1][j] (old value of itself),
// if we iterate backwards (i and j from big to small) then health[i][j + 1]and health[i + 1][j]
// will be updated before health[i][j]
// Time = O(mn), Space = O(n)
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        
        // health[i][j] stores the minimum life points you need to get from dungeon(i, j) to the princess room
        // we build health[][] through dp
        int[] health = new int[n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                minimumHealth(dungeon, health, i, j);
            }
        }
        return health[0];
    }
    
    private void minimumHealth(int[][] dungeon, int[] health, int i, int j) {
        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        if (i == m && j == n)
            health[j] = dungeon[i][j] < 0 ? 1 - dungeon[i][j] : 1;
        else if (i == m)
            health[j] = dungeon[i][j] < health[j + 1] ? health[j + 1] - dungeon[i][j] : 1;
        else if (j == n)
            health[j] = dungeon[i][j] < health[j] ? health[j] - dungeon[i][j] : 1;
        else {
            int min = Math.min(health[j + 1], health[j]);
            health[j] = dungeon[i][j] < min ? min - dungeon[i][j] : 1;
        }
    }
}



// Version 2, no helper method, same apporach
// O(mn) time, O(1) space.
public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
    
    int m = dungeon.length;
    int n = dungeon[0].length;
    
    int[][] health = new int[m][n];

    health[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);

    for (int i = m - 2; i >= 0; i--) {            
        health[i][n - 1] = Math.max(health[i + 1][n - 1] - dungeon[i][n - 1], 1);
    }

    for (int j = n - 2; j >= 0; j--) {
        health[m - 1][j] = Math.max(health[m - 1][j + 1] - dungeon[m - 1][j], 1);
    }

    for (int i = m - 2; i >= 0; i--) {
        for (int j = n - 2; j >= 0; j--) {
            int down = Math.max(health[i + 1][j] - dungeon[i][j], 1);
            int right = Math.max(health[i][j + 1] - dungeon[i][j], 1);
            health[i][j] = Math.min(right, down);
        }
    }

    return health[0][0];
}