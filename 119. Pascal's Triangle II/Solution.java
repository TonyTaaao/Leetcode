//beats 99.88%
class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[] res = new int[rowIndex + 1];
        res[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                res[j] += res[j - 1];
            }
        }
        
        List<Integer> list = new LinkedList<>();
        for (int i : res) list.add(i);
        return list;
    }
}



// Version2, same idea
// beats 88.39%
class Solution {
    public List<Integer> getRow(int rowIndex) {
        // Constructor: ArrayList(int initialCapacity): Constructs an empty list with the specified initial capacity.
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            int size = res.size();
            for (int j = 0; j < size - 1; j++) {
                //res.get(j) += res.get(j + 1); -->WRONG
                res.set(j, res.get(j) + res.get(j + 1));
            }
            res.add(0, 1);
        }
        return res;
    }
}



// Version3, same idea
// beats 88.39%
class Solution {
    public List<Integer> getRow(int rowIndex) {
        // Constructor: ArrayList(int initialCapacity): Constructs an empty list with the specified initial capacity.
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            res.add(0, 1);
            int size = res.size();
            for (int j = 1; j < size - 1; j++) {
                res.set(j, res.get(j) + res.get(j + 1));
            }
        }
        return res;
    }
}