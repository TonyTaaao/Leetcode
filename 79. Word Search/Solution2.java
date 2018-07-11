//Same approach a Solution.java, without the need to use an extra boolean[][] visited
//to mark visited cells.
//What we do here is that, once we visited current cell board[x][y], we change it 
//to a unique value different from any character in target word. Since all characters 
//in target word are letters or an empty string, we can change visited cells to '#', or 
//sth like that, such that word.charAt(index) will never be equal to board[visited].
//By doing this, we will never perform dfs search on visited cells.
//After dfs search of current cell is done, we change it back to its original value.

//Time Complexity = O(M*N*4*len) worst case, same as Solution.java
//Space Complexity = O(1), constant space.
class Solution {
    public boolean exist(char[][] board, String word) {
        int length = board.length;
        if (length == 0) return false;
        int width = board[0].length;
        if (width == 0) return false;
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfsSearch(board, word, i ,j, 0)) return true;
                }
            }
        }
        return false;
    }
    
    /*
    If current cell is already visited, we skip it because each letter cell
    can only be used once.

    "board[x][y] ^= 256" is like setting board[x][y] = '#', which is used to mark
    board[x][y], aka current cell, as visited. The range of char is between 0 - 255. 
    By doing xor with 256, board[x][y] becomes a number >= 256 and 
    thus is different from any char.
    After searching through all possible paths starting with board[x][y], we need
    to reset it back to its original value: (x ^ y) ^ y = x.
    */
    private boolean dfsSearch(char[][] board, String word, int x, int y,
                        int index) {
        //if index is out of bounds OR current cell is already visited in current search
        //process OR current cell does not match word
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
           board[x][y] != word.charAt(index)) return false;
        
        /*If board[x][y] is unvisited before and equal to word.charAt(index):
        1.if word[index] is the last character in word, meaning we search through all chars
        in word -->find the match, return true
        2.otherwise, continue to search the next character in word
        */
        if (index == word.length() - 1) return true;
        else {
            board[x][y] ^= 256;
            boolean existed = dfsSearch(board, word, x + 1, y, index + 1)
                || dfsSearch(board, word, x - 1, y, index + 1)
                || dfsSearch(board, word, x, y + 1, index + 1)
                || dfsSearch(board, word, x, y - 1, index + 1);
            board[x][y] ^= 256;
            return existed;
        }
    }
}