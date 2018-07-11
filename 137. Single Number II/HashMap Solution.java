//uses a map to keep track of the counts of each element in the array.
//not efficient at all
class Solution {
    public int singleNumber(int[] nums) {
        // integer --> count
        //declare map as HashMap will be faster than as Map 
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        
        //or: for (Integer key : map.keySet())
        for (int key : map.keySet()) {
            if (map.get(key) == 1) return key;
        }
        
        return 0;
    }
}