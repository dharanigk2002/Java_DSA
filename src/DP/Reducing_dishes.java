/*
Reducing Dishes

A chef has collected data on the satisfaction level of his n dishes. Chef can cook any dish in 1 unit of time.

Like-time coefficient of a dish is defined as the time taken to cook that dish including previous dishes multiplied by its satisfaction level i.e. time[i] * satisfaction[i].

Return the maximum sum of like-time coefficient that the chef can obtain after preparing some amount of dishes.

Dishes can be prepared in any order and the chef can discard some dishes to get this maximum value.

https://leetcode.com/problems/reducing-dishes/description/
*/

public int maxSatisfaction(int[] satisfaction) {
    Arrays.sort(satisfaction);
    int n=satisfaction.length;
    int[] dp=new int[n+1];
    for(int i=n-1;i>=0;i--) {
        int[] curr=new int[n+1];
        for(int cnt=n-1;cnt>=0;cnt--)
            curr[cnt]=Math.max((satisfaction[i] * (cnt+1))+dp[cnt+1], dp[cnt]);
        dp=curr;
    }
    return dp[0];
}
private Integer[][] dp;
private int f(int[] s, int i, int cnt) {
    if(dp[i][cnt]!=null)
        return dp[i][cnt];
    if(i==s.length)
        return dp[i][cnt]=0;
    int notTake=f(s, i+1, cnt);
    int take=(s[i] * (cnt+1))+f(s, i+1, cnt+1);
    return dp[i][cnt]=Math.max(take, notTake);
}

void main() {
    int[] satisfaction = {-1,-8,0,5,-9};
    int n=satisfaction.length;
    dp=new Integer[n+1][n+1];
    IO.println(maxSatisfaction(satisfaction));
}