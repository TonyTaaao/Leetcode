//Palindrome: 回文
//Two Pointer Solution
class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        char[] arr = s.toLowerCase().toCharArray();
        int left = 0; //left pointer
        int right = arr.length - 1; //right pointer
        while (left < right) {
            while (left < arr.length && !Character.isLetterOrDigit(arr[left])) {
                left++;
            }
            while (right >= 0 && !Character.isLetterOrDigit(arr[right])) {
                right--;
            }
            if (left >= right) return true;
            if (arr[left] != arr[right]) return false;
            left++;
            right--;
        }
        return true;
    }
}



//Another version (in C++)
bool isAlphanumeric(const char c) {
    if ('A' <= c && c <= 'Z')
        return true;
    else if ('a' <= c && c <= 'z')
        return true;
    else if ('0' <= c && c <= '9')
        return true;
    return false;
}

char toLower(const char c)
{
    if ('A' <= c && c <= 'Z')
        return 'a' - 'A' + c;
    return c;
}

bool isPalindrome(string s)
{
    if (s.empty())
        return true;
    int begin = 0;
    int end = s.size() - 1;
    while (true) {
        while (begin < s.size() && !isAlphanumeric(s[begin])){
            begin++;
        }
        while (end >= 0 && !isAlphanumeric(s[end])) {
            end--;
        }
        if (end <= begin)
            return true;
        if (toLower(s[begin]) != toLower(s[end])){
            return false;
        }
        begin++;
        end--;
    }
}
