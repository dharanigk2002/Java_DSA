/*
Maximal Network Rank

There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

https://leetcode.com/problems/maximal-network-rank/description/
*/
public int maximalNetworkRank(int n, int[][] roads) {
    List<Set<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new HashSet());
    for(int[] r:roads) {
        adj.get(r[0]).add(r[1]);
        adj.get(r[1]).add(r[0]);
    }
    int max=0;
    for(int i=0;i<n;i++)
        for(int j=i+1;j<n;j++) {
            int iRank=adj.get(i).size(), jRank=adj.get(j).size();
            int total=iRank+jRank;
            if(adj.get(i).contains(j))
                total--;
            max=Integer.max(max, total);
        }
    return max;
}
void main() {
    int n = 4;
    int[][] roads = {{0,1},{0,3},{1,2},{1,3}};
    System.out.println(maximalNetworkRank(n, roads));
}