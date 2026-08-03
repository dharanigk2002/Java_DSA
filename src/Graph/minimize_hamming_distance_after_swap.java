/*
Minimize Hamming Distance After Swap Operations

You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.

The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).

Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.

https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/description/
*/
class DSU {
    private final int[] parent;
    private final int[] rank;

    public DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }

    public int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }

    public void union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
    }
}

public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    int n=source.length;
    DSU ds=new DSU(n);
    for(int[] swap:allowedSwaps)
        ds.union(swap[0], swap[1]);
    Map<Integer, Map<Integer, Integer>> map=new HashMap();
    for(int i=0;i<n;i++) {
        int root=ds.find(i);
        map.computeIfAbsent(root, k->new HashMap<>()).merge(source[i], 1, Integer::sum);
    }
    int diff=0;
    for(int i=0;i<n;i++) {
        int root=ds.find(i);
        Map<Integer, Integer> freq=map.get(root);
        int count=freq.getOrDefault(target[i], 0);
        if(count>0) {
            count--;
            if(count==0)
                freq.remove(target[i]);
            else
                freq.put(target[i], count);
        } else
            diff++;
    }
    return diff;
}

void main() {
    int[] source = {1,2,3,4}, target = {2,1,4,5};
    int[][] allowedSwaps = {{0,1},{2,3}};
    System.out.println(minimumHammingDistance(source, target, allowedSwaps));
}