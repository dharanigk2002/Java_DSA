/*
Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/
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
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return false;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return true;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
    int m=edges.length;
    int[][] e=new int[m][4];
    for(int i=0;i<m;i++) {
        e[i][0]=edges[i][0];
        e[i][1]=edges[i][1];
        e[i][2]=edges[i][2];
        e[i][3]=i;
    }
    Arrays.sort(e, (a, b)->Integer.compare(a[2], b[2]));
    int originalMst=mst(n, e, -1, -1);
    List<Integer> critical=new ArrayList();
    List<Integer> pseudo=new ArrayList();
    for(int i=0;i<edges.length;i++) {
        int skip=mst(n, e, i, -1);
        if(skip>originalMst) {
            critical.add(e[i][3]);
            continue;
        }
        int force=mst(n, e, -1, i);
        if(force==originalMst)
            pseudo.add(e[i][3]);
    }
    return List.of(critical, pseudo);
}
private int mst(int n, int[][] edges, int skip, int force) {
    DSU ds=new DSU(n);
    int weight=0, used=0;
    if(force!=-1) {
        if(ds.union(edges[force][0], edges[force][1])) {
            weight+=edges[force][2];
            used++;
        }
    }
    for(int i=0;i<edges.length;i++) {
        if(i==skip) continue;
        int[] edge=edges[i];
        if(ds.union(edge[0], edge[1])) {
            weight+=edge[2];
            used++;
        }
        if(used==n-1) break;
    }
    return used==n-1 ? weight : Integer.MAX_VALUE;
}

void main() {
    int[][] edges = {{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}};
    int n=5;
    IO.println(findCriticalAndPseudoCriticalEdges(n, edges));
}