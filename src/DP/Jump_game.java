/*
Jump Game

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

https://leetcode.com/problems/jump-game/description/
*/

public boolean canJump(int[] nums) {
    int max=nums[0], n=nums.length;
    for(int i=1;i<n;i++) {
        if(max<i)
            return false;
        max=Math.max(max, i+nums[i]);
        if(max>=n-1)
            return true;
    }
    return true;
}

private boolean f(int[] nums, int i) {
    int n=nums.length;
    if(i>=n-1)
        return true;
    for(int j=1;j<=nums[i];j++)
        if(f(nums, j+i))
            return true;
    return false;
}

void main() {
    int[] nums={2,3,1,1,4};
    IO.println(canJump(nums));
}