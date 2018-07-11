//Doing this from back to front is much easier than the other way
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; //pointer in arr1
        int j = n - 1; //pointer in arr2
        int pos = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[pos--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
        while (j >= 0) nums1[pos--] = nums2[j--];
    }
}