/*
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/
*/

class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int u, int v) {
        int pu=findPar(u), pv=findPar(v);
        if(pu==pv)
            return true;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return false;
    }
    int findPar(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findPar(parent[node]);
    }
}

public int removeStones(int[][] stones) {
    int maxRow=0, maxCol=0, n=0;
    for(int[] stone:stones) {
        n++;
        maxRow=Math.max(maxRow, stone[0]);
        maxCol=Math.max(maxCol, stone[1]);
    }
    maxRow++;
    maxCol++;
    DSU ds=new DSU(maxRow+maxCol);
    Set<Integer> parent=new HashSet();
    for(int[] stone:stones) {
        int nodeRow=stone[0], nodeCol=stone[1]+maxRow;
        ds.union(nodeRow, nodeCol);
        parent.add(nodeRow);
        parent.add(nodeCol);
    }
    int components = 0;
    for(int p:parent)
        if(p==ds.findPar(p))
            components++;
    return n-components;
}

void main() {
    int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
    int stonesRemoved = removeStones(stones);
    /*
        Explanation: One way to remove 5 stones is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,1].
        2. Remove stone [2,1] because it shares the same column as [0,1].
        3. Remove stone [1,2] because it shares the same row as [1,0].
        4. Remove stone [1,0] because it shares the same column as [0,0].
        5. Remove stone [0,1] because it shares the same row as [0,0].
        Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
    */
    IO.println(stonesRemoved);
}