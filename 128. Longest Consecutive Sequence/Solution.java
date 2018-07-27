// Solution 1: HashSet --> O(1) search time
// beats 82.81%
// HashSet has O(1) search time. we store all elements in nums into a hashset.
// Then, pop each item from hashset, and search through the entire hashset for its left
// and right consecutives respectively and count the number of consecutive found, continue
// searching until there is no consecutives for current number, and update maxCount.
// Note that after finding a consecutive and count it, we must delete that number to
// avoid duplicate count.
// Do this until hashset is empty, aka all items in hashset has been searched.
/*
eg. hashset = [100, 4, 200, 1, 3, 2, 7, 5, 6]
1. pop 100 from hashset, search for 99 --> Not found, search for 101--> not found

2. hashset = [4, 200, 1, 3, 2, 7, 5, 6]
   pop 4 from hashset, search 3, Found --> search 2, Found --> search 1, Found --> search 0 --> not found
                       finish left search
                       search 5, Found --> search 6, Found --> search 7, Found --> search 8 --> not found
                       finish right search
   find 8 numbers 1,2,3,4,5,6,7,8 that can form consecutives around 4 --> update maxCount = 8.

3. hashset = [200], pop 200 --> hashset is empty, finish searching
return 8.
*/
// Space = O(n), Time = O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int longestConsecutiveCount = 0;
        while (!set.isEmpty()) {
            int current = set.iterator().next();
            set.remove(current);
            int count = 1;
            int cur = current - 1;
            while (!set.isEmpty() && set.contains(cur)) { // search left neighbors
                count++;
                set.remove(cur);
                cur--;
            }
            cur = current + 1;
            while (!set.isEmpty() && set.contains(cur)) { // search right neighbors
                count++;
                set.remove(cur);
                cur++;
            }
            longestConsecutiveCount = Math.max(longestConsecutiveCount, count);
        }
        return longestConsecutiveCount;
    }
}



// Solution 1: HashSet, another approach
// Using a set to collect all elements that hasn't been visited. search element will be O(1) 
// and eliminates visiting element again.
// beats 92.09%
public class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<Integer>();

        for(int num: nums) set.add(num);
        int max = 1;
        for(int num: nums) {
            if(set.remove(num)) { // num hasn't been visited
                int val = num;
                int sum = 1;
                while(set.remove(val-1)) { // count left consecutives
                    sum++;
                    val--;
                }

                val = num;
                while(set.remove(val+1)) { // count right consecutives
                    sum++;
                    val++;
                }

                max = Math.max(max, sum);
            }
        }
        return max;
    }
}