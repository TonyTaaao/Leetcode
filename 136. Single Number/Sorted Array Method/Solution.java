//写法1
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; ) { //leaving the 3rd loop condition empty is important, 
        	//since i+= 2 in the code below, we cannot write i++ here
            if (nums[i] == nums[i+1]) i += 2;
            else return nums[i];
        }
        return nums[nums.length-1];
    }
}

//写法2
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i+=2) {
            if (nums[i] != nums[i+1]) return nums[i];
        }
        return nums[nums.length-1];
    }
}

//写法3
class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i+=2) {
            if (nums[i] == nums[i+1]) {} //This {} is a must, otherwise the if else statement fails
            else return nums[i];
        }
        return nums[nums.length-1];
    }
}