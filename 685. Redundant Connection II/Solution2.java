class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int size = edges.length;
        int[] heads = new int[size + 1];
        int[] visit = new int[size + 1]; // v's number of occurrence
        for (int i = 0; i < size; i++) {
            int incomingNode = edges[i][1];
            heads[i] = i;
            visit[incomingNode]++;
        }
        
        int[] ans = new int[2];
        List<int[]> troubleEdges = new LinkedList<>(); // edges whose end is loopNode.
        for (int i = 0; i < size; i++) {
            int v = edges[i][1];
            if (visit[v] > 1) {
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
        for (int i = 0; i < size; i++) {
            int v = edges[i][1];
            if (visit[v] <= 1) {
                continue;
            }
            int u = edges[i][0];
            int head_u = findHead(heads, u);
            int head_v = findHead(heads, v);
            if (head_u == head_v) {
                ans[0] = u;
                ans[1] = v;
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