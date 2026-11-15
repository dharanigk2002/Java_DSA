/*
Jump Game V

Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and  0 < x <= d.
i - x where: i - x >= 0 and  0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.

https://leetcode.com/problems/jump-game-v/description/
*/

private Integer[] dp;
private int solve(int[] arr, int i, int d) {
    int n=arr.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[i]=0;
    int result=0;
    for(int j=i-1;j>=Math.max(0, i-d);j--)
        if(arr[i]>arr[j])
            result=Math.max(result, 1+solve(arr, j, d));
        else
            break;
    for(int j=i+1;j<=Math.min(n-1, i+d);j++)
        if(arr[i]>arr[j])
            result=Math.max(result, 1+solve(arr, j, d));
        else
            break;
    return dp[i]=result;
}

public int maxJumps(int[] arr, int d) {
    int max=1, n=arr.length;
    dp=new Integer[n+1];
    for(int i=0;i<n;i++)
        max=Math.max(max, 1+solve(arr, i, d));
    return max;
}

void main() {
    int[] arr = new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
    int d = 2;
    IO.println(maxJumps(arr, d));
}