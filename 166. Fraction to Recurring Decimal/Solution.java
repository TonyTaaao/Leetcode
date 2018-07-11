class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        //StringBuilder has faster run time than String when it is biult inside a loop
        StringBuilder  res = new StringBuilder();
        //positive or negative
        res.append(numerator == 0 || (numerator > 0 == denominator > 0) ? "" : "-");
        
        //Use long (8 bytes) instead of int (4 bytes) to prevent Integer overflow
        long num = Math.abs((long) numerator), deno = Math.abs((long) denominator);
        
        //Integer part
        res.append(num/deno);
        long remainder = num % deno;
        if (remainder == 0) return res.toString();
        
        //Fractional part
        res.append(".");
        Map<Long, Integer> map = new HashMap(); //remainder --> index to put "("
        map.put(remainder, res.length());
        
        while (remainder != 0) {
            remainder *= 10;
            res.append(remainder / deno);
            remainder %= deno;
            if (map.containsKey(remainder)) {
                res.insert(map.get(remainder), "(");
                res.append(")");
                break;
            } else map.put(remainder, res.length());
        }
        return res.toString();
    }
}