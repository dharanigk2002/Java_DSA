/*
Tallest Billboard

You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.

You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.

Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
*/

private Integer[][] dp;
private int f(int[] rods, int i, int diff) {
    if(dp[i][diff+5000]!=null)
        return dp[i][diff+5000];
    if(i==rods.length)
        return dp[i][diff+5000]=diff==0 ? 0 : Integer.MIN_VALUE;
    int skip=f(rods, i+1, diff);
    int op1=rods[i]+f(rods, i+1, diff+rods[i]);
    int op2=f(rods, i+1, diff-rods[i]);
    return dp[i][diff+5000]=Math.max(skip, Math.max(op1, op2));
}

private int f(int[] rods, int i, int l1, int l2) {
    if(i==rods.length)
        return l1==l2 ? l1 : 0;
    int skip=f(rods, i+1, l1, l2);
    int op1=f(rods, i+1, l1+rods[i], l2);
    int op2=f(rods, i+1, l1, l2+rods[i]);
    return Math.max(skip, Math.max(op1, op2));
}

public int tallestBillboard(int[] rods) {
    return f(rods, 0, 0, 0);
}

void main() {
    int[] rods={1, 2, 3, 6};
    int n=rods.length;
    dp=new Integer[n+1][100001];
    IO.println(tallestBillboard(rods));
}