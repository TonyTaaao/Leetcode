//写法1
class Solution {
    public int singleNumber(int[] nums) {
        //Integer in array --> number of occurrence
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], 1);
            else map.remove(nums[i]);
        }
        return map.keySet().iterator().next();
    }
}

//写法2
class Solution {
    public int singleNumber(int[] nums) {
        //Integer in array, remove entry if it is duplicate, which can be detected by Set
        HashSet<Integer> set = new HashSet();
        //Class HashSet<E>: boolean remove(Object o): Removes the specified element from this set if it is present.
        for (int i = 0; i < nums.length; i++) {
            if (!set.remove(nums[i])) set.add(nums[i]);
        } 
        return set.iterator().next();
    }
}