import java.util.Arrays;

/*
    Time and Space Complexity:
    Let's assume:
    the length of input array is n,
    average length of Strings in s_num is k,
    Then, compare 2 strings will take O(k).
    Sorting will take O(nlgn),
    We have nlog(n) comparison operations, and each compare will take O(k). 
    So the total amount of time for sorting should be nlog(n) * k = knlog(n).


    Appending to StringBuilder takes O(n).
    So total will be O(knlog(n)) + O(n) = O(knlogn).

    Space is pretty straight forward: O(n) extra space due to str_num.
*/

// Version 1, self-defined comparator class
// beats 98.89%
class Solution {
    /*
    Compare MSB that differs: 
    eg. [9, 13] --> 913 > 139 because MSB_differ(913) == 9 > 1 == MSB_differ(139)
    eg. [9, 91] --> 991 > 919, MSB_differ(991) = 9 > 1 == MSB_differ(919)
    */
	private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
           return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return "";
        
        // Convert int array to String array, so we can sort later on
        String[] str_num = new String[length];
        for (int i = 0; i < length; i++)
            str_num[i] = String.valueOf(nums[i]); // or: Integer.toString(nums[i]);
        
        // Sort strings according to custom comparator.
        Arrays.sort(str_num, new LargerNumberComparator());

        // An edge case, say you have only a bunch of 0 in your int array
        if (str_num[0].equals("0"))
            return "0";
        
        StringBuilder res = new StringBuilder();
        for (String str : str_num)
            res.append(str);
        
        return res.toString();
    }
}

// Version 1
class Solution {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return "";
        
        // Convert int array to String array, so we can sort later on
        String[] str_num = new String[length];
        for (int i = 0; i < length; i++)
            str_num[i] = String.valueOf(nums[i]); // or: Integer.toString(nums[i]);
        

        // Comparator to decide which string should come first in concatenation

        // Comparator<String> comp = new Comparator<>() --> Wrong!!!
        // error: cannot infer type arguments for Comparator<T>
        // we must write: Comparator<String> comp = new Comparator<String>()
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here (descending order), so that bigger one comes first.
            }
        }; // Don't forget this ";" !!!
        
        Arrays.sort(str_num, comp);
        // An edge case, say you have only a bunch of 0 in your int array
        if (str_num[0].equals("0"))
            return "0";
        
        StringBuilder res = new StringBuilder();
        for (String str : str_num)
            res.append(str);
        
        return res.toString();
    }
}



// Version 2: lambda expression
// Use same approach
// beats 48%
class Solution {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return "";
        
        String[] str_num = new String[length];
        for (int i = 0; i < length; i++)
            str_num[i] = String.valueOf(nums[i]); // or: Integer.toString(nums[i]);
        
        // sort using lambda
        Arrays.sort(str_num, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));

        // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        if (str_num[0].equals("0"))
            return "0";
        
        StringBuilder res = new StringBuilder();
        for (String str : str_num)
            res.append(str);
        
        return res.toString();
    }
}



// Version 3: Priority Queue
// sorting is done by PriorityQueue.
// beats 51%
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        final Queue<String> queue = new PriorityQueue<>(nums.length, 
            (left, right) -> Long.valueOf(right + left).compareTo(Long.valueOf(left + right))
        );
        
        for (int num : nums) {
            queue.offer(String.valueOf(num));
        }
        
        final StringBuilder stringBuilder = new StringBuilder(nums.length);
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
        }
        
        final String result = stringBuilder.toString();
        return result.startsWith("0") ? "0" : result;
    }
}


// Version 3: Priority Queue
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        
        final Queue<String> pq = new PriorityQueue<>(
            (left, right) -> (right + left).compareTo(left + right)
        );
        
        for (int num : nums) {
            pq.offer(String.valueOf(num));
        }
        
        // edge case: all elements are zero
        if (pq.peek().equals("0")) return "0";
        
        final StringBuilder stringBuilder = new StringBuilder(nums.length);
        while (!pq.isEmpty()) {
            stringBuilder.append(pq.poll());
        }
        
        final String result = stringBuilder.toString();
        return result;
    }
}