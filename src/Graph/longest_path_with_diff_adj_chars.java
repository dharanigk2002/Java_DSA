/*
Longest Path With Different Adjacent Characters

You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.

You are also given a string s of length n, where s[i] is the character assigned to node i.

Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

https://leetcode.com/problems/longest-path-with-different-adjacent-characters/description/
*/

private int max=1;
public int longestPath(int[] parent, String s) {
    int n=parent.length;
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int i=1;i<n;i++)
        adj.get(parent[i]).add(i);
    dfs(adj, 0, s);
    return max;
}

private int dfs(List<List<Integer>> adj, int node, String s) {
    int longest=0, sLongest=0;
    for(int next:adj.get(node)) {
        int child=dfs(adj, next, s);
        if(s.charAt(next)==s.charAt(node))
            continue;
        if(child>longest) {
            sLongest=longest;
            longest=child;
        } else if(sLongest<child)
            sLongest=child;
    }
    max=Math.max(max, 1+longest+sLongest);
    return 1+longest;
}

void main() {
    int[] parent = {-1,0,0,1,1,2};
    String s = "abacbe";
    System.out.println(longestPath(parent, s));
}