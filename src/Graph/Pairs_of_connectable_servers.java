/*
You are given an unrooted weighted tree with n vertices representing servers numbered from 0 to n - 1, an array edges where edges[i] = [ai, bi, weighti] represents a bidirectional edge between vertices ai and bi of weight weighti. You are also given an integer signalSpeed.

Two servers a and b are connectable through a server c if:

a < b, a != c and b != c.
The distance from c to a is divisible by signalSpeed.
The distance from c to b is divisible by signalSpeed.
The path from c to b and the path from c to a do not share any edges.
Return an integer array count of length n where count[i] is the number of server pairs that are connectable through the server i.

https://leetcode.com/problems/count-pairs-of-connectable-servers-in-a-weighted-tree-network/description/
*/

int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
    int n=edges.length+1;
    int[] count=new int[n];
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] e:edges) {
        adj.get(e[0]).add(new int[]{e[1], e[2]});
        adj.get(e[1]).add(new int[]{e[0], e[2]});
    }
    for(int root=0;root<n;root++) {
        if(adj.get(root).size()<2)
            continue;
        int total=0;
        for(int[] v:adj.get(root)) {
            int valid=dfs(adj, v[0], root, v[1], signalSpeed);
            count[root] += total * valid;
            total += valid;
        }
    }
    return count;
}

int dfs(List<List<int[]>> adj, int u, int parent, int weight, int signalSpeed) {
    int count=0;
    if(weight%signalSpeed==0)
        count++;
    for(int[] v:adj.get(u)) {
        int node=v[0], wt=v[1];
        if(node!=parent)
            count+=dfs(adj, node, u, weight+wt, signalSpeed);
    }
    return count;
}

void main() {
    int[][] edges = {{0,6,3},{6,5,3},{0,3,1},{3,2,7},{3,1,6},{3,4,2}};
    int signalSpeed = 3;
    /* Output: [2,0,0,0,0,0,2]
    Explanation: Through server 0, there are 2 pairs of connectable servers: (4, 5) and (4, 6).
            Through server 6, there are 2 pairs of connectable servers: (4, 5) and (0, 5).
            It can be shown that no two servers are connectable through servers other than 0 and 6.
     */
    int[] pairs = countPairsOfConnectableServers(edges, signalSpeed);
    IO.println(Arrays.toString(pairs));
}