/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

https://leetcode.com/problems/sum-of-distances-in-tree/description/
*/
int[] ans, count;
int n;
public int[] sumOfDistancesInTree(int n, int[][] edges) {
    ans=new int[n];
    this.n=n;
    count=new int[n];
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] e:edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
    }
    dfs1(adj, 0, -1);
    dfs2(adj, 0, -1);
    return ans;
}
private void dfs1(List<List<Integer>> adj, int src, int parent) {
    count[src]=1;
    for(int next:adj.get(src)) {
        if(next==parent) continue;
        dfs1(adj, next, src);
        count[src]+=count[next];
        ans[src]+=ans[next]+count[next];
    }
}
private void dfs2(List<List<Integer>> adj, int node, int parent) {
    for(int next:adj.get(node)) {
        if(next==parent) continue;
        ans[next]=ans[node]+n-2*count[next];
        dfs2(adj, next, node);
    }
}
void main() {
    int[][] edges = {{0,1},{0,2},{2,3},{2,4},{2,5}};
    int n=6;
    int[] dist = sumOfDistancesInTree(n, edges);
    IO.println(Arrays.toString(dist));
}