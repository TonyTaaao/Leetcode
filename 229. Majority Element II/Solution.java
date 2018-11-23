// Boyer-Moore Majority Vote algorithm
// beats 99%
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new LinkedList<>();
        int length = nums.length;
        if (length == 0) return res;
        
        int num1 = nums[0], num2 = nums[0], count1 = 0, count2 = 0;
        for (int num : nums) {
            if (num == num1) count1++;
            else if (num == num2) count2++;
            else if (count1 == 0) {
                num1 = num;
                count1++;
            } else if (count2 == 0) {
                num2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == num1) count1++;
            else if (num == num2) count2++;
        }
        if (count1 > length / 3) res.add(num1);
        if (count2 > length / 3) res.add(num2);
        return res;
    }
}