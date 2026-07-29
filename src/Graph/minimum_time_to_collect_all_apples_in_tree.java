/*
Minimum Time to Collect All Apples in a Tree

Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.

The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/
*/
public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] e:edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
    }
    return dfs(adj, 0, -1, hasApple);
}

private int dfs(List<List<Integer>> adj, int node, int parent, List<Boolean> hasApple) {
    int totalTime=0;
    for(int next:adj.get(node)) {
        if(next==parent) continue;
        int time=dfs(adj, next, node, hasApple);
        if(time>0 || hasApple.get(next))
            totalTime+=time+2;
    }
    return totalTime;
}
void main() {
    int n = 7;
    int[][] edges = {{0,1},{0,2},{1,4},{1,5},{2,3},{2,6}};
    List<Boolean> hasApple = List.of(false,false,true,false,true,true,false);
    System.out.println(minTime(n, edges, hasApple));
}