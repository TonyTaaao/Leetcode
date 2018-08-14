// Union Find
// beats 61%
// Time COmplexity = O(n) ~ Union Find is constant time.
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 两条有问题的边（子节点有两个父亲）
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                // 找到了有两个父亲的子节点
                can2 = new int[] {edges[i][0], edges[i][1]}; // 当前的边
                can1 = new int[] {parent[edges[i][1]], edges[i][1]}; // 之前的边
                edges[i][1] = 0; // 标记当前（第二次）有问题的边
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue; // 遇到第二次有问题的边就跳过
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) { // 找到了环
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1; // 因为第二次有问题的边被跳过了，所以是第一条有问题的边导致了环
            }
            parent[child] = father;
        }
        return can2; // 第一条边没有问题（没有导致环），返回第二条边
    }
    
    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }   
        return i;
    }
}

