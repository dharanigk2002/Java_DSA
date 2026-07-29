/*
Modify Graph Edge Weights

You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).

Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer target. If there are multiple modifications that make the shortest distance between source and destination equal to target, any of them will be considered correct.

Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from source to destination equal to target, or an empty array if it's impossible.

Note: You are not allowed to modify the weights of edges with initial positive weights.

https://leetcode.com/problems/modify-graph-edge-weights/description/
*/

private static final int INF=Integer.MAX_VALUE;
private static final int LARGE=2_000_000_000;
public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
    int distance=dijikstras(n, edges, source, destination);
    if(distance<target)
        return new int[0][0];
    if(distance==target) {
        for(int[] edge:edges)
            if(edge[2]==-1)
                edge[2]=LARGE;
        return edges;
    }
    boolean targetReached=false;
    for(int[] edge:edges) {
        if(edge[2]!=-1)
            continue;
        if(targetReached) {
            edge[2]=LARGE;
            continue;
        }
        edge[2]=1;
        distance=dijikstras(n, edges, source, destination);
        if(distance<=target) {
            targetReached=true;
            edge[2]+=(target-distance);
        }
    }
    return targetReached ? edges : new int[0][0];
}

private int dijikstras(int n, int[][] edges, int s, int d) {
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        if(w==-1) continue;
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }
    int[] dist=new int[n];
    Arrays.fill(dist, INF);
    dist[s]=0;
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    q.add(new int[]{0, s});
    while(!q.isEmpty()) {
        int[] curr=q.poll();
        int node=curr[1], w=curr[0];
        if(w>dist[node]) continue;
        if(node==d)
            return w;
        for(int[] next:adj.get(node)) {
            int nextWeight=next[1]+w;
            if(nextWeight<dist[next[0]]) {
                dist[next[0]]=nextWeight;
                q.add(new int[]{dist[next[0]], next[0]});
            }
        }
    }
    return dist[d];
}

void main() {
    int n = 5, source = 0, destination = 1, target = 5;
    int[][] edges = {{4,1,-1},{2,0,-1},{0,3,-1},{4,3,-1}};
    int[][] res=modifiedGraphEdges(n, edges, source, destination, target);
    for(int[] row:res)
        System.out.println(Arrays.toString(row));
}
