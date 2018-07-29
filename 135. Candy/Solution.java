// This approach is not very efficient.
// Idea: If ratings is rising, candy(i + 1) = candy(i) + 1
// if ratings is equal, candy(i + 1) = 1
// else: rating is decreasing, we find the decrementing interval and assign
// this interval of length k to [Math.max(k, candy(i)), k-1, k-2, ... 3,2,1]
// the current peak must both satisfy leftside (current = candy[i] we got earlier) 
// and rightside (current = k), so it has to be Math.max(candy[i], k).
// Time Complexity = O(n), visit each index once.
// Space Complexity = O(n)
// beats 7%
class Solution {
    public int candy(int[] ratings) {
        int size = ratings.length;
        if (size == 0) return 0;
        
        // candices[i] store min number of candies that we give to child i.
        int[] candies = new int[size];
        for (int i = 0; i < size; i++) {
            candies[i] = 1;
        }
        int i = 0;
        while (i + 1 < size) {
            if (ratings[i] < ratings[i + 1]) {
                // if cur < next, ratings is increasing, candy(next) = candy(cur) + 1
                candies[i + 1] = candies[i] + 1;
                i++;
            }
            else if (ratings[i] == ratings[i + 1]) {
                // if current == next, we can give next 1 candy
                candies[i + 1] = 1;
                i++;
            }
            else {
                // if next < cur, meaning interval arr[cur, next] is descending
                // find the descending interval starting at index cur, which is [start, end].
                // during this interval, candy[end] will be 1 and the rest of child from 
                // end - 1 to start - 1 will be incrementing by 1 each. Since ratings[cur]
                // is a peak value, it should be the max(itself, candy[cur + 1] + 1).
                int end = descendingInterval(ratings, i + 1);
                candies[end] = 1;
                for (int j = end - 1; j > i; j--) {
                    candies[j] = candies[j + 1] + 1;
                }
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                i = end;
            }
        }
        for (int n : candies)
            System.out.print(n + ", ");
        return Arrays.stream(candies).sum();
    }
    
    // return the end index of current descending interval
    private int descendingInterval(int[] arr, int end) {
        int size = arr.length;
        while (end + 1 < size && arr[end] > arr[end + 1]) {
            end++;
        }
        return end;
    }
}


// same approach without extra array
// Time Complexity = O(n), visit each index once.
// Constant extra space
class Solution {
    public int candy(int[] ratings) {
        int size = ratings.length;
        if (size == 0) return 0;
        
        int cur = 1, next = 1;
        int sum = 0;
        int i = 0;
        while (i + 1 < size) {
            if (ratings[i] < ratings[i + 1]) {
                // if next > cur
                next = cur + 1;
                i++;
            }
            else if (ratings[i] == ratings[i + 1]) {
                next = 1;
                i++;
            }
            else {
                // if next < cur, meaning interval arr[cur, next] is descending
                int end = descendingInterval(ratings, i + 1);
                int intervalLength = end - i + 1;
                sum += intervalLength * (intervalLength - 1) / 2 - 1;
                cur = Math.max(cur, intervalLength);
                next = 1;
                i = end;
            }
            System.out.print(cur + ", ");
            sum += cur;
            cur = next;
        }
        sum += next;
        return sum;
    }
    
    // return the end index of current descending interval
    private int descendingInterval(int[] arr, int end) {
        int size = arr.length;
        while (end + 1 < size && arr[end] > arr[end + 1]) {
            end++;
        }
        return end;
    }
}