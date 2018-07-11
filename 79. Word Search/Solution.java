/*
This problem is similar to LC.130 Surrounded Regions and LC.200 Number of Islands.
Iterate through all cells of 2D array board, if we find a match(current cell == word[0]),
we do dfs search beginning with this cell -->
Search all cells adjacent to current cell (up, down, left, right) and check if
it matches the next character in word, continue this searching until
1. current cell == word[end] --> the entire string is found, return true
2. current cell index is out of bounds, OR current cell is already visited, 
    OR curent cell != word[current position] --> return false
*/

/*
board size = M*N, word length = len

Time Complexity = O(M*N*4*len) worst case: we iterate through all M*N cells,
and for each cell, we perform dfsSearch from word[0] to word[length - 1] in four
directions, which will search O(4*len) times.

Space Complexity = O(M*N) due to boolean[][] visited.
*/
class Solution {
    public boolean exist(char[][] board, String word) {
        int length = board.length;
        if (length == 0) return false;
        int width = board[0].length;
        if (width == 0) return false;
        
        boolean[][] visited = new boolean[length][width];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfsSearch(board, word, i ,j, 0, visited)) return true;
                }
            }
        }
        return false;
    }
    
    //boolean[][] visited is used to mark all visited cells.
    //If current cell is already visited, we skip it because each letter cell
    //can only be used once.
    private boolean dfsSearch(char[][] board, String word, int x, int y,
                        int index, boolean[][] visited) {
        //if index is out of bounds OR current cell is already visited in current search
        //process OR current cell does not match word
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
           visited[x][y] || board[x][y] != word.charAt(index)) return false;
        
        /*If board[x][y] is unvisited before and equal to word.charAt(index):
        1.if word[index] is the last character in word, meaning we search through all chars
        in word -->find the match, return true
        2.otherwise, continue to search the next character in word
        */
        if (index == word.length() - 1) return true;
        else {
            visited[x][y] = true;
            boolean existed = dfsSearch(board, word, x + 1, y, index + 1, visited)
                || dfsSearch(board, word, x - 1, y, index + 1, visited)
                || dfsSearch(board, word, x, y + 1, index + 1, visited)
                || dfsSearch(board, word, x, y - 1, index + 1, visited);
            visited[x][y] = false;
            return existed;
        }
    }
}