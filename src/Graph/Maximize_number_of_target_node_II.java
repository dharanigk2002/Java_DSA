/*
There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.

You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always target to itself.

Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to node i of the first tree if you had to connect one node from the first tree to another node in the second tree.

Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.

https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/description/
*/

public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
    List<List<Integer>> adj1=buildGraph(edges1);
    List<List<Integer>> adj2=buildGraph(edges2);
    int n=adj1.size();
    int[] ans=new int[n];
    int[] count2=bfs(adj2);
    int best=0;
    for(int i:count2)
        best=Math.max(best, i);
    int[] count1=bfs(adj1);
    for(int i=0;i<n;i++)
        ans[i]=count1[i]+best;
    return ans;
}
private List<List<Integer>> buildGraph(int[][] edges) {
    int n=edges.length+1;
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
    return adj;
}
private int[] bfs(List<List<Integer>> adj) {
    int n=adj.size();
    int[] parity=new int[n];
    Arrays.fill(parity, -1);
    Queue<Integer> q=new LinkedList();
    q.add(0);
    parity[0]=0;
    int even=0, odd=0;
    while(!q.isEmpty()) {
        int node=q.poll();
        if(parity[node]==0)
            even++;
        else
            odd++;
        for(int next:adj.get(node))
            if(parity[next]==-1) {
                parity[next]=parity[node]^1;
                q.add(next);
            }
    }
    for(int i=0;i<n;i++)
        parity[i]=(parity[i]==0)?even:odd;
    return parity;
}

void main() {
    int[][] edges1 = {{0,1},{0,2},{2,3},{2,4}}, edges2 = {{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}};
    IO.println(Arrays.toString(maxTargetNodes(edges1, edges2)));
}