/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

https://leetcode.com/problems/lfu-cache/description/
*/
class Node {
    int key, value, freq;
    Node next, prev;
    Node(int k, int v) {
        key=k;
        value=v;
        freq=1;
        next=prev=null;
    }
}

class DLL {
    Node first, last;
    int size;
    DLL() {
        first=new Node(-1, -1);
        last=new Node(-1, -1);
        first.next=last;
        last.prev=first;
        size=0;
    }
    boolean isEmpty() {
        return size==0;
    }
    void addFirst(Node node) {
        node.next=first.next;
        first.next=node;
        node.prev=first;
        node.next.prev=node;
        size++;
    }
    void remove(Node node) {
        node.next.prev=node.prev;
        node.prev.next=node.next;
        size--;
    }
    Node removeLast() {
        if(isEmpty())
            return null;
        Node node=last.prev;
        remove(node);
        return node;
    }
}

class LFUCache {
    private final Map<Integer, Node> nodeMap;
    private final Map<Integer, DLL> freqMap;
    private final int capacity;
    private int minFreq;
    public LFUCache(int capacity) {
        this.capacity=capacity;
        nodeMap=new HashMap();
        freqMap=new HashMap();
        minFreq=1;
    }

    public int get(int key) {
        if(!nodeMap.containsKey(key))
            return -1;
        Node node=nodeMap.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity<=0)
            return;
        if(nodeMap.containsKey(key)) {
            Node node=nodeMap.get(key);
            node.value=value;
            updateFrequency(node);
            return;
        }
        if(nodeMap.size()==capacity) {
            DLL dl=freqMap.get(minFreq);
            Node node=dl.removeLast();
            nodeMap.remove(node.key);
        }
        Node newNode=new Node(key, value);
        minFreq=1;
        freqMap.computeIfAbsent(minFreq, k->new DLL()).addFirst(newNode);
        nodeMap.put(key, newNode);
    }

    private void updateFrequency(Node node) {
        int oldFreq=node.freq;
        node.freq++;
        DLL dl=freqMap.get(oldFreq);
        dl.remove(node);
        if(dl.isEmpty() && minFreq==oldFreq)
            minFreq++;
        freqMap.computeIfAbsent(oldFreq+1, k->new DLL()).addFirst(node);
    }
}

void main() {
    LFUCache cache=new LFUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    IO.println(cache.get(2));
    cache.put(3, 3);
    IO.println(cache.get(3));
    IO.println(cache.get(2));
    cache.put(4, 4);
    IO.println(cache.get(1));
    IO.println(cache.get(3));
    IO.println(cache.get(4));
}