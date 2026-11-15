/*
Maximum Walls Destroyed by Robots

There is an endless straight line populated with some robots and walls. You are given integer arrays robots, distance, and walls:
robots[i] is the position of the ith robot.
distance[i] is the maximum distance the ith robot's bullet can travel.
walls[j] is the position of the jth wall.
Every robot has one bullet that can either fire to the left or the right at most distance[i] meters.

A bullet destroys every wall in its path that lies within its range. Robots are fixed obstacles: if a bullet hits another robot before reaching a wall, it immediately stops at that robot and cannot continue.

Return the maximum number of unique walls that can be destroyed by the robots.

Notes:

A wall and a robot may share the same position; the wall can be destroyed by the robot at that position.
Robots are not destroyed by bullets.

https://leetcode.com/problems/maximum-walls-destroyed-by-robots/description/
*/

private static final int MAX=1_000_000_000;
private Integer[][] dp;

private int lb(int[] nums, int target) {
    int l=0, r=nums.length;
    while(l<r) {
        int mid=l+(r-l)/2;
        if(nums[mid]>=target)
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

private int ub(int[] nums, int target) {
    int l=0, r=nums.length;
    while(l<r) {
        int mid=l+(r-l)/2;
        if(nums[mid]>target)
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

private int countWalls(int[] walls, int l, int r) {
    return ub(walls, r) - lb(walls, l);
}

private int solve(int[][] roboDist, int[] walls, int[][] range, int idx, int prev) {
    int n=roboDist.length;
    if(dp[idx][prev]!=null)
        return dp[idx][prev];
    if(idx==n)
        return dp[idx][prev]=0;
    int leftStart=range[idx][0];
    if(prev==1)
        leftStart=Math.max(leftStart, range[idx-1][1]+1);
    int left=countWalls(walls, leftStart, roboDist[idx][0])+solve(roboDist, walls, range, idx+1, 0);
    int right=countWalls(walls, roboDist[idx][0], range[idx][1])+solve(roboDist, walls, range, idx+1, 1);
    return dp[idx][prev]=Math.max(left, right);
}

public int maxWalls(int[] robots, int[] distance, int[] walls) {
    int n=robots.length;
    dp=new Integer[n+1][n+1];
    int[][] roboDist=new int[n][2];
    for(int i=0;i<n;i++)
        roboDist[i]=new int[]{robots[i], distance[i]};
    Arrays.sort(roboDist, (a, b)->Integer.compare(a[0], b[0]));
    Arrays.sort(walls);
    int[][] range=new int[n][2];
    for(int i=0;i<n;i++) {
        int curr=roboDist[i][0], d=roboDist[i][1];
        int left=i>0 ? roboDist[i-1][0]+1 : 1;
        int right=i<n-1 ? roboDist[i+1][0]-1 : MAX;
        range[i]=new int[]{Math.max(curr-d, left), Math.min(curr+d, right)};
    }
    return solve(roboDist, walls, range, 0, 0);
}

void main() {
    int[] robots = new int[]{10, 2};
    int[] distance = new int[]{5, 1};
    int[] walls = new int[]{5, 2, 7};

    IO.println(maxWalls(robots, distance, walls));
}