/*
Shortest Path Visiting All Nodes

You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

https://leetcode.com/problems/shortest-path-visiting-all-nodes/description
*/
public int shortestPathLength(int[][] graph) {
    int n=graph.length;
    int allNodes=(1<<n)-1;
    boolean[][] vis=new boolean[n][allNodes+1];
    Queue<int[]> q=new LinkedList();
    for(int i=0;i<n;i++) {
        q.add(new int[]{i, (1<<i)});
        vis[i][(1<<i)]=true;
    }
    int paths=0;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int[] node=q.poll();
            if(node[1]==allNodes)
                return paths;
            for(int next:graph[node[0]]) {
                int state=node[1]|(1<<next);
                if(!vis[next][state]) {
                    vis[next][state]=true;
                    q.add(new int[]{next, state});
                }
            }
        }
        paths++;
    }
    return -1;
}
void main() {
    int[][] graph = {{1,2,3},{0},{0},{0}};
    System.out.println(shortestPathLength(graph));
}