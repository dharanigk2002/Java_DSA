/*
Maximize the Number of Partitions After Operations

You are given a string s and an integer k.

First, you are allowed to change at most one index in s to another lowercase English letter.

After that, do the following partitioning operation until s is empty:

Choose the longest prefix of s containing at most k distinct characters.
Delete the prefix from s and increase the number of partitions by one. The remaining characters (if any) in s maintain their initial order.
Return an integer denoting the maximum number of resulting partitions after the operations by optimally choosing at most one index to change.

https://leetcode.com/problems/maximize-the-number-of-partitions-after-operations/description/
*/
private final Map<String, Integer> dp=new HashMap();
private int f(String s, int k, int idx, int map, int t) {
    int n=s.length();
    String key=idx+"_"+map+"_"+t;
    Integer memo=null;
    if((memo=dp.get(key))!=null)
        return memo;
    if(idx==n)
        return dp.computeIfAbsent(key, f->1);
    int maxPartitions=1;
    int curr=(1<<(s.charAt(idx)-'a'));
    int newMap=map|curr;
    if(Integer.bitCount(newMap)>k)
        maxPartitions=1+f(s, k, idx+1, curr, t);
    else
        maxPartitions=f(s, k, idx+1, newMap, t);

    if(t>0)
        for(int i=0;i<26;i++) {
            curr=(1<<i);
            newMap=map|curr;
            if(Integer.bitCount(newMap)>k) {
                maxPartitions=Math.max(maxPartitions, 1+f(s, k, idx+1, curr, 0));
            } else {
                maxPartitions=Math.max(maxPartitions, f(s, k, idx+1, newMap, 0));
            }
        }
    dp.put(key, maxPartitions);
    return maxPartitions;
}

public int maxPartitionsAfterOperations(String s, int k) {
    return f(s, k, 0, 0, 1);
}

void main() {
    String s = "accca";
    int k = 2;
    IO.println(maxPartitionsAfterOperations(s, k));
}