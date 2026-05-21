/*
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
*/

public int countPaths(int n, int[][] roads) {
    final int MOD=1_000_000_007;
    long[] dist=new long[n], ways=new long[n];
    List<List<int[]>> adj=new ArrayList();
    Queue<long[]> q=new PriorityQueue<>((a, b)->Long.compare(a[0], b[0]));
    for(int i=0;i<n;i++) {
        adj.add(new ArrayList());
        dist[i]=Long.MAX_VALUE;
    }
    for(int[] edge:roads) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
    }
    ways[0]=1;
    dist[0]=0;
    q.add(new long[]{0, 0});
    while(!q.isEmpty()) {
        int node=(int)q.peek()[1];
        long w=q.poll()[0];
        if(w>dist[node]) continue;
        for(int[] nei:adj.get(node)) {
            long d=nei[1];
            int next=(int)nei[0];
            if(dist[next]>d+w) {
                dist[next]=d+w;
                q.add(new long[]{dist[next], next});
                ways[next]=ways[node];
            } else if(dist[next]==d+w)
                ways[next]=(ways[node] + ways[next])%MOD;
        }
    }
    return (int)(ways[n-1]%MOD);
}

void main() {
    int n = 7;
    int[][] roads = {{0,6,7},{0,1,2},{1,2,3},{1,3,3},{6,3,3},{3,5,1},{6,5,1},{2,5,1},{0,4,5},{4,6,2}};
    /*
    Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
    The four ways to get there in 7 minutes are:
    - 0 ➝ 6
    - 0 ➝ 4 ➝ 6
    - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
    - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
    */
    int paths=countPaths(n, roads);
    IO.println(paths);
}