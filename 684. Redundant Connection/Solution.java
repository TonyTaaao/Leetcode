class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // node --> head of path
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        int size = edges.length;
        for (int i = 0; i < size; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (!map.containsKey(u) && !map.containsKey(v)) {
                map.put(u, u); // let u be the head of path
                map.put(v, u);
            } else if (!map.containsKey(u)) {
                map.put(u, map.get(v)); // put u on path v, which is head(v)
            } else if (!map.containsKey(v)) {
                map.put(v, map.get(u)); // put v on path u, which is head(u)
            } else {
                // map contains both u and v
                // if u and v are not on the same path
                int head_u = findHead(map, u);
                int head_v = findHead(map, v);
                if (head_u != head_v) {
                    map.put(v, head_u);
                    map.put(head_v, head_u); // merge path(u) and path(v)
                } else {
                    ans[0] = u;
                    ans[1] = v;
                    break;
                }
            }
        }
        return ans;
    }
    
    // Union Head Function, this is a must!!!
    private int findHead(HashMap<Integer, Integer> map, int x) {
        if (map.get(x) == x) return x;
        else {
            int head = findHead(map, map.get(x));
            map.put(x, head);
            return head;
        }
    }
}



// beats 95%
// Time Complexity = O(n), Union Find is constant time.
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] ans = new int[2];
        int size = edges.length;
        // heads[i] = path head of node i
        int[] heads = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            heads[i] = i;
        }
        
        int u, v;
        for (int i = 0; i <= size; i++) {
            u = edges[i][0];
            v = edges[i][1];
            int head_u = findHead(heads, u); // amortized O(1) complexity, so overall time complexity is O(n)
            int head_v = findHead(heads, v);
            if (head_u != head_v) {
                heads[head_v] = head_u;
            } else {
                ans[0] = u;
                ans[1] = v;
                break;
            }
        }
        return ans;
    }
    
    // Union Head Function, 回调的平均深度不超过4--> constant time complexity
    private int findHead(int[] heads, int x) {
        if (heads[x] == x) return x;
        else {
            heads[x] = findHead(heads, heads[x]);
            return heads[x];
        }
    }
}