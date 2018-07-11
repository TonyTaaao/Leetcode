//All solutions have O(logN) time complexity.

/* Solution 1 wrong version, Wrong due to pass in primitive type in helper method
class Solution {
    public int[] searchRange(int[] nums, int target) {
        //if (nums.length == 0) return new int[]{-1, -1};
        int start = -1, end = -1;
        helper(nums, target, 0, nums.length - 1, start, end);
        return new int[]{start, end};
    }
    
    //Primitive types are passed by a copy of it, so start and end  at line 5 won't change here-->futile
    //However, arrays in Java are treated as Object. Any changes in the content of array through that reference will affect the original array.
    private void helper(int[] nums, int target, int lo, int hi, int s, int e) {
        if (lo < 0 || hi >= nums.length || lo > hi) return;
        int mid = (lo + hi + 1) / 2;
        if (target < nums[mid]) helper(nums, target, lo, mid - 1, s, e);
        else if (target > nums[mid]) helper(nums, target, mid + 1, hi, s, e);
        else {
            if (s == -1 || mid < s) s = mid;
            if (e == -1 || mid > e) e = mid;
            helper(nums, target, lo, mid - 1, s, e);
            helper(nums, target, mid + 1, hi, s, e);
        }
    }
}
*/

//Solution 1
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        helper(nums, target, 0, nums.length - 1, ans);
        return ans;
    }
    
    //Primitive types are passed by a copy of it, so start and end  at line 5 won't change here-->futile
    //However, arrays in Java are treated as Object. Any changes in the content of array through that reference will affect the original array.
    private void helper(int[] nums, int target, int lo, int hi, int[] ans) {
        if (lo < 0 || hi >= nums.length || lo > hi) return;
        int mid = (lo + hi + 1) / 2;
        if (target < nums[mid]) helper(nums, target, lo, mid - 1, ans);
        else if (target > nums[mid]) helper(nums, target, mid + 1, hi, ans);
        else {
            //boolean algebra: a + (!a)*b = a + b, + == OR, * == AND
            if (ans[0] == -1 || mid < ans[0]) ans[0] = mid; //the same as: if (ans[0] == -1 || (ans[0] != -1 && mid < ans[0]))
            if (ans[1] == -1 || mid > ans[1]) ans[1] = mid;
            helper(nums, target, lo, mid - 1, ans);
            helper(nums, target, mid + 1, hi, ans);
        }
    }
}

//Solution 2, starting position = the index of the first number >= target
//ending position = the index of the first number > target, or >= (target + 1)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = firstGreaterEqual(nums, target); //nums[start] >= target
        //if start == nums.length (all elements < target) or nums[start] > target -->Not found
        if (start > nums.length - 1 || nums[start] != target) return new int[]{-1, -1};
        //If the code can go here, then start is valid, meaning target is found in nums.
        //end = the index of the last number equal to target.
        //Find the index of the first element > target, then end = this index - 1.
        int end = firstGreaterEqual(nums, target + 1) - 1;
        return new int[]{start, end};
    }
    
    //find the index of the first number that is greater than or equal to target.
    //This helper method returns A.length if target is greater than A[A.length-1].
    private int firstGreaterEqual(int[] A, int target) {
        int lo = 0, hi = A.length;
        while (lo < hi) { //when lo == hi, we find the index, break this loop
            int mid = (lo + hi) / 2; //偏左的mid, low <= mid < high
            //int mid = (lo + hi + 1) / 2; //偏右的mid, low < mid <= high
            if (target <= A[mid]) hi = mid; //should not be mid-1 when A[mid]==target
            else lo = mid + 1;
        }
        return lo;
    }
}


//Solution 3, use two binary search, one for starting position and one for ending position
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        int lo = 0, hi = nums.length - 1;
        
        //Find starting index of target, using binary search
        int start = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (target < nums[mid]) hi = mid - 1;
            else if (target == nums[mid]) {
                start = mid;
                hi = mid - 1;
            }
            else lo = mid + 1; //target > nums[mid]
        }
        if (start == -1) return res;
        else res[0] = start;
        
        //Find ending index of target, only need to do binary search on index [start, end - 1].
        int end = start;
        lo = start; hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (target < nums[mid]) hi = mid - 1;
            else if (target == nums[mid]) {
                end = mid;
                lo = mid + 1;
            }
            else lo = mid + 1;
        }
        res[1] = end;
        return res;
    }
}