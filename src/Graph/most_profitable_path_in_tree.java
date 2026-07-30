/*
Most Profitable Path in a Tree

There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0. You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:

the price needed to open the gate at node i, if amount[i] is negative, or,
the cash reward obtained on opening the gate at node i, otherwise.
The game goes on as follows:

Initially, Alice is at node 0 and Bob is at node bob.
At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward. Note that:
If the gate is already open, no price will be required, nor will there be any cash reward.
If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the gate is c, both of them receive c / 2 each.
If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving. Note that these events are independent of each other.
Return the maximum net income Alice can have if she travels towards the optimal leaf node.

https://leetcode.com/problems/most-profitable-path-in-a-tree/description/
*/

private int answer=Integer.MIN_VALUE;
private int[] amount;
public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
    this.amount=amount;
    List<List<Integer>> adj=new ArrayList();
    int n=amount.length;
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1];
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
    int[] bobTime=new int[n];
    Arrays.fill(bobTime, Integer.MAX_VALUE);
    dfsBob(adj, bobTime, 0, bob, -1);
    aliceDfs(adj, bobTime, 0, 0, -1, 0);
    return answer;
}

private void aliceDfs(List<List<Integer>> adj, int[] bobTime, int time, int alice, int parent, int profit) {
    if(time<bobTime[alice])
        profit+=amount[alice];
    else if(time==bobTime[alice])
        profit+=(amount[alice]/2);
    boolean isLeaf=alice!=0 && adj.get(alice).size()==1;
    if(alice==0 && adj.size()==1)
        isLeaf=true;
    if(isLeaf) {
        answer=Math.max(answer, profit);
        return;
    }
    for(int next:adj.get(alice))
        if(parent!=next)
            aliceDfs(adj, bobTime, time+1, next, alice, profit);
}

private boolean dfsBob(List<List<Integer>> adj, int[] bobTime, int time, int bob, int parent) {
    if(bob==0) {
        bobTime[0]=time;
        return true;
    }
    for(int next:adj.get(bob)) {
        if(next==parent) continue;
        if(dfsBob(adj, bobTime, time+1, next, bob)) {
            bobTime[bob]=time;
            return true;
        }
    }
    return false;
}

void main() {
    int[][] edges = {{0,1},{1,2},{1,3},{3,4}};
    int bob = 3;
    int[] amount = {-2,4,2,-4,6};

    System.out.println(mostProfitablePath(edges, bob, amount));
}