/*
You are given a positive integer n representing n cities numbered from 1 to n. You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates that there is a bidirectional road between cities ai and bi with a distance equal to distancei. The cities graph is not necessarily connected.

The score of a path between two cities is defined as the minimum distance of a road in this path.

Return the minimum possible score of a path between cities 1 and n.

Note:

A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n.

https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/
*/

public int minScore(int n, int[][] roads) {
    int ans=(1<<31)-1;
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<=n;i++)
        adj.add(new ArrayList());
    for(int[] road:roads) {
        adj.get(road[0]).add(new int[]{road[1], road[2]});
        adj.get(road[1]).add(new int[]{road[0], road[2]});
    }
    Queue<Integer> q=new LinkedList();
    boolean[] visited=new boolean[n+1];
    q.add(1);
    visited[1]=true;
    while(!q.isEmpty()) {
        int node=q.poll();
        for(int[] next:adj.get(node)) {
            ans=Math.min(ans, next[1]);
            if(!visited[next[0]]) {
                visited[next[0]]=true;
                q.add(next[0]);
            }
        }
    }
    return ans;
}

void main() {
    int n = 4;
    int[][] roads = {{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
    int score=minScore(n, roads);
    IO.println(score);
}