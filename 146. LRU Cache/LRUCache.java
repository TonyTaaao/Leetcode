// beats 5.16%
class LRUCache {
    private int capacity;
    private int currentLength;
    private Map<Integer, Integer> map;
    private LinkedList<Integer> queue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        currentLength = 0;
        map = new HashMap<>();
        queue = new LinkedList<>();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            // if key is in cache
            queue.remove(new Integer(key));
            queue.addFirst(key);
            return map.get(key);
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            currentLength++;
            queue.addFirst(key);
            if (currentLength > capacity) {
                int deletedKey = queue.removeLast();
                map.remove(new Integer(deletedKey));
                currentLength--;
            }
        } else {
            queue.remove(new Integer(key));
            queue.addFirst(key);
        }
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */