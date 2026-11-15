/*
Minimum Sum of Values by Dividing Array

You are given two arrays nums and andValues of length n and m respectively.

The value of an array is equal to the last element of that array.

You have to divide nums into m disjoint contiguous subarrays such that for the ith subarray [li, ri], the bitwise AND of the subarray elements is equal to andValues[i], in other words, nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] for all 1 <= i <= m, where & represents the bitwise AND operator.

Return the minimum possible sum of the values of the m subarrays nums is divided into. If it is not possible to divide nums into m subarrays satisfying these conditions, return -1.

https://leetcode.com/problems/minimum-sum-of-values-by-dividing-array/description/
*/

private static final int INF=1_000_000_007;
private final Map<String, Integer> dp=new HashMap();
private final int f(int[] nums, int[] andValues, int i, int j, int andOp) {
    int n=andValues.length;
    String key=i+"_"+j+"_"+andOp;
    Integer res=dp.get(key);
    if(res!=null)
        return res;
    if(i==nums.length)
        return dp.computeIfAbsent(key, k->(j==n ? 0 : INF));
    if(j==n)
        return dp.computeIfAbsent(key, k->INF);
    int ans=INF;
    if((andOp&nums[i])==andValues[j]) {
        ans=nums[i]+f(nums, andValues, i+1, j+1, (1<<17)-1);
    }
    int minVal=Math.min(ans, f(nums, andValues, i+1, j, andOp&nums[i]));
    return dp.computeIfAbsent(key, k->minVal);
}

public int minimumValueSum(int[] nums, int[] andValues) {
    int ans=f(nums, andValues, 0, 0, (1<<17)-1);
    return ans>=INF ? -1 : ans;
}

void main() {
    int[] nums = new int[]{1, 4, 3, 3, 2};
    int[] andValues = new int[]{0, 3, 3, 2};
    IO.println(minimumValueSum(nums, andValues));
}