public class NumArray {
    private SegmentTree tree;

    public NumArray(int[] nums) {
        tree = new SegmentTree();
        if (nums.length == 0) return;
        tree.buildTree(nums, 0, nums.length - 1);
    }
    
    public void update(int i, int val) {
        tree.updateTree(i, val);
    }
    
    public int sumRange(int i, int j) {
        return tree.sumInterval(i, j);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class SegmentTree {
    public int[] interval;
    public int sum;
    public SegmentTree left;
    public SegmentTree right;
    
    public SegmentTree() {
        interval = new int[2];
        sum = 0;
    }
    
    public int buildTree(int[] nums, int start, int end) {
        interval[0] = start;
        interval[1] = end;
        if (start == end) {
            sum = nums[start];
        } else {
            int mid = (start + end) / 2;
            left = new SegmentTree();
            int leftSum = left.buildTree(nums, start, mid);
            right = new SegmentTree();
            int rightSum = right.buildTree(nums, mid + 1, end);
            sum = leftSum + rightSum;
            System.out.println(sum);
        }
        return sum;
    }
    
    public void updateTree(int i, int val) {
        int start = interval[0], end = interval[1];
        System.out.println("start:" + start + ", end:" + end);
        if (i == start && i == end) {
            sum = val;
            return;
        } else {
            int mid = (start + end) / 2;
            if (i <= mid) {
                System.out.println("left here");
                left.updateTree(i, val);
            } else {
                right.updateTree(i, val);
            }
        }
        sum = (left != null ? left.sum : 0) + (right != null ? right.sum : 0);
        System.out.println("left sum:" + left.sum);
        System.out.println("right sum:" + right.sum);
                System.out.println(" sum:" + sum);
    }
    
    public int sumInterval(int left, int right) {
        int start = interval[0], end = interval[1];
        int mid = (start + end) / 2;
        if (start == left && end == right) return sum;
        if (right <= mid) {
            return this.left.sumInterval(left, right);
        } else if (left > mid) {
            return this.right.sumInterval(left, right);
        } else {
            return this.left.sumInterval(left, mid) + this.right.sumInterval(mid + 1, right);
        }
    }
}