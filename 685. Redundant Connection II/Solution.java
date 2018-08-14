// beats 2.89%
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int size = edges.length;
        int[] heads = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            heads[i] = i;
        }
        // node --> number of incoming edges, if a node has more than 1 incoming edge, it means it has 
        // two or more roots (causing a loop), and we must delete some of those edges until this node
        // has only 1 incoming edge, aka only 1 root node.
        int loopNode = -1;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int incomingNode = edges[i][1];
            if (!set.contains(incomingNode)) {
                set.add(incomingNode);
            } else {
                loopNode = incomingNode;
                break;
            }
        }
        System.out.println(loopNode);
        
        int[] ans = new int[2];
        List<int[]> troubleEdges = new LinkedList<>(); // edges whose end is loopNode.
        for (int i = 0; i < size; i++) {
            int v = edges[i][1];
            if (v == loopNode) {
                troubleEdges.add(edges[i]);
                continue;
            }
            int u = edges[i][0];
            int head_u = findHead(heads, u);
            int head_v = findHead(heads, v);
            System.out.println("u: "+u+", v:" + v);
            if (head_u != head_v) {
                heads[head_v] = head_u;
            } else {
                // head_u == head_v, meaning they are already on the same path
                ans[0] = u;
                ans[1] = v;
                return ans;
            }
        }
        for (int[] edge : troubleEdges) {
            int u = edge[0];
            System.out.println("u: "+u+", v:" + loopNode);
            int head_u = findHead(heads, u);
            int head_v = findHead(heads, loopNode);
            if (head_u == head_v) {
                ans[0] = u;
                ans[1] = loopNode;
                break;
            } else {
                heads[head_v] = head_u;
            }
        }
        return ans;
    }
    
    // return the head of path which node x belongs to.
    private int findHead(int[] heads, int x) {
        if (heads[x] == x) return x;
        else {
            heads[x] = findHead(heads, heads[x]);
            return heads[x];
        }
    }
}