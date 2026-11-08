/*
Unique Paths

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

https://leetcode.com/problems/unique-paths/description/
*/
public int uniquePaths(int m, int n) {
    if(m==0 || n==0)
        return 0;
    if(m==1 && n==1)
        return 1;
    return uniquePaths(m-1, n)+uniquePaths(m, n-1);
}

public int bottomUp(int m, int n) {
    int[][] dp=new int[m+1][n+1];
    for(int i=1;i<=m;i++)
        for(int j=1;j<=n;j++) {
            if(i==1 && j==1)
                dp[i][j]=1;
            else
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
        }
    return dp[m][n];
}

void main() {
    int m = 3, n = 7;
    IO.println(uniquePaths(m, n));
}