class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        
        f[0] = true;
        
        
        /* First DP
        for(int i = 1; i <= s.length(); i++){
            for(String str: dict){
                if(str.length() <= i){
                    if(f[i - str.length()]){
                        if(s.substring(i-str.length(), i).equals(str)){
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }*/
        

        //Start i from 1, not 0 because:
        //public String substring(int beginIndex, int endIndex)
        // The substring begins at the specified beginIndex and extends to the character 
        //at index endIndex - 1. Thus the length of the substring is endIndex-beginIndex.

        //Second DP
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
}

//Easy to understand:
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] matched = new boolean[s.length() + 1]; 
        matched[0] = true;
        
        //find the LONGEST match possible: 
        //This is for cases like s="aaaaa", ["aa","aaa"] --> return true
        //if don't find longest possible match, s = "aa"(found) + "aa"(found) + "a"(not found), return false
        //but actually, it should be s = "aa"(found) + "aaa"(found) -->return true
        
        for (int tail = 1; tail < s.length()+1; tail++) {
            for (int head = 0; head < tail; head++) {//head always start with 0, this is to find the longest possible match
                if (matched[head] && wordDict.contains(s.substring(head, tail))) {
                    matched[tail] = true;
                    break;
                }
            }
        }
        return matched[s.length()];  
    }
}