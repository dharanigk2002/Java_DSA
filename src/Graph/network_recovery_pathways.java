/*
Network Recovery Pathways

You are given a directed acyclic graph of n nodes numbered from 0 to n − 1. This is represented by a 2D array edges of length m, where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a recovery cost of costi.

Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online. Nodes 0 and n − 1 are always online.

A path from 0 to n − 1 is valid if:

All intermediate nodes on the path are online.
The total recovery cost of all edges on the path does not exceed k.
For each valid path, define its score as the minimum edge‑cost along that path.

Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists, return -1.

https://leetcode.com/problems/network-recovery-pathways/description/
*/
public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
    int low=0, high=0;
    int n=online.length;
    List<List<int[]>> adj=new ArrayList();
    int[] indegree=new int[n];
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        if(!online[u] || !online[v]) continue;
        adj.get(u).add(new int[]{v, w});
        indegree[v]++;
        high=Math.max(high, w);
    }
    int answer=-1;
    while(low<=high) {
        int mid=low+(high-low)/2;
        if(isPossible(adj, indegree, online, mid, k)) {
            answer=mid;
            low=mid+1;
        } else
            high=mid-1;
    }
    return answer;
}

private boolean isPossible(List<List<int[]>> adj, int[] indegree, boolean[] online, int mid, long k) {
    int[] ind=indegree.clone();
    int n=adj.size();
    Queue<Integer> q=new LinkedList();
    long[] dist=new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    dist[0]=0;
    for(int i=0;i<n;i++)
        if(ind[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        long cost=dist[node];
        for(int[] next:adj.get(node)) {
            int nextNode=next[0];
            if(cost!=Long.MAX_VALUE && next[1]>=mid)
                dist[nextNode]=Math.min(dist[nextNode], cost+next[1]);
            if(--ind[nextNode]==0)
                q.add(nextNode);
        }
    }
    return dist[n-1]<=k;
}

void main() {
    int[][] edges = {{0,1,5},{1,3,10},{0,2,3},{2,3,4}};
    boolean[] online = {true,true,true,true};
    int k = 10;
    System.out.println(findMaxPathScore(edges, online, k));
}