class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        String ans = "";
        int count = 1;
        helper(res, ans, count, s);
        return res;
    }
    
    private void helper(List<String> res, String ans, int count, String s) {
        if (s.length() == 0) return;
        
        //End case of recursion
        if (count == 4) {
            if (s.length() > 3 || Integer.parseInt(s) > 255) return;
            //To avoid cases like "00.1.0.0" or "0.01.0.0"
            else if (s.length() > 1 && s.substring(0, 1).equals("0")) {
                return;
            }
            else {
                ans += s;
                res.add(new String(ans));
                return;
            }
        }
        
        for (int i = 0; i < s.length() && Integer.parseInt(s.substring(0, i + 1)) <= 255; i++) {
            //To avoid cases like "00.1.0.0" or "0.01.0.0"
            if (i > 0 && s.substring(0, 1).equals("0")) continue;
            helper(res, ans + s.substring(0, i + 1) + ".", count + 1, s.substring(i+1, s.length()));
        }
    }
}



//Another version
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0);
        return result;
    }
    
    private void doRestore(List<String> result, String path, String s, int count) {
        if (s.isEmpty() || count == 4) {
            if (s.isEmpty() && count == 4)
                result.add(path.substring(1)); //substring(beginIndex) -->remove the first "."
            return;
        }

        //substring valid length is at most 3
        //if s begins with a 0, only check index 0, since anything start with '0' with length > 1 is invalid.
        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) { // Avoid leading 0
            String part = s.substring(0, i);
            if (Integer.valueOf(part) <= 255)
                doRestore(result, path + "." + part, s.substring(i), count + 1);
        }
    }
}