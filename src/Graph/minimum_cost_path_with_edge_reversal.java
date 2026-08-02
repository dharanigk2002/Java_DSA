/*
Minimum Cost Path with Edge Reversals

You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.

Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch, you may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.

The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.

Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.
*/

public int minCost(int n, int[][] edges) {
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, 2*w});
    }
    int[] dist=new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0]=0;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
    q.add(new int[]{0, 0});
    while(!q.isEmpty()) {
        int[] curr=q.poll();
        int cost=curr[0], node=curr[1];
        if(node==n-1)
            return cost;
        if(cost>dist[node]) continue;
        for(int[] next:adj.get(node)) {
            if(dist[next[0]]>cost+next[1]) {
                dist[next[0]]=cost+next[1];
                q.add(new int[]{dist[next[0]], next[0]});
            }
        }
    }
    return dist[n-1]==Integer.MAX_VALUE ? -1 : dist[n-1];
}

void main() {
    int n = 4;
    int[][] edges = {{0,1,3},{3,1,1},{2,3,4},{0,2,2}};
    System.out.println(minCost(n, edges));
}