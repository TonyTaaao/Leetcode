//Time Complexity = 1 + 2 + 3 + ... + N = N^2/2 ~ O(N^2)
//beats 17.48%
class Solution {
    public List<List<Integer>> generate(int numRows) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (numRows == 0) return res;
        res.add(Arrays.asList(1));
        if (numRows-- == 1) return res;

        while (numRows-- > 0) {
            List<Integer> cur = new ArrayList<Integer>();
            //linkedlist is a queue, peek() retrieves head of list, peekLast() retrieves end of list
            //LinkedList in java is a doubly-linked list.
            List<Integer> prev = res.peekLast();
            cur.add(1);
            int size = prev.size();
            for (int i = 1; i < size; i++) {
                cur.add(prev.get(i - 1) + prev.get(i));
            }
            cur.add(1);
            res.add(cur); //DON'T FORGET THIS!!! add current layer to res
        }
        return res;
    }
}



//Version2, beats 17.48%
class Solution {
    public List<List<Integer>> generate(int numRows) {
    	//change res to arraylist, runtime will become much faster
        List<List<Integer>> res = new LinkedList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            row.add(0, 1);
            res.add(new ArrayList(row));
        }
        return res;
    }
}

//Version3, only 1 line different
//beats 99.87%
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(); //only difference with ver2
        //if you declare row as List, not ArrayList, runtime will be 17.48%
        //Why??? --Take notes
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < row.size() - 1; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
            row.add(0, 1);
            res.add(new ArrayList(row));
        }
        return res;
    }
}



//Version4
//beats 99.87%
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0){
            return res;
        }
        
        //iterate through each layer
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            //iterate through each element in layer i
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1);
                else {
                    List<Integer> last_layer = res.get(i - 1);
                    row.add(last_layer.get(j - 1) + last_layer.get(j));
                }
            }
            res.add(row);
        }
        return res;
    }
}