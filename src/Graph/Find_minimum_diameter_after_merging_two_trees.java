/*
There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

You must connect one node from the first tree with another node from the second tree with an edge.

Return the minimum possible diameter of the resulting tree.

The diameter of a tree is the length of the longest path between any two nodes in the tree.

https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/
*/

public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
    int dia1=getDia(edges1);
    int dia2=getDia(edges2);
    return Math.max(Math.max(dia1, dia2), 1+(dia1+1)/2+(dia2+1)/2);
}
private int getDia(int[][] edges) {
    List<List<Integer>> adj=new ArrayList();
    int n=edges.length+1;
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] e:edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
    }
    int farthest=bfs(adj, 0)[0];
    return bfs(adj, farthest)[1];
}
private int[] bfs(List<List<Integer>> adj, int start) {
    Queue<Integer> q=new LinkedList();
    int n=adj.size();
    boolean[] visited=new boolean[n];
    visited[start]=true;
    q.add(start);
    int farthest=start, distance=-1;
    while(!q.isEmpty()) {
        distance++;
        for(int size=q.size();size>0;size--) {
            int node=q.poll();
            farthest=node;
            for(int next:adj.get(node))
                if(!visited[next]) {
                    visited[next]=true;
                    q.add(next);
                }
        }
    }
    return new int[]{farthest, distance};
}

void main() {
    int[][] edges1 = {{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}}, edges2 = {{0,1},{0,2},{0,3},{2,4},{2,5},{3,6},{2,7}};
    IO.println(minimumDiameterAfterMerge(edges1, edges2));
}