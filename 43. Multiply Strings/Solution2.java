//beats 88% runtime
//Time Complexity = O(M*N), M=length of num1, N=length of num2
//due to two for loops
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) return "0";
        
        int l1 = num1.length(), l2 = num2.length();
        int[] pos = new int[l1 + l2];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                product = product + pos[p2];
                
                pos[p1] += product / 10;
                pos[p2] = product % 10;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for (int p : pos) res.append(p);
        if (res.charAt(0) == '0') res.delete(0, 1);
        return res.toString();
    }
}



//Version2, same idea
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) return "0";
        
        int l1 = num1.length(), l2 = num2.length();
        int[] pos = new int[l1 + l2];
        
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                product = product + pos[p2];
                
                pos[p1] += product / 10;
                pos[p2] = product % 10;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int p : pos) {
            if(!(res.length() == 0 && p == 0)) res.append(p);
        }
        return res.toString();
    }
}