//regular expression solution
//beats 56.57%
public class Solution {
  
  public String reverseWords(String s) {
    /*The first \ means escape character (start of regular expression)
    \s : matches single whitespace character
    + : matches one or more occurrences of the preceding subexpression
    */
    //regular expression "\\s+" means: split when we meet one or more whitespaces
    String[] parts = s.trim().split("\\s+"); //trim(): trim whitespace at the beginning and end of a string.
    StringBuilder res = new StringBuilder();
    for (int i = parts.length - 1; i > 0; i--) {
        res.append(parts[i]).append(' ');
    }
    res.append(parts[0]);
    return res.toString();
  }

}