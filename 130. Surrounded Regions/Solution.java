/***The approach is exactly the same as LC200. Solved by DFS
-->if there is an 'O' on border, we spread out to the entire land connected to this 'O' through depth search
and mark them as '1', meaning these 'O's are not connected to border, aka not surrounded,
and therefore won't be converted to 'X'.
We go through all boundary grids to mark all 'O's connected to border.
There is no need to go through non-border grids because if they are not connected to a boundary 'O' via DFS land construction,
they are definitely going to be flipped to 'X'.
After marking all 'O's connected to border (constructing all lands), we flip the rest of 'O's (unmarked) to 'X',
and convert the marked 'O's, which now become '1', to 'O'.
*/

/***Concise Explanation:
核心思想：只有边界上'O'的位置组成的片区不会被'X'包围。
因此先对边界上的'O'遍历之后暂存为'*'。
非'*'的'O'即被'X'包围了。
*/
class Solution {
    public void solve(char[][] board) {
        int row = board.length;
        if (row <= 2) return; //if only 2 rows or less, the entire board is on border, no flipping
        int col = board[0].length;
        if (col <= 2) return;
        //Only the first/last row/col are borders.
        //Iterate through the first and last row
        for (int j = 0; j < col-1; j++) {
            if (board[0][j] == 'O') mark(board, 0,j);
            if (board[row-1][col-1-j] == 'O') mark(board, row-1, col-1-j);
        }
        //Iterate through the first and last col
        for (int i = 0; i < row-1; i++) {
            if (board[i][col-1] == 'O') mark(board, i, col-1);
            if (board[row-1-i][0] == 'O') mark(board, row-1-i, 0);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X'; //There must be an else, if you just place two ifs then
                //then one you just converted from '1' to 'O' would be converted to 'X' again, which is not what we want
            }
        }
    }

    //Method1: DFS Search
    //set all regions connected to border to '1' --> so that they won't be flipped to 'x'
    private void mark(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
           board[x][y] != 'O') return;
        board[x][y] = '1';
        mark(board, x-1, y);
        mark(board, x+1, y);
        mark(board, x, y-1);
        mark(board, x, y+1);
    }
    
    
    //Method2: BFS Search -- slower runtime
    private void mark(char[][] board, int x, int y) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        
        while (!q.isEmpty()) {
            x = q.poll();
            y = q.poll();
            if (board[x][y] != 'O') continue; //not 'o', no need to manipulate this node, go to the next
            board[x][y] = '1';
            if (x-1 >= 0) {q.add(x-1); q.add(y);} //{}must be used if more than 1 statement in if clause
            if (x+1 < board.length) {q.add(x+1); q.add(y);}
            if (y-1 >= 0) {q.add(x); q.add(y-1);}
            if (y+1 < board[0].length) {q.add(x); q.add(y+1);}
            //x-1 >= 0 --> x-1 > 0, y-1 >= 0 --> y-1 > 0 is also correct.
            //because we are goint to iterate through all grids on the border in line 25&30,
            //so they are going to call mark() and be marked anyway.
        }
    }
    
}