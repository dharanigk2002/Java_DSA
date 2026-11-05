/*
Minimum Cost to Cut a Stick

Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

You should perform the cuts in order, you can change the order of the cuts as you wish.

The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

Return the minimum total cost of the cuts.

https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
*/
private int optimized(int[] cuts, int n) {
    int c=cuts.length;
    int[] newCut=new int[c+2];
    newCut[c+1]=n;
    for(int i=0;i<c;i++)
        newCut[i+1]=cuts[i];
    Arrays.sort(newCut);
    int[][] dp=new int[c+2][c+2];
    for(int i=c;i>=1;i--)
        for(int j=1;j<=c;j++) {
            if(i>j) continue;
            int min=(int)1e9;
            for(int k=i;k<=j;k++) {
                int cost=newCut[j+1]-newCut[i-1]+dp[i][k-1]+dp[k+1][j];
                min=Math.min(min, cost);
            }
            dp[i][j]=min;
        }
    return dp[1][c];
}


private int f(int[] cut, int i, int j) {
    if(i>j)
        return 0;
    int cost=Integer.MAX_VALUE;
    for(int k=i;k<=j;k++) {
        cost=Math.min(cost, cut[j+1]-cut[i-1]+f(cut, i, k-1)+f(cut, k+1, j));
    }
    return cost;
}

private int f(int i, int j, int[] cuts, Integer[][] dp) {
    if(i>j) return 0;
    if(dp[i][j]!=null) return dp[i][j];
    int min=(int)1e9;
    for(int k=i;k<=j;k++) {
        int cost=cuts[j+1]-cuts[i-1]+f(i, k-1, cuts, dp)+f(k+1, j, cuts, dp);
        min=Math.min(min, cost);
    }
    return dp[i][j]=min;
}

public int minCost(int n, int[] cuts) {
    int m=cuts.length;
    Arrays.sort(cuts);
    int[] cut=new int[m+2];
    cut[0]=0;
    cut[m+1]=n;
    for(int i=1;i<=m;i++)
        cut[i]=cuts[i-1];
    return f(cut, 1, m);
}

void main() {
    int n = 7;
    int[] cuts = {1,3,4,5};
    IO.println(minCost(n, cuts));
}