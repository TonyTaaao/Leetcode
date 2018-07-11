//runtime beats 100% java submissions
//Time Complexity = O(log(26, n))
class Solution {
    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            int remainder = n % 26;
            if (remainder == 0) remainder = 26;
            res.insert(0, Character.toChars('A' + remainder - 1));
            if (n <= 26) break;
            n = (n - remainder) / 26;
        }
        return res.toString();
    }
}



//Version2, same idea, no special case about 'Z' through n = n-1 at line 26.
//runtime beats 100% java submissions
//Time Complexity = O(log(26, n))
class Solution {
    public String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            n--;
            res.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return res.toString();
    }
}



/*Convert int to char:
1. char c = Character.toChars('A' + 1)); //c = 'B'
   char c = Character.toChars(65); //c = 'A'
2. char c = (char) ('A' + 1); //c = 'B'
*/