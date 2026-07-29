/*
Count the Number of Houses at a Certain Distance I

You are given three positive integers n, x, and y.

In a city, there exist houses numbered 1 to n connected by n streets. There is a street connecting the house numbered i with the house numbered i + 1 for all 1 <= i <= n - 1 . An additional street connects the house numbered x with the house numbered y.

For each k, such that 1 <= k <= n, you need to find the number of pairs of houses (house1, house2) such that the minimum number of streets that need to be traveled to reach house2 from house1 is k.

Return a 1-indexed array result of length n where result[k] represents the total number of pairs of houses such that the minimum streets required to reach one house from the other is k.

Note that x and y can be equal.

https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i/description/
*/

import java.util.Arrays;

public int[] countOfPairs(int n, int x, int y) {
    int[] ans=new int[n];
    int[][] cost=new int[n][n];
    for(int i=0;i<n;i++) {
        Arrays.fill(cost[i], Integer.MAX_VALUE/2);
        cost[i][i]=0;
    }
    for(int i=0;i<n-1;i++)
        cost[i][i+1]=cost[i+1][i]=1;
    cost[x-1][y-1]=cost[y-1][x-1]=1;
    for(int k=0;k<n;k++)
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                cost[i][j]=Math.min(cost[i][j], cost[i][k]+cost[k][j]);
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            if(i!=j)
                ans[cost[i][j]-1]++;
    return ans;
}

void main() {
    int n = 5, x = 2, y = 4;
    System.out.println(Arrays.toString(countOfPairs(n, x, y)));
}
