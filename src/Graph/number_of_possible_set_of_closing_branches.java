/*
Number of Possible Sets of Closing Branches

There is a company with n branches across the country, some of which are connected by roads. Initially, all branches are reachable from each other by traveling some roads.

The company has realized that they are spending an excessive amount of time traveling between their branches. As a result, they have decided to close down some of these branches (possibly none). However, they want to ensure that the remaining branches have a distance of at most maxDistance from each other.

The distance between two branches is the minimum total traveled length needed to reach one branch from another.

You are given integers n, maxDistance, and a 0-indexed 2D array roads, where roads[i] = [ui, vi, wi] represents the undirected road between branches ui and vi with length wi.

Return the number of possible sets of closing branches, so that any branch has a distance of at most maxDistance from any other.

Note that, after closing a branch, the company will no longer have access to any roads connected to it.

Note that, multiple roads are allowed.

https://leetcode.com/problems/number-of-possible-sets-of-closing-branches/description/
*/

private static final int INF=1_000_007;
public int numberOfSets(int n, int maxDistance, int[][] roads) {
    int total = (1<<n);
    int count=0;
    for(int mask=0;mask<total;mask++) {
        int[][] cost=new int[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(cost[i], INF);
            if(isOpen(mask, i))
                cost[i][i]=0;
        }
        for(int[] road:roads) {
            int u=road[0], v=road[1], weight=road[2];
            if(isOpen(mask, u) && isOpen(mask, v)) {
                cost[u][v]=Math.min(cost[u][v], weight);
                cost[v][u]=Math.min(cost[v][u], weight);
            }
        }
        for(int mid=0;mid<n;mid++) {
            if(!isOpen(mask, mid)) continue;
            for(int src=0;src<n;src++) {
                if(!isOpen(mask, src)) continue;
                for(int dest=0;dest<n;dest++) {
                    if(!isOpen(mask, dest)) continue;
                    cost[src][dest]=Math.min(cost[src][dest], cost[src][mid]+cost[mid][dest]);
                }
            }
        }
        boolean flag=false;
        for(int i=0;i<n;i++) {
            if(!isOpen(mask, i)) continue;
            for(int j=i+1;j<n;j++)
                if(isOpen(mask, j) && cost[i][j]>maxDistance) {
                    flag=true;
                    break;
                }
            if(flag)
                break;
        }
        if(!flag)
            count++;
    }
    return count;
}

private boolean isOpen(int mask, int node) {
    return (mask&(1<<node))!=0;
}

void main() {
    int n = 3, maxDistance = 5;
    int[][] roads = {{0,1,2},{1,2,10},{0,2,10}};
    System.out.println(numberOfSets(n, maxDistance, roads));
}