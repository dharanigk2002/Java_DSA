/*
There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/
*/
public int minReorder(int n, int[][] connections) {
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] conn:connections) {
        int u=conn[0], v=conn[1];
        adj.get(u).add(v);
        adj.get(v).add(-u);
    }
    return dfs(adj, 0, new boolean[n]);
}
private int dfs(List<List<Integer>> adj, int src, boolean[] visited) {
    visited[src]=true;
    int count=0;
    for(int next:adj.get(src))
        if(!visited[Math.abs(next)])
            count+=dfs(adj, Math.abs(next), visited)+((next>0)?1:0);
    return count;
}
void main() {
    int n=6;
    int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
    IO.println(minReorder(n, connections));
}