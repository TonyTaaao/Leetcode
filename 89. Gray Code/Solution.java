//10% runtime distribution
class Solution {
    public List<Integer> grayCode(int n) {
        LinkedList<Integer> res = new LinkedList<>();
        res.add(0);
        if (n == 0) return res;
        
        int base = 1; //base = 2^(n-1)
        while (n-- != 0) {
            LinkedList<Integer> append = new LinkedList<>();
            for (Integer i : res) {
                append.addFirst(base + i);
            }
            res.addAll(append);
            base *= 2;
        }
        return res;
    }
}



//Solution 2, using bit manipulation
//100% runtime distribution (fastest)
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> rs=new ArrayList<Integer>();
        rs.add(0);
        for(int i=0;i<n;i++){
            int size=rs.size();
            for(int k=size-1;k>=0;k--)
                rs.add(rs.get(k) | 1<<i); //rs.add(rs.get(k) + (1<<i)); is the same
        }
        return rs;
    }
}