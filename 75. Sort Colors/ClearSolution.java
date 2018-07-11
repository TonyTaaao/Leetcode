class Solution {
    public void sortColors(int[] A) {
        if (A == null || A.length <= 1) return;
        
        int index0 = 0; //index of last occurred 0
        int index1 = 0; //index of last occurred 1
        int index2 = 0; //index of last occurred 2
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                A[index2++] = 2; //Notice the order, first 2 then 1 then 0
                A[index1++] = 1;
                A[index0++] = 0;
            }
            else if (A[i] == 1) {
                A[index2++] = 2;
                A[index1++] = 1;
            }
            else {
                A[index2++] = 2;
            }
        }
    }
}