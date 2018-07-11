// Solution 1: copy backup array into array
/*
We use an extra array in which we place every element of the array at its 
correct position, i.e. the number at index i in the original array is placed at 
the index (i+k). Then, we copy the new array to the original one.
*/
// Time Complexity = O(2n) ~ O(n)
// O(n) extra space
// beats 98.09%
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if (k % length == 0) return;

        int[] copy = new int[length];
        for (int i = 0; i < length; i++) {
            copy[(i + k) % length] = nums[i];
        }
        for (int i = 0; i < length; i++) {
            nums[i] = copy[i];
        }
    }
}



// Solution 2: Brute Force, Time Limit Exceeded
// Approach: shift 1 step to the right each time, shift k times
// Time Complexity = O(kn)
// Space Complexity = O(1)
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        if (k == 0) return;
        
        for (int i = 1; i <= k; i++) {
            int temp = nums[length - 1];
            for (int j = length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }
}

// Solution 2 version 2
public class Solution {
    public void rotate(int[] nums, int k) {
        int temp, previous;
        for (int i = 1; i <= k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
}



// Solution 3: Using reverse
/*
This approach is based on the fact that when we right shift the array k times, 
k elements at the back end of the array will come to the front, and 
the (n-k) elements at the front will move to the back.

In this approach, we firstly reverse all elements of the array. Then, reversing
the k elements at the front followed by reversing the rest (n−k) elements gives us
thee right result.

Let n=7 and k=3.

Original List                   : 1 2 3 4 5 6 7
Correct answer after rotate     : 5 6 7 1 2 3 4
---------------------------------
After reversing all numbers     : 7 6 5 4 3 2 1
After reversing first k numbers : 5 6 7 4 3 2 1
After revering last n-k numbers : 5 6 7 1 2 3 4 --> Correct Result
*/
// Time complexity : O(2n) ~ O(n). The array is reversed twice. First, reverse
// the entire array. Next, we reverse the front and back end of the array separately.
// Space complexity : O(1). No extra space is used.
// beats 98.09%
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}