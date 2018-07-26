// Time = O(log(min(m,n))), Space = O(1)
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}



// My version, same approach
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // n has to be >= m
        if (n < m) return findMedianSortedArrays(B, A);
        
        // perform binary search(二分法检索，折半检索)
        int low = 0, high = m, halfLength = (m + n + 1) / 2;
        int i, j;
        while (low <= high) {
            i = (low + high) / 2; // split A into 2 halves of equal length, A[low, i-1] and A[i+1, high]
            j = halfLength - i;
            if (i < high && B[j - 1] > A[i]) {
                // i is too small, we should continue searching in the higher half A[i+1, high]
                low = i + 1;
            } 
            else if (i > low && A[i - 1] > B[j]) {
                // i is too big, we should continue searching in the lower half A[low, i-1]
                high = i - 1;
            } 
            else {
                // A[i - 1] <= B[j] && B[j - 1] < A[i], i and j are the right positions to split A and B
                int maxLeft;
                if (i == 0) maxLeft = B[j - 1];
                else if (j == 0) maxLeft = A[i - 1];
                else maxLeft = Math.max(A[i - 1], B[j - 1]);
                if ((m + n) % 2 == 1) return (double) maxLeft;
                
                int minRight;
                if (i == m) minRight = B[j];
                else if (j == n) minRight = A[i];
                else minRight = Math.min(A[i], B[j]);
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}