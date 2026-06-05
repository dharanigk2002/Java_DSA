/*
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
*/

public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] dist=new int[n][n];
    final int INF=(int)1e8;
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            if(i!=j)
                dist[i][j]=INF;
    for(int[] edge:edges) {
        dist[edge[0]][edge[1]]=edge[2];
        dist[edge[1]][edge[0]]=edge[2];
    }
    for(int k=0;k<n;k++)
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                if(dist[i][k]!=INF && dist[k][j]!=INF)
                    dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
    int currentCity=0, cnt=n;
    for(int i=0;i<n;i++) {
        int count=0;
        for(int j=0;j<n;j++)
            if(dist[i][j]<=distanceThreshold)
                count++;
        if(count<=cnt) {
            cnt=count;
            currentCity=i;
        }
    }
    return currentCity;

}

void main() {
    int n = 4, distanceThreshold = 4;
    int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
    /*
    Explanation: The figure above describes the graph.
    The neighboring cities at a distanceThreshold = 4 for each city are:
    City 0 -> [City 1, City 2]
    City 1 -> [City 0, City 2, City 3]
    City 2 -> [City 0, City 1, City 3]
    City 3 -> [City 1, City 2]
    Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
    */
    int city=findTheCity(n, edges, distanceThreshold);
    IO.println(city);
}