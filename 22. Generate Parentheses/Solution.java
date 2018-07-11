//Recursive Solution
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n == 0) return res;
        helper(res, "", n, n);
        return res;
    }
    
    //open = number of "(" to generate, close = number of ")" to generate
    private void helper(List<String> res, String str, int open, int close) {
        if (open == 0) {
            while (close-- > 0) str += ")";
            res.add(str);
        } else if (open == close) {
            helper(res, str + "(", open - 1, close);
        } else { //if open < close
            helper(res, str + "(", open - 1, close);
            helper(res, str + ")", open, close - 1);
        }
    }
}
/*
Time Complexity should be nd.
n is how many options you can choose from at each position.
d is how many positions.


I think the time complexity should be O(2^2n). Think it like this,
for every character so far, we have two options, go left or go right, which means 
we could either "(" or ")". Our base case should be either we have 2n "(" or ")". 
So we have 2n levels, it is a geometric progression. Then you will get the result 
O(2^2n). However, for this problem, we only need to consider half of the whole 
because when we have n "(" or ")" then we reach to the level our result. So generally 
we can add 1/2 as a factor to the 2^2n, but our time complexity is still O(2^2n) level. 
Hope this will help you understand.


I think the runtime is O(2^n):
given n, the initial state must be "(", then we want to add the next char, it 
can be '(' or ')',
so T(n) = 2T(n-1),
then T(n-1) = 2T(n-1),
finally, T(n) = 2^n
*/



//Iterative Solution
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n == 0) { //End case
            res.add("");
            return res;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            List<String> innerList = generateParenthesis(i);
            List<String> outerList = generateParenthesis(n - 1 - i);
            for (String inner : innerList) {
                for (String outer : outerList) {
                    res.add("(" + inner + ")" + outer);
                }
            }
        }
        return res;
    }
}



//Optimized Iterative Solution
//prevents solving the same N multiple times by using memoization array
//Utilize more space/memory, but reduce runtime
class Solution {
    public List<String> generateParenthesis(int n) {
        //memoization that stores answer already solved.
        //memo[i] stores answer for n = i
        //This is to prevent solving the same n multiple times
        List<List<String>> memo = new ArrayList<>();
        memo.add(Collections.singletonList("")); //memo[0] = {""};
        
        for (int i = 1; i <= n; i++) { //stores the answer list for n = i into memo[i]
            List<String> list = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                for (String inner : memo.get(j)) {
                    for (String outer : memo.get(i - 1 - j)) {
                        list.add("(" + inner + ")" + outer);
                    }
                }
            }
            memo.add(list);
        }
        
        return memo.get(n);
    }
}