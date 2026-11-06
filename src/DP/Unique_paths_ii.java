/*
Unique Paths II

You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

https://leetcode.com/problems/unique-paths-ii/description/
*/

public int uniquePathsWithObstacles(int[][] obstacleGrid) {
  int m=obstacleGrid.length, n=obstacleGrid[0].length;
  if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1)
    return 0;
  int[] dp=new int[n];
  for(int i=m-1;i>=0;i--) {
    int[] next=dp.clone();
    for(int j=n-1;j>=0;j--)
      if(i==m-1 && j==n-1)
        dp[j]=1;
      else if(obstacleGrid[i][j]==0) {
        int down=0, right=0;
        down=next[j];
        if(j<n-1)
          right=dp[j+1];
        dp[j]=down+right;
      }
      else
        dp[j]=0;
  }
  return dp[0];
}

void main() {
    
}