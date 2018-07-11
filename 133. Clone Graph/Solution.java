/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

/***
思路：克隆图的一个难点就是一个结点的邻居可能在已经出现过，这样你只要把他的指针加到邻居集合中即可，也有可能这个结点
还没出现过，因此你需要新建一个这个结点，因此我们需要一个hash表来对结点做一一映射．

本题有两种方法来做，广度搜索BFS和深度搜索DFS.
*/
//Recursive DFS, this method can also be done iteratively through Stack
//Time = O(V+E), it visited every node and every edge once.
//Space = O(V), stores every visted node
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        //map stores visited nodes to avoid double new/endless loop
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return clone(node, map);
    }
    
    private UndirectedGraphNode clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        //Since all neighbors in node.neighbors won't be null, there is no need to check null noes
        //if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);
        
        //Line 22: if the node has already been constructed, no need for duplicate construction
        //to avoid double new/endless loop/repeated clone neighbor operation
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);
        
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor, map));
        }
        return clone;
    }
}
/***
DFS: solved by Recursion (return type: Node, return the node that we need to add==onstruct==to return)
or by Stack(LIFO, go deeper)
BFS: solved by Queue(LIFO)

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
查重需要储存visited node，在所有能储存的data structures中Map的search time为O(1),
所以遇到查重题（clone node-->duplicate new, graph-->endless loop, etc.)时首先想到用HashMap查重！！！！！
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*/

//BFS
//I believe the time complexity is O(V+E) in the worst case, where V is the # of nodes and E is the number of Edges(connections). 
//I want to say space complexity would be O(V) in the worst case, since there can be a node that is connected to all other nodes.
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>(); //stores visited nodes to avoid duplicate new/endless loop/duplicate neighbor construction
        Queue<UndirectedGraphNode> bfs = new LinkedList<>(); //stores nodes that need to be cloned in BFS order
        
        UndirectedGraphNode res = new UndirectedGraphNode(node.label);
        bfs.add(node);
        map.put(node, res); //put the first (original, clone) pair into map
        
        while (!bfs.isEmpty()) {
            UndirectedGraphNode root = bfs.poll();
            UndirectedGraphNode clone = map.get(root);
            
            for (UndirectedGraphNode neighbor : root.neighbors) {
                if (!map.containsKey(neighbor)) {
                    bfs.add(neighbor); //only add neighbor to bfs if it isn't already added, no repeated add
                    UndirectedGraphNode neighbor_clone = new UndirectedGraphNode(neighbor.label);
                    clone.neighbors.add(neighbor_clone);
                    map.put(neighbor, neighbor_clone);
                }
                else clone.neighbors.add(map.get(neighbor));
            }
        }
        return res;
    }
}

//另一种写法：原理一模一样，只是line94-102写法有区别
public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    LinkedList<UndirectedGraphNode> bfs = new LinkedList<>();
    UndirectedGraphNode cloneNode = null;
    Map<UndirectedGraphNode, UndirectedGraphNode> parentToClone = new HashMap<>();
    if (node != null)
    {
        cloneNode = new UndirectedGraphNode(node.label);
        parentToClone.put(node, cloneNode);
        bfs.addLast(node);

    }
    
    while(!bfs.isEmpty())
    {
        UndirectedGraphNode root = bfs.poll();
        UndirectedGraphNode clone = parentToClone.get(root);
        List<UndirectedGraphNode> neighbors = root.neighbors;
        for(UndirectedGraphNode childNode : neighbors)
        {
            UndirectedGraphNode cloneChildNode = (parentToClone.get(childNode) != null) 
                                                ? parentToClone.get(childNode)
                                                : new UndirectedGraphNode(childNode.label);
            if (parentToClone.get(childNode) == null)
            {
                bfs.addLast(childNode);
            }
            clone.neighbors.add(cloneChildNode);
            parentToClone.putIfAbsent(childNode, cloneChildNode);
        }
        
        
    }
    
    return cloneNode;
}