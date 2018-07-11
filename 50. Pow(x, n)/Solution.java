class Solution {
    public double myPow(double x, int n) {
        //edge cases
        if (x == 0) return 0; 
        if (x == 1) return 1;
        if (x == -1) return (n % 2 == 0) ? 1 : -1;
        //prevent stack overflow
        if (n == Integer.MIN_VALUE) return 1/(Pow(x, Integer.MAX_VALUE)*2);
        
        if (n >= 0) return Pow(x, n);
        else return 1/Pow(x, -n);
    }
    
    private double Pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        //n >= 2
        /*
        double base = x^2;
        int pow = n / 2;
        int remainder = n % 2;
        */
        return Pow(x*x/*x^2*/, n / 2) * Pow(x, n % 2); 
    }
}