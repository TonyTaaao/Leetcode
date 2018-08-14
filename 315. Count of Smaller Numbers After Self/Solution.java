// beats 11%
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int size = nums.length;
        List<Integer> copy = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < size; i++) {
            if (visited.contains(nums[i])) {
                continue;
            }
            copy.add(nums[i]);
            visited.add(nums[i]);
        }
        Collections.sort(copy);
        Map<Integer, Integer> map = new HashMap<>(); // number --> sorted position
        int num = copy.size();
        System.out.println("num = " + num + "  size = " + size);
        for (int i = 0; i < num; i++) {
            map.put(copy.get(i), i + 1);
        }
        
        LinkedList<Integer> ans = new LinkedList<>();
        
        BinaryIndexedTree tree = new BinaryIndexedTree(nums);
        for (int i = size - 1; i >= 0; i--) {
            int index = map.get(nums[i]);
            System.out.print(nums[i] + "'s index = " + index);
            tree.update(index);
            ans.addFirst(tree.sum(index-1));
        }
        System.out.println();
        return ans;
    }
}

class BinaryIndexedTree {
    //int[] f;
    int[] c; // sorted order
    int[] tree;
    int size;
    
    public BinaryIndexedTree(int[] nums) {
        size = nums.length;
        //f = new int[size + 1];
        c = new int[size + 1];
        tree = new int[size + 1];
    }
    
    public int lowbit(int x) {
        return x & (-x);
    }
    
    public int sum(int idx) {
        int i = idx, sum = 0;
        while (i > 0) {
            sum += tree[i];
            i = i - lowbit(i);
        }
        return sum;
    }
    
    public void update(int i) {
        while (i <= size) {
            tree[i]++;
            i += lowbit(i);
        }
    }
}