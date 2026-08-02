/*
Minimum Score After Removals on a Tree

There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given a 0-indexed integer array nums of length n where nums[i] represents the value of the ith node. You are also given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Remove two distinct edges of the tree to form three connected components. For a pair of removed edges, the following steps are defined:

Get the XOR of all the values of the nodes for each of the three components respectively.
The difference between the largest XOR value and the smallest XOR value is the score of the pair.
For example, say the three components have the node values: [4,5,7], [1,9], and [3,3,3]. The three XOR values are 4 ^ 5 ^ 7 = 6, 1 ^ 9 = 8, and 3 ^ 3 ^ 3 = 3. The largest XOR value is 8 and the smallest XOR value is 3. The score is then 8 - 3 = 5.
Return the minimum score of any possible pair of edge removals on the given tree.
*/

private int[] nums;
private int[] dp;
private int[] tin;
private int[] tout;
private List<List<Integer>> adj;
private int time;
public int minimumScore(int[] nums, int[][] edges) {
    this.nums=nums;
    this.time=0;
    this.adj=new ArrayList();
    int n=nums.length;
    tin=new int[n];
    tout=new int[n];
    dp=new int[n];
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    int totalXor=dfs(0, -1);
    int answer=Integer.MAX_VALUE;
    for(int a=1;a<n;a++) {
        for(int b=a+1;b<n;b++) {
            int xor1, xor2, xor3;
            if(isAncestor(a, b)) {
                xor1=dp[b];
                xor2=dp[a]^dp[b];
                xor3=totalXor^dp[a];
            } else if(isAncestor(b, a)) {
                xor1=dp[a];
                xor2=dp[b]^dp[a];
                xor3=totalXor^dp[b];
            } else {
                xor1=dp[a];
                xor2=dp[b];
                xor3=totalXor^dp[a]^dp[b];
            }
            int maxi=Math.max(xor1, Math.max(xor2, xor3));
            int mini=Math.min(xor1, Math.min(xor2, xor3));
            answer=Math.min(answer, maxi-mini);
        }
    }
    return answer;
}

private boolean isAncestor(int a, int b) {
    return tin[a]<=tin[b] && tout[a]>=tout[b];
}

private int dfs(int node, int parent) {
    tin[node]=time++;
    dp[node]=nums[node];
    for(int next:adj.get(node)) {
        if(parent==next) continue;
        dp[node]^=dfs(next, node);
    }
    tout[node]=time++;
    return dp[node];
}

void main() {
    int[] nums = {1,5,5,4,11};
    int[][] edges = {{0,1},{1,2},{1,3},{3,4}};
    System.out.println(minimumScore(nums, edges));
}