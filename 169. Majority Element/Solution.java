// Solution 1: hash table
// beats 12.38%
// use hashmap to store each element's number of occurrences
// Time Complexity = O(n): one for loop, hashmap.containsKey() and .put() is 
// amortized O(1).
// Space Complexity = O(n) due to hashmap

class Solution {
    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        
        // element --> number of occurrences
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int times = map.containsKey(nums[i]) ? map.get(nums[i]) + 1 : 1;
            if (times > length / 2) return nums[i];
            map.put(nums[i], times);
        }
        return 0;
    }
}

// Solution 1, another version
class Solution {
    public int majorityElement(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        
        // element --> number of occurrences
        HashMap<Integer, Integer> map = new HashMap<>();
        int majority = 0;
        for (int num : nums) {
            int times = map.containsKey(num) ? map.get(num) + 1 : 1;
            if (times > length / 2) {
                majority = num;
                break;
            }
            map.put(num, times);
        }
        return majority;
    }
}



// Solution 2: sort and count
// beats 34.58%
// sort input array in ascending order, and count the number of each elements.
// if count > n/2, return that element, nums[i]
// Time Complexity = O(NlogN), due to Arrays.sort().
// Space Complexity = O(1), no extra data structure.

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums); //takes O(NlogN) time
        
        int count = 1;
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > length / 2) return nums[i];
            } else {
                count = 1;
            }
        }
        return nums[0]; //if length == 1
    }
}



// Solution 3: sort, then return mid of array
// beats 35.92%
// Since the majority element appears more than n/2 times, it should occupy
// more than half of the array.
// Therefore, after sorting, the middle of the array, aka nums[n/2], must be
// the majority element.
// 对数组进行排序，那么出现次数超过一半的元素必定是数组中的中间元素，返回这个元素即可。
// Time Complexity = O(NlogN), Space Complexity = O(1)

class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}