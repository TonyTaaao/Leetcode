/*
Problem
"Start from any index, we can collect at most two types of fruits. What is the maximum amount"

Translation
Find out the longest length of subarrays with at most 2 different numbers?

Solution of sliding window will be easier to understand.
Here I share another solution wihtout hash map.
Hope it's not damn hard to understand.

Explanation:
Loop all fruit c in tree,
Note that a and b are the last two different types of fruit that we met,
c is the current fruit type,
so it's something like "....aaabbbc..."

Case 1 c == b:
fruit c already in the basket,
and it's same as the last type of fruit
cur += 1
count_b += 1

Case 2 c == a:
fruit c already in the basket,
but it's not same as the last type of fruit
cur += 1
count_b = 1
a = b, b = c

Case 3 c != b && c!= a:
fruit c not in the basket,
cur = count_b + 1
count_b = 1
a = b, b = c

Of course, in each turn we need to update res = max(res, cur)

Complexity:
O(N) time, O(1) space
*/
class Solution {
    public int totalFruit(int[] tree) {
        int res = 0, cur = 0, count_b = 0, a = 0, b = 0;
        for (int c :  tree) {
            cur = c == a || c == b ? cur + 1 : count_b + 1;
            count_b = c == b ? count_b + 1 : 1;
            if (b != c) {a = b; b = c;}
            res = Math.max(res, cur);
        }
        return res;
    }
}


// Another version, same idea
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}