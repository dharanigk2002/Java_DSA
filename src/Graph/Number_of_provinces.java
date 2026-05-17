/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

https://leetcode.com/problems/number-of-provinces/description
* */

public int findCircleNum(int[][] isConnected) {
    int provinces=0, n=isConnected.length;
    boolean[] visited=new boolean[n];
    for(int i=0;i<n;i++) {
        if(visited[i]) continue;
        provinces++;
        visitCities(isConnected, i, visited);
    }
    return provinces;
}
private void visitCities(int[][] graph, int city, boolean[] visited) {
    visited[city]=true;
    for(int i=0;i<graph[city].length;i++) {
        if(graph[city][i]==0 || visited[i]) continue;
        visitCities(graph, i, visited);
    }
}

void main() {
//    int[][] graph={{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
    int[][] graph={{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    int provinces=findCircleNum(graph);
    IO.println(provinces);
}