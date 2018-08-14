// Use a hashmap to store K-V pairs
// Use a doubly-linkedlist (prev, next) to perform get() and put() in O(1) time complexity.
public class LRUCache {
    int capacity;
    Map<Integer, Node> cache;
    Node head = null;
    Node tail = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
        head = new Node(0,0);
        tail = new Node(0,0);
        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {
        int res = -1;
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            moveToHead(n);
            res = n.value;
        }
        return res;
    }

    public void put(int key, int value) {
//        Node node = new Node(key, value);
        Node node = cache.get(key);

        if (cache.containsKey(key)) { // update the key and move to front
            node.value = value;
            this.moveToHead(node);
        } else {        // insert the key to the front after popTail (if full)
            if (cache.size() == capacity) {
                Node tail = this.popTail();
                cache.remove(tail.key);
            }
            Node newNode = new Node(key, value);
            this.addToHead(newNode);
            cache.put(key, newNode);
        }
//        cache.put(key, node);
    }

    // add node to the real-head, so the recent location
    private void addToHead(Node node) {
        node.pre = head;
        node.post = head.post;

        head.post.pre = node;
        head.post = node;
    }

    private void removeNode(Node node) {
        Node pre = node.pre;
        Node post = node.post;
        pre.post = post;
        post.pre = pre;
    }

    private void moveToHead(Node node) {
        this.removeNode(node);
        this.addToHead(node);
    }

    private Node popTail() {
        Node res = tail.pre;
        this.removeNode(res);
        return res;
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node post;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 );
        cache.put(2, 1);
        cache.put(2, 2);
        cache.get(2);       // returns 1
        cache.put(1, 1);    // evicts key 2
        cache.put(4, 1);
        cache.get(2);
    }
}



// another version
class DLinkedNode {
    int key;
    int value;
    DLinkedNode pre;
    DLinkedNode post;
}

/**
 * Always add the new node right after head;
 */
private void addNode(DLinkedNode node){
    node.pre = head;
    node.post = head.post;
    
    head.post.pre = node;
    head.post = node;
}

/**
 * Remove an existing node from the linked list.
 */
private void removeNode(DLinkedNode node){
    DLinkedNode pre = node.pre;
    DLinkedNode post = node.post;
    
    pre.post = post;
    post.pre = pre;
}

/**
 * Move certain node in between to the head.
 */
private void moveToHead(DLinkedNode node){
    this.removeNode(node);
    this.addNode(node);
}

// pop the current tail. 
private DLinkedNode popTail(){
    DLinkedNode res = tail.pre;
    this.removeNode(res);
    return res;
}

private Hashtable<Integer, DLinkedNode> 
    cache = new Hashtable<Integer, DLinkedNode>();
private int count;
private int capacity;
private DLinkedNode head, tail;

public LRUCache(int capacity) {
    this.count = 0;
    this.capacity = capacity;

    head = new DLinkedNode();
    head.pre = null;
    
    tail = new DLinkedNode();
    tail.post = null;
    
    head.post = tail;
    tail.pre = head;
}

public int get(int key) {
    
    DLinkedNode node = cache.get(key);
    if(node == null){
        return -1; // should raise exception here.
    }
    
    // move the accessed node to the head;
    this.moveToHead(node);
    
    return node.value;
}


public void set(int key, int value) {
    DLinkedNode node = cache.get(key);
    
    if(node == null){
        
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;
        
        this.cache.put(key, newNode);
        this.addNode(newNode);
        
        ++count;
        
        if(count > capacity){
            // pop the tail
            DLinkedNode tail = this.popTail();
            this.cache.remove(tail.key);
            --count;
        }
    }else{
        // update the value.
        node.value = value;
        this.moveToHead(node);
    }
    
}