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
                    count++;
                }
            }
        }
        return count;
    }
    
    //BFS
    private void search(char[][] map, int i, int j) {
        Queue<Integer> q = new LinkedList<>();
        q.add(i);
        q.add(j);
        
        while (!q.isEmpty()) {
            i = q.poll();
            j = q.poll();
            if (map[i][j] != '1') continue;
            map[i][j] = '0';
            if (i-1 >= 0) {q.add(i-1); q.add(j);}
            if (i+1 < map.length) {q.add(i+1); q.add(j);}
            if (j-1 >= 0) {q.add(i); q.add(j-1);}
            if (j+1 < map[0].length) {q.add(i); q.add(j+1);}
        }
    }
}