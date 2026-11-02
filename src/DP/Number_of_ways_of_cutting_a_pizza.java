/*
Number of Ways of Cutting a Pizza

Given a rectangular pizza represented as a rows x cols matrix containing the following characters: 'A' (an apple) and '.' (empty cell) and given the integer k. You have to cut the pizza into k pieces using k-1 cuts.

For each cut you choose the direction: vertical or horizontal, then you choose a cut position at the cell boundary and cut the pizza into two pieces. If you cut the pizza vertically, give the left part of the pizza to a person. If you cut the pizza horizontally, give the upper part of the pizza to a person. Give the last piece of pizza to the last person.

Return the number of ways of cutting the pizza such that each piece contains at least one apple. Since the answer can be a huge number, return this modulo 10^9 + 7.

https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/description/
*/

private Integer[][][] dp;
private static final int MOD=1_000_000_007;
private int f(int m, int n, int i, int j, int k, int[][] grid) {
    if(dp[i][j][k]!=null)
        return dp[i][j][k];
    if(grid[i][j]==0)
        return dp[i][j][k]=0;
    if(k==0)
        return dp[i][j][k]=1;
    int sum=0;
    for(int nr=i+1;nr<m;nr++)
        if(grid[i][j]-grid[nr][j]>0) // checks atleast 1 apple(horizontal cut)
            sum=(sum+f(m, n, nr, j, k-1, grid))%MOD;
    for(int nc=j+1;nc<n;nc++)
        if(grid[i][j]-grid[i][nc]>0) // checks atleast 1 apple(vertical cut)
            sum=(sum+f(m, n, i, nc, k-1, grid))%MOD;
    return dp[i][j][k]=sum;
}

public int ways(String[] pizza, int k) {
    int m=pizza.length, n=pizza[0].length();
    int[][] grid=new int[m][n];
    dp=new Integer[m][n][k];
    grid[m-1][n-1]=pizza[m-1].charAt(n-1)=='A' ? 1 : 0;
    for(int j=n-2;j>=0;j--)
        grid[m-1][j]+=grid[m-1][j+1]+(pizza[m-1].charAt(j)=='A' ? 1 : 0);
    for(int i=m-2;i>=0;i--)
        grid[i][n-1]+=grid[i+1][n-1]+(pizza[i].charAt(n-1)=='A' ? 1 : 0);
    for(int i=m-2;i>=0;i--)
        for(int j=n-2;j>=0;j--)
            grid[i][j]+=(pizza[i].charAt(j)=='A' ? 1 : 0)+grid[i+1][j]+grid[i][j+1]-grid[i+1][j+1];
    return f(m, n, 0, 0, k-1, grid);
}

void main() {
    String[] pizza = {"A..","AAA","..."};
    int k = 3;
    IO.println(ways(pizza, k));
}