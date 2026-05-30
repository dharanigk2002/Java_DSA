/*
There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and [0, m - 1], respectively.

You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree. You are also given an integer k.

Node u is target to node v if the number of edges on the path from u to v is less than or equal to k. Note that a node is always target to itself.

Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i of the first tree if you have to connect one node from the first tree to another node in the second tree.

Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.

https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/description/
*/
public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
    int n=edges1.length, m=edges2.length;
    List<List<Integer>> adj1=new ArrayList();
    List<List<Integer>> adj2=new ArrayList();
    build(adj1, edges1, n);
    build(adj2, edges2, m);
    int best2=0;
    if(k>0)
        for(int i=0;i<=m;i++)
            best2=Math.max(best2, bfs(adj2, k-1, i, m));
    int[] ans=new int[n+1];
    for(int i=0;i<=n;i++)
        ans[i]=bfs(adj1, k, i, n)+best2;
    return ans;
}

private void build(List<List<Integer>> adj, int[][] edges, int n) {
    for(int i=0;i<=n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
}

private int bfs(List<List<Integer>> adj, int k, int src, int size) {
    Queue<Integer> q=new LinkedList();
    q.add(src);
    boolean[] visited=new boolean[size+1];
    int moves=0, count=1;
    visited[src]=true;
    while(!q.isEmpty() && moves<k) {
        for(int i=q.size();i>0;i--) {
            int node=q.poll();
            for(int next:adj.get(node)) {
                if(!visited[next]) {
                    visited[next]=true;
                    count++;
                    q.add(next);
                }
            }
        }
        moves++;
    }
    return count;
}

void main() {
    int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}}, edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
    int k = 2;
    int[] maxTarget=maxTargetNodes(edges1, edges2, k);
    IO.println(Arrays.toString(maxTarget));
}