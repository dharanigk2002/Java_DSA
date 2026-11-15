/*
Painting a Grid With Three Different Colors

You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.

Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.

https://leetcode.com/problems/painting-a-grid-with-three-different-colors/description/
*/

private final List<String> states=new ArrayList<>();
private static final int MOD=1_000_000_007;
private static final char[] chars={'R', 'G', 'B'};
private int size=0;

private void solve(int m, StringBuilder sb, char prev) {
    if(m==0) {
        states.add(sb.toString());
        return;
    }
    for(char c:chars) {
        if(c==prev) continue;
        sb.append(c);
        solve(m-1, sb, c);
        sb.deleteCharAt(sb.length()-1);
    }
}
private Integer[][] dp;

private int solve(int prev, int col, int m) {
    if(dp[prev][col]!=null)
        return dp[prev][col];
    if(col==0)
        return dp[prev][col]=1;
    String prevString=states.get(prev);
    long ways=0;
    for(int i=0;i<size;i++) {
        int j=-1;
        String curr=states.get(i);
        for(j=0;j<m;j++)
            if(prevString.charAt(j)==curr.charAt(j))
                break;
        if(j==m)
            ways+=solve(i, col-1, m);
    }
    return dp[prev][col]=(int)(ways%MOD);
}

public int colorTheGrid(int m, int n) {
    solve(m, new StringBuilder(), '*');
    long ways=0;
    this.size=states.size();
    dp=new Integer[this.size][n];
    for(int i=0;i<size;i++)
        ways+=solve(i, n-1, m);
    return (int)(ways%MOD);
}

void main() {
    int m = 5;
    int n = 5;
    IO.println(colorTheGrid(m, n));
}