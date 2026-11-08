/*
Minimum Number of Taps to Open to Water a Garden

There is a one-dimensional garden on the x-axis. The garden starts at the point 0 and ends at the point n. (i.e., the length of the garden is n).

There are n + 1 taps located at points [0, 1, ..., n] in the garden.

Given an integer n and an integer array ranges of length n + 1 where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
*/
private static final int MAX=1_000_000_000;
private int n;
private final Map<String, Integer> dp=new HashMap();
private int f(int[][] range, int i, int max) {
    String key=i+", "+max;
    if(dp.containsKey(key))
        return dp.get(key);
    if(i==n+1)
        return dp.computeIfAbsent(key, k->max==n ? 0 : MAX);
    if(range[i][0]>max)
        return dp.computeIfAbsent(key, k->MAX);
    int notPick=f(range, i+1, max);
    int pick=1+f(range, i+1, Math.max(max, range[i][1]));
    return dp.computeIfAbsent(key, k->Math.min(pick, notPick));
}

public int minTaps(int n, int[] ranges) {
    this.n=n;
    int[][] range=new int[n+1][2];
    for(int i=0;i<=n;i++)
        range[i]=new int[]{Math.max(0, i-ranges[i]), Math.min(n, i+ranges[i])};
    Arrays.sort(range, (a, b)->a[0]-b[0]);
    int ways=f(range, 0, 0);
    return ways==MAX ? -1 : ways;
}

// greedy approach
public int greedy(int n, int[] ranges) {
    int[] last=new int[n+1];
    for(int i=0;i<=n;i++) {
        int l=Math.max(0, i-ranges[i]);
        int r=i+ranges[i];
        last[l]=Math.max(last[l], r);
    }
    int mx=0, pre=0, taps=0;
    for(int i=0;i<n;i++) {
        mx=Math.max(mx, last[i]);
        if(mx<=i)
            return -1;
        if(pre==i) {
            pre=mx;
            taps++;
        }
    }
    return taps;
}

void main() {
    int n = 5;
    int[] ranges = {3,4,1,1,0,0};
    IO.println(greedy(n, ranges));
}