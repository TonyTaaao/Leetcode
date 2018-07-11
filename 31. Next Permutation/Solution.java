/*Time Complexity = O(N) worst case
one for loop to iterate through all N elements in the array
for the first nums[i] that is < nums[i+1], swapSmallestGreater() and swap() 
takes O(N) time.
Note that we don't need to do swapSmallestGreater() and swap() for every element
in array, we only need to do it for one element when we first encounter the nums[i]
that is smaller than nums[i]
*/
//runtime beats 90.71% of java submissions
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                swapSmallestGreater(nums, i);
                int j = i + 1;
                int k = nums.length - 1;
                //swap takes O(N/2) time
                while (j < k) {
                    swap(nums, j++, k--);
                }
                return;
            }
        }
        
        //if next permutation is impossible
        int j = 0;
        int k = nums.length - 1;
        while (j < k) {
            swap(nums, j++, k--);
        }
    }
    
    //find the smallest element after i that is greater than nums[i], and swap nums[i] with that element
    //takes O(N) time worst case
    private void swapSmallestGreater(int[] nums, int idx) {
        for (int i = nums.length - 1; i > idx; i--) {
            if (nums[i] > nums[idx]) {
                swap(nums, i, idx);
                return;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



//Same idea, another version
//runtime beats 63% of java submissions
public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}