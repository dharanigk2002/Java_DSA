/*

*/

private static final int MOD=1_000_000_007;
private Integer[][] dp;

private final int f(int n, int k) {
    if(dp[n][k]!=null)
        return dp[n][k];
    if(k==0)
        return dp[n][k]=1;
    if(n==0)
        return dp[n][k]=0;
    long ways=0;
    for(int i=0;i<=Math.min(k, n-1);i++)
        ways+=f(n-1, k-i);
    return dp[n][k]=(int)(ways%MOD);
}

public int kInversePairs(int n, int k) {
    int[] dp=new int[k+1];
    dp[0]=1;
    for(int i=1;i<=n;i++) {
        int[] curr=new int[k+1];
        curr[0]=1;
        for(int j=1;j<=k;j++) {
            long ways=0;
            for(int l=0;l<=Math.min(j, i-1);l++)
                ways+=dp[j-l];
            curr[j]=(int)(ways%MOD);
        }
        dp=curr;
    }
    return dp[k];
}

void main() {
    int n = 3;
    int k = 1;
    IO.println(kInversePairs(n, k));
}