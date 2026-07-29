/*
Minimum Cost to Convert String I

You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where cost[i] represents the cost of changing the character original[i] to the character changed[i].

You start with the string source. In one operation, you can pick a character x from the string and change it to the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y.

Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

https://leetcode.com/problems/minimum-cost-to-convert-string-i/description/
*/

private static final long INF = Long.MAX_VALUE;
public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
    long[][] dist=new long[26][26];
    for(int i=0;i<26;i++) {
        Arrays.fill(dist[i], INF);
        dist[i][i]=0;
    }
    int n=original.length;
    for(int i=0;i<n;i++) {
        int u=original[i]-97, v=changed[i]-97;
        dist[u][v]=Math.min(dist[u][v], cost[i]);
    }
    for(int k=0;k<26;k++)
        for(int i=0;i<26;i++)
            for(int j=0;j<26;j++)
                if(dist[i][k]!=INF && dist[k][j]!=INF)
                    dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
    int l=source.length();
    long total=0;
    for(int i=0;i<l;i++) {
        int u=source.charAt(i)-97, v=target.charAt(i)-97;
        if(dist[u][v]==INF)
            return -1;
        total+=dist[u][v];
    }
    return total;
}

void main() {
    String source = "abcd", target = "acbe";
    char[] original = {'a','b','c','c','e','d'}, changed = {'b','c','b','e','b','e'};
    int[] cost = {2,5,5,1,2,20};
    System.out.println(minimumCost(source, target, original, changed, cost));
}