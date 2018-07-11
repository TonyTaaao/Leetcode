//DFS
class Solution {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if (row == 0) return 0; //if grid is empty, then we cannot access grid[0], causing the next time to report runtime error
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    search(grid, i ,j);
                    count++; //After drawing out the entire island (draw as big as possible, reaching boundary
                    	//in all four directions), we add 1 to the number of islands
                }
            }
        }
        return count;
    }
    
    //i = row idnex, j = col index
    private void search(char[][] map, int i, int j) {
        if (i < 0 || i >= map.length || j < 0 || j >= map[0].length ||
            map[i][j] != '1') return;
        map[i][j] = '0'; //avoid double count of the same grid
        //search all four directions of this grid to spread this piece of island
        search(map, i+1, j);
        search(map, i-1, j);
        search(map, i, j+1);
        search(map, i, j-1);
    }
}

/***Note: Given a '1', you cannot only search rightward and downward, you have to search
all four directions!!!
只向右方和下方搜寻的反例：
       1<--This grid will be the first place to find a '1' on this piece of island
       1
 1     1       1
 1     1 1 1 1 1
 1 1 1 1   
     |   
     | 
     |
     ^
     If we do not check left and up, we are going to miss this part of the island
*/