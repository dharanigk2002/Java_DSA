/*
Longest Arithmetic Subsequence of Given Difference

Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.

A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.

https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/description/
*/

public int longestSubsequence(int[] arr, int diff) {
    int n=arr.length, max=0;
    Map<Integer, Integer> dp=new HashMap();
    dp.put(arr[0], 1);
    for(int i=1;i<n;i++) {
        int freq=dp.getOrDefault(arr[i]-diff, 0)+1;
        dp.put(arr[i], freq);
        max=Math.max(max, freq);
    }
    return max;
}

void main() {
    int[] arr = {1,2,3,4};
    int difference = 1;
    IO.println(longestSubsequence(arr, difference));
}