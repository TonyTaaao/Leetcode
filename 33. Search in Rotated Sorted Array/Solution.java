//There are 3 solutions in total. All solutions have O(logN) time complexity.
//Binary search of an array usually invloves two-pointer: low and high, or start and end.
class Solution { //Solution 1
    public int search(int[] nums, int target) {
        // First, find the index of the smallest value (rotate index) using binary search.
        int length = nums.length;
        int lo = 0, hi = length - 1;
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while (lo < hi) {
            int mid = (lo + hi) / 2; //int mid = (lo + hi) >> 1;
            //If nums[mid] > nums[hi] -->abnormal order detected, right side is the rotate interval
            //NOTE: normal order means ascending order only.
            if (nums[mid] > nums[hi]) lo = mid + 1;
            /***If nums[mid] < nums[hi] -->normal order on right side, There are two cases:
            1. abnormal order on left side: left side is the rotate interval
            2. normal order on left side: if both left side and right side are normally ordered,
            it means we have already located the rotate interval, and since rotate point is the smallest number,
            it should be the leftmost number of current interval, so we continue on the left side
            */
            else hi = mid;
            /* This is wrong.
            if (nums[lo] > nums[mid]) hi = mid;
            else lo = mid + 1;
            */
        }
        //After this, lo == hi is the index of the smallest value and also the rotate index.

        //Next, search target throughout the array
        //The usual binary search and accounting for rotation.
        int rot = lo;
        lo = 0; hi = length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (rot + mid) % length;
            if (target == nums[realmid]) return realmid;
            else if (target < nums[realmid]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }
}


//Another version, same approach, only difference is line 57
class Solution { //Solution 1
    public int search(int[] nums, int target) {
        // First, find the index of the smallest value (rotate index) using binary search.
        int length = nums.length;
        int lo = 0, hi = length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            //If nums[mid] > nums[hi] -->abnormal order detected, right side is the rotate interval
            if (nums[mid] > nums[hi]) lo = mid + 1;
            //Both left and right side are in normal order, already located the rotate interval, 
            //the rotate point is the leftmost point (which is lo) -->rot = lo;
            else if (nums[lo] < nums[mid]) break;
            //abnormal order on left side: left side is the rotate interval
            else hi = mid;
        }
        //After this, lo == hi is the index of the smallest value and also the rotate index.
        
        //The usual binary search and accounting for rotation.
        int rot = lo;
        lo = 0; hi = length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (rot + mid) % length;
            if (target == nums[realmid]) return realmid;
            else if (target < nums[realmid]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }
}

/***A different idea for the second part of the code(finding target's index):
The first two solutions can be improved a little..no need to do a binary search accounting for rotation.
if target>A[0] then need to search only in 0 to rot
if target<A[n-1] then need to binary search in only rot to n-1
and if target is between A[n-1] and A[0], return -1
*/

/***A different idea for the second part of the code(finding target's index).
Use regular binary search on either of the two intervals of the array. The intervals are: 
(1) [0 to actualLow] (2)[actualLow to (n-1)] <--actualLow == rot
(1)if target >= nums[0], (2) if target < nums[0]
Both (1) and (2) have ascending order only. Once we find out which interval of the array target belongs to,
we can use regular binary search on that interval.
Code:
    if(target >=A[0] ){
                lo = 0;
                hi = (actualLow == 0) ? n-1: (actualLow-1);
            }
    else{
       lo=actualLow ;
       hi=A.length - 1;
    }

    //Followed by Regular binary search using these lo and hi values
*/
class Solution { //Solution 2
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) return -1;
        
        // First, find the index of the smallest value (rotate index) using binary search.
        int lo = 0, hi = length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            //If nums[mid] > nums[hi] -->abnormal order detected, right side is the rotate interval
            if (nums[mid] > nums[hi]) lo = mid + 1;
            //Both left and right side are in normal order, already located the rotate interval, 
            //the rotate point is the leftmost point (which is lo) -->rot = lo;
            else if (nums[lo] < nums[mid]) break;
            //abnormal order on left side: left side is the rotate interval
            else hi = mid;
        }
        //After this, lo == hi is the index of the smallest value and also the rotate index.
        
        //There are two intervals of the array. The intervals are : (1) [0 to rot] (2)[rot to (n-1)]
        //Both intervals are ascending order only.
        //Locate target on the right interval, and then use regular binary search on that interval.
        
        //Locate the interval target belongs to
        int rot = lo;
        if (target == nums[0]) return 0;
        else if (target > nums[0]) {
            lo = 0;
            hi = (rot == 0) ? length - 1 : rot - 1;
        } else {
            lo = rot;
            hi = length - 1;
        }
        //Followed by Regular binary search using these lo and hi values
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (target == nums[mid]) return mid;
            else if (target > nums[mid]) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}


//Solution using only one binary search
/***
The idea is that when rotating the array, there must be one half of the array that is still in sorted order.
For example, [6 7 1 2 3 4 5], the order is disrupted from the point between 7 and 1. So when doing binary 
search, we can make a judgement that which part is ordered and whether the target is in that range, 
if yes, continue the search in that half, if not continue in the other half.
*/
public class Solution { //Solution 3
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi + 1) / 2;
            if (target == nums[mid]) return mid;
            //Find the normal interval (normal == ascending order only)
            //If target falls into the normal interval, continue on that interval
            //Otherwise, continue on the other interval
            if (nums[lo] < nums[mid]) { //left interval is normal (ascending order)
                if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else { //right interval is normal (ascending order)
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }
}


public class Solution { //Solution 3
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2; //difference is here
            if (target == nums[mid]) return mid;
            //Find the normal interval (normal == ascending order only)
            //If target falls into the normal interval, continue on that interval
            //Otherwise, continue on the other interval
            if (nums[lo] <= nums[mid]) { //left interval is normal (ascending order)
                if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else { //right interval is normal (ascending order)
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }
}