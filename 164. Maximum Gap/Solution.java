// Solution 1: Radix Sort
// beats 12.30%
// Time Coplexity = O(d(n + radix)) ~ O(n)
// Space Complexity = O(aux.length + count.length) = O(n + radix) ~ O(n) extra space.
class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        
        int size = nums.length;
        int m = nums[0];
        for (int i = 1; i < size; i++) {
            m = Math.max(m, nums[i]);
        }
        
        int exp = 1;
        int radix = 10;
        int[] aux = new int[size];
        while (m >= exp) {
            int[] count = new int[radix];
            
            for (int i = 0; i < size; i++) {
                count[(nums[i] / exp) % 10]++;
            }
            for (int i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            for (int i = size - 1; i >= 0; i--) {
                aux[--count[(nums[i] / exp) % 10]] = nums[i];
            }
            for (int i = 0; i < size; i++) {
                nums[i] = aux[i];
            }
            exp *= radix;
        }
        
        int max = 0;
        for (int i = 1; i < size; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }
}



// Solution 2: Bucket Sort, beats 93.04%
// See Detailed Explanation at Leetcode Solution/Discussion
/*
Suppose there are N elements in the array, the min value is min and the max value is max. Then 
the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].

Let gap = ceiling[(max - min ) / (N - 1)]. We divide all numbers in the array into n-1 buckets, 
where k-th bucket contains all numbers in [min + (k-1)gap, min + k*gap). Since there 
are n-2 numbers that are not equal min or max and there are n-1 buckets, at least one of the 
buckets are empty. We only need to store the largest number and the smallest number in each bucket.

After we put all the numbers into the buckets. We can scan the buckets sequentially and get 
the max gap.
*/
// Time Coplexity = O(n), Space Complexity = O(n).
public class Solution {
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        // get the max and min value of the array
        int min = num[0];
        int max = num[0];
        for (int i:num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // the minimum possibale gap, ceiling of the integer division
        int gap = (int)Math.ceil((double)(max - min)/(num.length - 1));
        int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i:num) {
            if (i == min || i == max)
                continue;
            int idx = (i - min) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < num.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
        return maxGap;
    }
}