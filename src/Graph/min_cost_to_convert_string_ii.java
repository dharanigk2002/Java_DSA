/*
Minimum Cost to Convert String II

You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English characters. You are also given two 0-indexed string arrays original and changed, and an integer array cost, where cost[i] represents the cost of converting the string original[i] to the string changed[i].

You start with the string source. In one operation, you can pick a substring x from the string, and change it to y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y. You are allowed to do any number of operations, but any pair of operations must satisfy either of these two conditions:

The substrings picked in the operations are source[a..b] and source[c..d] with either b < c or d < a. In other words, the indices picked in both operations are disjoint.
The substrings picked in the operations are source[a..b] and source[c..d] with a == c and b == d. In other words, the indices picked in both operations are identical.
Return the minimum cost to convert the string source to the string target using any number of operations. If it is impossible to convert source to target, return -1.

Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].

https://leetcode.com/problems/minimum-cost-to-convert-string-ii/description/
*/

record Pair<U, V>(U first, V second) {}

private Long[] dp;
public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
    dp=new Long[source.length()+1];
    Map<String, List<Pair<String, Integer>>> g=new HashMap();
    int n=original.length;
    for(int i=0;i<n;i++)
        g.computeIfAbsent(original[i], k->new ArrayList()).add(new Pair<>(changed[i], cost[i]));
    Set<Integer> set=new TreeSet();
    for(int i=0;i<n;i++)
        set.add(original[i].length());
    long minCost = dfs(g, 0, set, original, changed, source, target);
    return minCost==Long.MAX_VALUE ? -1 : minCost;
}

private long dfs(Map<String, List<Pair<String, Integer>>> g, int id, Set<Integer> size, String[] original, String[] changed, String src, String target) {
    long minCost=Long.MAX_VALUE;
    if(id==src.length())
        return 0;
    if(dp[id]!=null)
        return dp[id];
    if(src.charAt(id)==target.charAt(id))
        minCost=dfs(g, id+1, size, original, changed, src, target);
    for(int len:size) {
        if(id+len>src.length())
            break;
        String srcSub=src.substring(id, id+len);
        String targetSub=target.substring(id, id+len);
        long cost=dijikstras(g, srcSub, targetSub);
        if(cost==Long.MAX_VALUE) continue;
        long s=dfs(g, id+len, size, original, changed, src, target);
        if(s!=Long.MAX_VALUE)
            minCost=Math.min(minCost, cost+s);
    }
    return dp[id]=minCost;
}

private long dijikstras(Map<String, List<Pair<String, Integer>>> g, String src, String target) {
    Map<String, Long> dist=new HashMap();
    dist.put(src, 0l);
    Queue<Pair<Long, String>> q=new PriorityQueue<>((a, b)->Long.compare(a.first(), b.first()));
    q.add(new Pair<>(0l, src));
    while(!q.isEmpty()) {
        Pair<Long, String> node=q.poll();
        long cost=node.first();
        String str=node.second();
        if(str.equals(target))
            return cost;
        if(cost>dist.getOrDefault(str, Long.MAX_VALUE))
            continue;
        for(Pair<String, Integer> next:g.getOrDefault(str, Collections.emptyList())) {
            if(cost+next.second()<dist.getOrDefault(next.first(), Long.MAX_VALUE)) {
                dist.put(next.first(), cost+next.second());
                q.add(new Pair<>(cost+next.second(), next.first()));
            }
        }
    }
    return dist.getOrDefault(target, Long.MAX_VALUE);
}

void main() {
    String source = "abcdefgh", target = "acdeeghh";
    String[] original = {"bcd","fgh","thh"}, changed = {"cde","thh","ghh"};
    int[] cost = {1,3,5};
    System.out.println(minimumCost(source, target, original, changed, cost));
}