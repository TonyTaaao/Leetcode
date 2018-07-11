//runtime beats 61.52% java submissions
//Version1, iterate from start to end
class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + s.charAt(i) -'A' + 1;
        }
        return res;
    }
}


//Version2, iterate backwards, 61.52% runtime
class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        int base = 1; //each time we move to the next index, base = base * 26
        for (int i = s.length() - 1; i >=0; i--) {
            char letter = s.charAt(i);
            res += base * (letter - 'A' + 1);
            base *= 26;
        }
        return res;
    }
}



//Version3, change String s to char array.
//runtime doesn't become faster, same as 61.52%
class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        int base = 1; //each time we move to the next index, base = base * 26
        char[] arr = s.toCharArray();
        for (int i = arr.length - 1; i >=0; i--) {
            res += base * (arr[i] - 'A' + 1);
            base *= 26;
        }
        return res;
    }
}