注意看题：Given a non-empty array of integers, every element appears three times 
except for one, which appears exactly once. Find that single one.
数组中仅有一个元素出现一次，而其他元素都正好出现三次。

此解法适用于：数组中仅有一个元素出现一次，其他元素出现任何多于一次的次数的情况。
相当于题目要求的扩展，即不仅适用于题目的情形，还可以用于其他情形。
// beats 56.65%
// Time Complexity = O(NlogN + N) ~ O(NlogN)
// Space Complexity = O(1), just one extra variable: boolean duplicate
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums); // takes O(NlogN) time
        boolean duplicate = false; // mark if current element is a duplicate
        // do not use this:
        // for (int i = 0; i < nums.length - 1; i++) {
        // N次重复提取nums.length, not efficient
        int length = nums.length; // 仅提取一次，更省时间
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if (!duplicate) return nums[i];
                else duplicate = false;
            } else {
                duplicate = true;
            }
        }
        return nums[length - 1];
    }
}


//Version2, same approach
//beats 48.21%
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        int length = nums.length;
        while (i < length - 1) {
            if (nums[i] != nums[i + 1]) return nums[i];
            //i += 2; // can skip i and i+1 since both are duplicate. 不加这句也ok.
            while (nums[i] == nums[i + 1]) i++;
            i++;
        }
        return nums[length - 1];
    }
}