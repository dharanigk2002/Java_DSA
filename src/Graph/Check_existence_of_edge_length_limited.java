/*
An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.

Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j] whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .

Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true if there is a path for queries[j] is true, and false otherwise.

https://leetcode.com/problems/checking-existence-of-edge-length-limited-paths/
*/

class DSU {
    int[] parent;
    int[] rank;

    DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path Compression
        }
        return parent[x];
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return;

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
    }
}

boolean[] distanceLimitedPathsExist(int m, int[][] edgeList, int[][] queries) {
    int n=queries.length;
    DSU ds=new DSU(m);
    int[][] offlineQueries=new int[n][4];
    for(int i=0;i<n;i++) {
        offlineQueries[i][0]=queries[i][0];
        offlineQueries[i][1]=queries[i][1];
        offlineQueries[i][2]=queries[i][2];
        offlineQueries[i][3]=i;
    }
    boolean[] ans=new boolean[n];
    Arrays.sort(offlineQueries, (a, b)->Integer.compare(a[2], b[2]));
    Arrays.sort(edgeList, (a, b)->Integer.compare(a[2], b[2]));
    int j=0;
    for(int[] q:offlineQueries) {
        int u=q[0], v=q[1], limit=q[2], idx=q[3];
        while(j<edgeList.length && edgeList[j][2]<limit)
            ds.union(edgeList[j][0], edgeList[j++][1]);
        ans[idx]=(ds.find(u)==ds.find(v));
    }
    return ans;
}

void main() {
    int n = 3;
    int[][] edgeList = {{0,1,2},{1,2,4},{2,0,8},{1,0,16}};
    int[][] queries = {{0,1,2},{0,2,5}};
    IO.println(Arrays.toString(distanceLimitedPathsExist(n, edgeList, queries)));
}