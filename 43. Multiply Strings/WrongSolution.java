//does not work if num1 * num2 > Integer.MAX_VALUE == 2^31-1
class Solution {
    public String multiply(String num1, String num2) {
        int res = 0;
        int base = 1;
        for (int i = num2.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(num2.charAt(i));
            if (digit == 0) {
                base *= 10;
                continue;
            }
            
            int base2 = 1;
            int product = 0;
            int overhead = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                int digit2 = Character.getNumericValue(num1.charAt(j));
                int curProduct = digit * digit2 + overhead;
                overhead = curProduct / 10;
                curProduct = curProduct % 10;
                product += curProduct * base2;
                base2 *= 10;
            }
            if (overhead > 0) product += overhead * base2;
            res += product * base;
            base *= 10;
        }
        return String.valueOf(res);
    }
}



//Also wrong
class Solution {
    public String multiply(String num1, String num2) {
        /*
        if (num2.length() > num1.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        */
        StringBuilder res = new StringBuilder("0");
        int base = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(num2.charAt(i));
            if (digit == 0) {
                base++;
                continue;
            }
            
            StringBuilder product = new StringBuilder();
            int overhead = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                int digit2 = Character.getNumericValue(num1.charAt(j));
                int curProduct = digit * digit2 + overhead;
                overhead = curProduct / 10;
                curProduct = curProduct % 10;
                product.insert(0, curProduct);
            }
            if (overhead > 0) product.insert(0, overhead);
            
            String firstHalf = (res.length() - base > 0) ? res.substring(0, res.length() - base) : "0";
            String secondHalf = (res.length() - base > 0) ? res.substring(res.length() - base, res.length()) : res.toString();
            res = new StringBuilder();
            res.append(Integer.parseInt(firstHalf) + Integer.parseInt(product.toString()));
            res.append(secondHalf);
            
            base++;
        }
        return res.toString();
    }
}









class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0")) return "0";
        
        StringBuilder res = new StringBuilder();
        //int base = 0; //base == i in the first for loop, so we don't need it, just use i
        int overhead = 0;
        for (int i = 0; i < num2.length(); i++) {
            int digit = Character.getNumericValue(num2.charAt(num2.length() - 1 - i));
            if (digit == 0) continue;
            
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
                /*
                overhead = overhead + overhead2;
                res.insert(0, overhead % 10);
                overhead /= 10;
                */
                overhead = overhead + overhead2;
                res.insert(0, overhead);
                overhead = 0;
            }
            //base++;
        }
        if (overhead > 0) res.insert(0, overhead);
        
        return res.toString();
    }
}