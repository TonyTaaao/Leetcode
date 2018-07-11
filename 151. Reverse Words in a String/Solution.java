//beats 77.74% runtime
//Two Pointers method, one pointer is start of current word, another is end of current word
//When we meet an empty space, we add current word(from s[start] to s[end]) to sb.
//One pass solution, without trim() built-in method
//Time Complexity = O(N), N = s.length()
public class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1; //end of a complete word
        int start = end; //start of a complete word
        
        //if s[start] == ' ', we add s[start + 1, end] (the currrent word) to sb
        //otherwise, move start leftwise by 1
        while (start >= 0) {
            if (s.charAt(start) == ' ') {
                sb.append(s.substring(start + 1, end + 1));
                sb.append(' ');
                start--;
                while (start >= 0 && s.charAt(start) == ' ') start--;
                end = start;
            } else {
                start--;
            }
        }
        if (s.charAt(0) != ' ') sb.append(s.substring(0, end + 1));
        
        if (sb.charAt(0) == ' ') sb.deleteCharAt(0);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() - 1);
        
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution soln = new Solution();
        String s = "a";
        String ans = soln.reverseWords(s);
        System.out.println(ans);
        System.out.println(ans.length());
    }
}


//version2, same idea, clearer logic
//different in line 68-73
//beats 82.41%
public class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        int end = s.length() - 1; //end of a complete word
        int start = end; //start of a complete word
        
        //if s[start] == ' ', we add s[start + 1, end] (the currrent word) to sb
        //otherwise, move start leftwise by 1
        while (start >= 0) {
            if (s.charAt(start) == ' ') {
                sb.append(s.substring(start + 1, end + 1));
                sb.append(' ');
                start--;
                while (start >= 0 && s.charAt(start) == ' ') start--;
                end = start;
            } else {
                start--;
            }
        }
        if (s.charAt(0) != ' ') sb.append(s.substring(0, end + 1));
        //if s starts with empty space, sb will end with an empty space, delete that empty space
        else sb.deleteCharAt(sb.length() - 1);
        //if s ends with empty space, sb will start with an empty space, delete that empty space
        //after the while loop at line 55, sb.length() will be at least 1 (sb is at least " ")
        //at line 68, we may delete sb once, so sb may already become an empty string
        //so we have to check if sb is empty before delete sb[0]-->avoid IndexOutOfBoundsException
        if (sb.length() > 0 && s.charAt(s.length() - 1) == ' ') sb.deleteCharAt(0);
        
        return sb.toString();
    }
}