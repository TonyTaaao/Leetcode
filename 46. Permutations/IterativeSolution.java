/*
the basic idea is, to permute n numbers, we can add the nth number into the resulting 
List<List<Integer>> from the n-1 numbers, in every possible position.

For example, if the input num[] is {1,2,3}: First, add 1 into the initial 
List<List<Integer>> (let's call it "answer").

Then, 2 can be added in front or after 1. So we have to copy the List in answer 
(it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and 
add 2 in position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the 
current answer.

Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then 
copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for position 3. 
Finally we have 2*3=6 lists in answer, which is what we want.

Demo: How res grows over the loop
{} -> {1} -> {1,2},{2,1} -> {3,1,2},{1,3,2},{1,2,3} (generated from {1,2}, insert 3 at all possible indices)
                            &{3,2,1},{2,3,1},{2,1,3}(generated from {2,1}, insert 3 at all possible indices)
*/

/*
Time Complexity: it doesn't do any extra computation but we need to copy N elements 
into (List<Integer> update) for N! operations.
O(N) per list copied in List<Integer> update * N! times = O(N*N!)
So Time Complexity = O(N*N!).

Space Complexity = O(N*N!) because res contains N! permutations, and each permutation 
contains N elements
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>()); //res begins with {} and grows during the for loop below
        
        //Iterate though every number n in nums
        for (int n : nums) {
            int size = res.size();

            //Iterate through each list, aka each permutation, in res
            while (size-- > 0) {
                //res.poll() won't work if res is declared as List<List<Integer>>, 
                //since List Interface does not have poll() method --> error: cannot find symbol
                //If a variable is declared as List but not LinkedList, it can only
                //call method of List Interface even if it is implemented by LinkedList
                List<Integer> list = res.poll();

                //Insert current number n at all possible indices of list, do this for all lists in res
                for (int j = 0; j <= list.size(); j++) {
                    List<Integer> update = new ArrayList(list);
                    update.add(j, n);
                    res.add(update);
                }
            }
        }
        return res;
    }
}



//Another version, same idea
/*
Time and space complexity are both O(n*(n!)).
Space: It doesn't alloc any extra space, just copy and concat the buffers to form 
the output. There is n! output and each has n elements.

Time: it doesn't do any extra computation but we need to copy N elements into 
List<Integer> update for N! operations.
O(N) per list copied in List<Integer> update * N! times = O(N*N!)
So time complexity is also O(N*N!)
*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        
        //Iterate though every number n in nums
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> res_updated = new LinkedList<>();
            //Iterate through each list/permutation in res
            for (List<Integer> list : res) {
                //Insert current number nums[i] at all possible indices of each list in res
                for (int j = 0; j <= i; j++) {
                    List<Integer> update = new ArrayList(list);
                    update.add(j, nums[i]);
                    res_updated.add(update);
                }
            }
            res = res_updated;
        }
        return res;
    }
}