/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

https://leetcode.com/problems/cheapest-flights-within-k-stops/
*/

public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] dist=new int[n];
    Queue<int[]> q=new LinkedList();
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++) {
        adj.add(new ArrayList());
        dist[i]=Integer.MAX_VALUE;
    }
    dist[src]=0;
    for(int[] flight:flights)
        adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
    q.add(new int[]{0, src, 0});
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int currDist=node[0], currCity=node[1], level=node[2];
        if(level>k) continue;
        for(int[] nei:adj.get(currCity)) {
            int city=nei[0], cost=nei[1];
            if(dist[city] > cost+currDist && level<=k) {
                dist[city]=cost+currDist;
                q.add(new int[]{dist[city], city, level+1});
            }
        }
    }
    return dist[dst]!=Integer.MAX_VALUE ? dist[dst] : -1;
}

void main() {
    int n = 4, src = 0, dst = 3, k = 1;
    int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
    int cheapestFlight=findCheapestPrice(n, flights, src, dst, k);
    IO.println(cheapestFlight);
}