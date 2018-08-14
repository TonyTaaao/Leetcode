// beats 91.96%
// Time Complexity = O(32n) * 3 ~ O(n)
// Space Complexity = O(32n) due to Trie Tree and 2D digits matrix.
class Solution {
    class TrieTree {
        private int val;
        //private List<TrieTree> children;
        private TrieTree left;
        private TrieTree right;
        
        public TrieTree(int val) {
            this.val = val;
        }
        
        public void setChild(int value) {
            if (value == 0 && left == null) this.left = new TrieTree(0);
            else if (value == 1 && right == null) this.right = new TrieTree(1);
        }
        
        public TrieTree getLeft() {
            return left;
        }
        
        public TrieTree getRight() {
            return right;
        }
    }
    
    public int findMaximumXOR(int[] nums) {
        int size = nums.length;
        int[][] digits = new int[size][32];
        for (int i = 0; i < size; i++) { // Time = O(32n)
            int num = nums[i];
            for (int j = 0; j < 32; j++) {
                if (num % 2 == 1) digits[i][31 - j] = 1;
                num = num >> 1;
            }
        }
        
        TrieTree root = new TrieTree(-1);
        TrieTree cur;
        for (int i = 0; i < size; i++) { // Time = O(32n)
            cur = root;
            for (int j = 0; j < 32; j++) {
                int digit = digits[i][j];
                cur.setChild(digit);
                if (digit == 0) cur = cur.getLeft();
                else cur = cur.getRight();
            }
        }
        
        int maxXor = 0;
        for (int i = 0; i < size; i++) { // Time = O(32n)
            int curMax = 0;
            cur = root;
            for (int j = 0; j < 32; j++) {
                curMax = curMax << 1;
                int digit = digits[i][j];
                if (digit == 0 && cur.getRight() != null) {
                    cur = cur.getRight();
                    curMax += 1;
                } else if (digit == 1 && cur.getLeft() != null) {
                    cur = cur.getLeft();
                    curMax += 1;
                } else {
                    cur = cur.getLeft() != null ? cur.getLeft() : cur.getRight();
                }
                // why this mismatch?
                //if (j != 31) curMax = curMax << 1;
            }
            maxXor = Math.max(curMax, maxXor);
        }
        return maxXor;
    }
}