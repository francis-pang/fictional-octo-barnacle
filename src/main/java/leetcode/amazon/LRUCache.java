package leetcode.amazon;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 *  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * LRUCache cache = new LRUCache( 2 capacity);
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class LRUCache {
    private int capacity;
    private Map<Integer, Integer> lruCacheMap;
    private Queue<Integer> leastRecentlyUsedQueue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lruCacheMap = new HashMap<>(capacity + 1);
        leastRecentlyUsedQueue = new ArrayDeque<>(capacity);
    }

    public int get(int key) {
        if (lruCacheMap.containsKey(key)) {
            leastRecentlyUsedQueue.remove(key);
            leastRecentlyUsedQueue.offer(key);
            return lruCacheMap.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //System.out.println("Processing key " + key + " and value " + value + " at capacity " + this.capacity);
        lruCacheMap.put(key, value);
        if (lruCacheMap.size() > capacity) {
            int toBeRemovedKey = leastRecentlyUsedQueue.poll();
            lruCacheMap.remove(toBeRemovedKey);
        }
        leastRecentlyUsedQueue.remove(key);
        leastRecentlyUsedQueue.offer(key);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */