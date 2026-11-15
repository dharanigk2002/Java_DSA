/*
Maximum Points After Collecting Coins From All Nodes

There exists an undirected tree rooted at node 0 with n nodes labeled from 0 to n - 1. You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given a 0-indexed array coins of size n where coins[i] indicates the number of coins in the vertex i, and an integer k.

Starting from the root, you have to collect all the coins such that the coins at a node can only be collected if the coins of its ancestors have been already collected.

Coins at nodei can be collected in one of the following ways:

Collect all the coins, but you will get coins[i] - k points. If coins[i] - k is negative then you will lose abs(coins[i] - k) points.
Collect all the coins, but you will get floor(coins[i] / 2) points. If this way is used, then for all the nodej present in the subtree of nodei, coins[j] will get reduced to floor(coins[j] / 2).
Return the maximum points you can get after collecting the coins from all the tree nodes.

https://leetcode.com/problems/maximum-points-after-collecting-coins-from-all-nodes/description/
*/

private final List<List<Integer>> adj=new ArrayList();
private int[] coins;
private int k;
private Integer[][] dp;

private int f(List<List<Integer>> adj, int node, int parent, int p) {
    if(p==14)
        return dp[node][p]=0;
    if(dp[node][p]!=null)
        return dp[node][p];
    long case1=(coins[node]>>p)-k;
    long case2=(coins[node]>>(p+1));
    for(int next:adj.get(node)) {
        if(next==parent) continue;
        case1+=f(adj, next, node, p);
        case2+=f(adj, next, node, p+1);
    }
    return dp[node][p]=(int)Math.max(case1, case2);
}

public int maximumPoints(int[][] edges, int[] coins, int k) {
    int n=coins.length;
    this.coins=coins;
    this.k=k;
    dp=new Integer[n+1][15];
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] e:edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
    }
    return f(adj, 0, -1, 0);
}

void main() {
    int[][] edges = {{0,1},{1,2},{2,3}};
    int[] coins = {10,10,3,3};
    int k = 5;

}