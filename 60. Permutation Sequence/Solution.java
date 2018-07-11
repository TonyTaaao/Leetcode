/*Convert Integer to String:
int a = 10;
1) String str = Integer.toString(a);
2) String str = "" + a;
*/

//Time Complexity = O(N^2) worst case
//Iterate from 1 to N, each time ArrayList.remove(index) takes O(N) time
//So the total runtime is O(N^2)
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n];
        StringBuilder sb = new StringBuilder();
        
        //Build up the factorial table, factorial[n] = n!
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = i*factorial[i-1];
        }
        
        //ArrayList is easy to remove visited elements, unlike array (needs left right)
        for (int i = 1; i <= n; i++) nums.add(i);
        
        k = k - 1; //Since index starts with 0 not 1, substract k by 1

        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(Integer.toString(nums.get(index))); //OR String.valueOf(int)
            nums.remove(index);
            k -= index*factorial[n - i];
        }
        return sb.toString(); //of String.valueOf(StringBuilder)
    }
}