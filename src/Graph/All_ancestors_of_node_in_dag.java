/*
You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).

You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.

Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.

A node u is an ancestor of another node v if u can reach v via a set of edges.

https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/description/
*/

public List<List<Integer>> getAncestors(int n, int[][] edges) {
    List<List<Integer>> adj=new ArrayList();
    List<Set<Integer>> ans=new ArrayList();
    int[] indegree=new int[n];
    for(int i=0;i<n;i++) {
        adj.add(new ArrayList());
        ans.add(new TreeSet<>());
    }
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1];
        adj.get(u).add(v);
        indegree[v]++;
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        for(int next:adj.get(node)) {
            indegree[next]--;
            if(indegree[next]==0)
                q.add(next);
            ans.get(next).addAll(ans.get(node));
            ans.get(next).add(node);
        }
    }
    return ans.stream().map(ArrayList::new).collect(Collectors.toList());
}

void main() {
    int[][] edgeList = {{0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}};
    int n = 8;
    IO.println(getAncestors(n, edgeList));
}