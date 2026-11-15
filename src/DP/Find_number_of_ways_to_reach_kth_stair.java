/*
Find Number of Ways to Reach the K-th Stair

You are given a non-negative integer k. There exists a staircase with an infinite number of stairs, with the lowest stair numbered 0.

Alice has an integer jump, with an initial value of 0. She starts on stair 1 and wants to reach stair k using any number of operations. If she is on stair i, in one operation she can:

Go down to stair i - 1. This operation cannot be used consecutively or on stair 0.
Go up to stair i + 2jump. And then, jump becomes jump + 1.
Return the total number of ways Alice can reach stair k.

Note that it is possible that Alice reaches the stair k, and performs some operations to reach the stair k again.

https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/description/
*/

private final Map<String, Integer> map=new HashMap();
private final int f(int k, int jump, long i, int state) {
    String key=jump+"_"+i+"_"+state;
    Integer dp=map.get(key);
    if(dp!=null)
        return dp;
    if(i>k+1)
        return map.computeIfAbsent(key, ke->0);
    int cnt=0;
    if(i==k)
        cnt=1;
    if(i>0 && state==0)
        cnt+=f(k, jump, i-1, 1);
    int result=cnt+f(k, jump+1, i+(1l<<jump), 0);
    return map.computeIfAbsent(key, ke->result);
}

public int waysToReachStair(int k) {
    return f(k, 0, 1, 0);
}

void main() {
    int k = 1;
    IO.println(waysToReachStair(k));
}