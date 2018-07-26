// Solution 1: HashSet
// 看到题目 －－> 想到最快的lookup time --> HashTable/HashSet has O(1)lookup time
// Time = O(n), Space = O(n)
// beats 3.91%
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>(); // HashSet has O(1) lookup time due to hash table.
        // add all items in nums to set, so that set is the same as nums but has O(1) lookup time.
        for (int i = 0; i < n; i++) { // takes O(n) time
            set.add(nums[i]);
        }
        
        // iterate through all positive integers ascendingly, if not found in set(aka nums), return that integer.
        for (int i = 1; i <= n; i++) { // takes O(n) time
            if (!set.contains(i)) // takes O(1) time
                return i;
        }
        return n + 1;
    }
}



// Solution 2: O(n) runtime and constant extra space
// beats 65%
/*
Put each number in its right place.
For example: we want A to look like A = [1,2,3,4,5]

When we find 5 at an index != 4, then swap it with A[4].
--> When we find i at an index != i-1, then swap it with A[i-1].

At last, the first place where its number is not right, return the place + 1.

The key here is to use swapping to keep constant space and also make use of the length 
of the array, which means there can be at most n positive integers. So each time we 
encounter an valid integer, find its correct position and swap. Otherwise we continue.
*/
class Solution {
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            while (A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i])
                swap(A, i, A[i] - 1);
        }
        
        for (int i = 0; i < n; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return n + 1;
    }
    
    private void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}


// clearer logic, same approach
// bears 65%
class Solution {
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        int i = 0;
        while (i < n) {
            if (A[i] == i+1 || A[i] <= 0 || A[i] > n) i++;
            else if (A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        
        for (i = 0; i < n; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return n + 1;
    }
    
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}