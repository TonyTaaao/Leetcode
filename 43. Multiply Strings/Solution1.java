//beats 13% runtime
//Time Complexity = O(M*N), M=length of num1, N=length of num2
//due to two for loops
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) return "0";
        
        StringBuilder res = new StringBuilder();
        //int base = 0; //base == i in the first for loop, so we don't need it, just use i
        int overhead = 0;
        for (int i = 0; i < num2.length(); i++) {
            int digit = Character.getNumericValue(num2.charAt(num2.length() - 1 - i));
            //if (digit == 0) continue;
            
            int product;
            int overhead2 = 0;
            for (int j = 0; j < num1.length(); j++) {
                int digit2 = Character.getNumericValue(num1.charAt(num1.length() - 1 - j));
                product = digit * digit2 + overhead2;
                overhead2 = product / 10;
                product = product % 10;
                
                if (res.length()-1-i-j >= 0) {
                    int a = Character.getNumericValue(res.charAt(res.length()-1 - i - j));
                    int sum = a + product + overhead;
                    overhead = sum / 10;
                    sum = sum % 10;
                    res.setCharAt(res.length()-1 - i - j, (char) (sum + '0'));
                } else {
                    product = product + overhead;
                    overhead = product / 10;
                    res.insert(0, product % 10);
                }
            }

            if (overhead2 > 0 || overhead > 0) {
                overhead = overhead + overhead2;
                res.insert(0, overhead);
                overhead = 0;
            }
            //base++;
        }
        //if (overhead > 0) res.insert(0, overhead);
        
        return res.toString();
    }
}




//Version 2, a bit faster due to skip digit == 0 case
//beats 14%
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) return "0";
        
        StringBuilder res = new StringBuilder();
        int overhead = 0;
        for (int i = 0; i < num2.length(); i++) {
            int digit = Character.getNumericValue(num2.charAt(num2.length() - 1 - i));
            if (digit == 0) {
                if (res.length() - 1 - i < 0) res.insert(0, '0');
                continue;
            }
            
            int product;
            int overhead2 = 0;
            for (int j = 0; j < num1.length(); j++) {
                int digit2 = Character.getNumericValue(num1.charAt(num1.length() - 1 - j));
                product = digit * digit2 + overhead2;
                overhead2 = product / 10;
                product = product % 10;
                
                if (res.length()-1-i-j >= 0) {
                    int a = Character.getNumericValue(res.charAt(res.length()-1 - i - j));
                    int sum = a + product + overhead;
                    overhead = sum / 10;
                    sum = sum % 10;
                    res.setCharAt(res.length()-1 - i - j, (char) (sum + '0'));
                } else {
                    product = product + overhead;
                    overhead = product / 10;
                    res.insert(0, product % 10);
                }
            }
            
            if (overhead2 > 0 || overhead > 0) {
                overhead = overhead + overhead2;
                res.insert(0, overhead);
                overhead = 0;
            }
        }
        
        return res.toString();
    }
}