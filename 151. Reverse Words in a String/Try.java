public class Solution {
    public String reverseWords(String s) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < s.length()) {
            s.append(s.charAt(index));
            if (s.charAt(index) == ' ') {
                while (index < s.length() && s.charAt(index) == ' ') index++;
            } else {
                index++;
            }
        }
        return sb.toString();
    }
}