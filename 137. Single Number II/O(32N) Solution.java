注意看题：Given a non-empty array of integers, every element appears three times 
except for one, which appears exactly once. Find that single one.

数组中仅有一个元素出现一次，而其他元素都正好出现三次。

解题思路：
 This solution can be extended to any times of occurance, even negative numbers.
 Just change count % 3 to count % k, k = target number of occurrences.

 充分利用数组元素是int类型这一特点，统计所有元素的第i个bit上为1的个数，因为题目说了其它元素
 出现了3次，而特殊元素只出现了1次，所以当统计的个数不能整除3就表明特殊元素(unique element)
 在第i个bit上是1，所以我们把结果的第i个bit设为1，能整除就说明特殊元素在第i个bit上是0。
 通过检查32个bit，这样我们就构造出了特殊元素，最后返回结果即可，时间复杂度是O（32n）。

 归纳：
 考虑每个元素的为一个32位的二进制数，这样每一位上出现要么为1 ，要么为0。
 对数组，统计每一位上1 出现的次数count，必定是3N或者3N+1 次。让count对3取模，能够获得到
 那个只出现1次的元素该位是0还是1。

 This is a general solution that can be applied to any times of occurrence.
 Say all the numbers except the unique one occurs 5 times, just do count % 5.
 

// beats 56.65%
// Note: "&" is bitwise AND (for binary numbers), "&&" is logic AND (for booleans).
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        int length = nums.length; // 避免在for loop里反复提取nums.length,更耗时。
        for(int i = 0; i < 32; i++) {
            int count = 0;
            for(int j = 0; j < length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    count++;
                }
            }
            if(count % 3 != 0) { // or: if (count % 3 == 1)
                ans |= 1 << i;
            }
        }
        return ans;
    }
}


// Version2, same approach
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        int length = nums.length;
        for(int i = 0; i < 32; i++) {
            int count = 0;
            int mask = 1 << i;
            for(int j = 0; j < length; j++) {
                if((nums[j] & mask) != 0) {
                    count++;
                }
            }
            if(count % 3 == 1) {
                ans |= mask;
            }
        }
        return ans;
    }
}