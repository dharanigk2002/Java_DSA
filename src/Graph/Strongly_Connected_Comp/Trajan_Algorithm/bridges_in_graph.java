/*
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

https://leetcode.com/problems/critical-connections-in-a-network/description/
*/

private int times=0;
private boolean[] visited;
private int[] low, disc;
private List<List<Integer>> conn=new ArrayList();
List<List<Integer>> criticalConnections(int n, int[][] connections) {
    low=new int[n];
    disc=new int[n];
    visited=new boolean[n];
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] connection:connections) {
        adj.get(connection[0]).add(connection[1]);
        adj.get(connection[1]).add(connection[0]);
    }
    for(int i=0;i<n;i++)
        if(!visited[i])
            dfs(adj, i, -1);
    return conn;
}

void dfs(List<List<Integer>> adj, int src, int parent) {
    visited[src]=true;
    low[src]=disc[src]=times++;
    for(int v:adj.get(src)) {
        if(!visited[v]) {
            dfs(adj, v, src);
            low[src]=Math.min(low[src], low[v]);
            if(low[v]>disc[src])
                conn.add(new ArrayList(List.of(src, v)));
        } else if(v!=parent)
            low[src]=Math.min(low[src], disc[v]);
    }
}

void main() {
    int n = 4;
    int[][] connections = {{0,1},{1,2},{2,0},{1,3}};
    List<List<Integer>> connect = criticalConnections(n, connections);
    IO.println(connect);
}