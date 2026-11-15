/*
Number of Ways to Paint N × 3 Grid

You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/
*/

private static final String[] states={"RGR", "RBR", "GRG", "GBG", "BGB", "BRB", "RGB", "RBG", "GRB", "GBR", "BRG", "BGR"};
private static final int MOD=1_000_000_007;
private Integer[][] dp;

private final int solve(int prev, int row) {
    if(dp[prev][row]!=null)
        return dp[prev][row];
    if(row==0)
        return dp[prev][row]=1;
    String prevStr=states[prev];
    long ways=0;
    for(int i=0;i<12;i++) {
        String curr=states[i];
        int j=0;
        while(j<3 && curr.charAt(j)!=prevStr.charAt(j))
            j++;
        if(j==3)
            ways+=solve(i, row-1);
    }
    return dp[prev][row]=(int)(ways%MOD);
}

public int numOfWays(int n) {
    long res=0;
    dp=new Integer[12][n];
    for(int i=0;i<12;i++)
        res+=solve(i, n-1);
    return (int)(res%MOD);
}

void main() {
    int n = 5000;
    IO.println(numOfWays(n));
}