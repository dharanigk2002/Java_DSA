/*
Maximum Value of K Coins From Piles

There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.

https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/description/
*/
private Integer[][] dp;
private int f(List<List<Integer>> piles, int i, int k) {
 if(dp[i][k]!=null)
     return dp[i][k];
 if(i==piles.size())
     return dp[i][k]=0;
 int notPick=f(piles, i+1, k);
 int pick=0, sum=0;
 for(int j=0;j<Math.min(k, piles.get(i).size());j++) {
     sum+=piles.get(i).get(j);
     pick=Math.max(pick, sum+f(piles, i+1, k-(j+1)));
 }
 return dp[i][k]=Math.max(notPick, pick);
}

public int maxValueOfCoins(List<List<Integer>> piles, int k) {
    int n=piles.size();
    int[] dp=new int[k+1];
    for(int i=n-1;i>=0;i--) {
        int[] curr=new int[k+1];
        for(int coin=1;coin<=k;coin++) {
            int notPick=dp[coin];
            int pick=0, sum=0;
            for(int j=0;j<Math.min(coin, piles.get(i).size());j++) {
                sum+=piles.get(i).get(j);
                pick=Math.max(pick, sum+dp[coin-(j+1)]);
            }
            curr[coin]=Math.max(notPick, pick);
        }
        dp=curr;
    }
    return dp[k];
}

void main() {
    List<List<Integer>> piles=List.of(
            List.of(1, 100, 3),
            List.of(7, 8, 9)
    );
    int k = 2;
    int n= piles.size();
    dp=new Integer[n+1][k+1];
    IO.println(f(piles, 0, k));
}