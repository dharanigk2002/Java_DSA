/*
Find the Longest Valid Obstacle Course at Each Position

You want to build some obstacle courses. You are given a 0-indexed integer array obstacles of length n, where obstacles[i] describes the height of the ith obstacle.

For every index i between 0 and n - 1 (inclusive), find the length of the longest obstacle course in obstacles such that:

You choose any number of obstacles between 0 and i inclusive.
You must include the ith obstacle in the course.
You must put the chosen obstacles in the same order as they appear in obstacles.
Every obstacle (except the first) is taller than or the same height as the obstacle immediately before it.
Return an array ans of length n, where ans[i] is the length of the longest obstacle course for index i as described above.

https://leetcode.com/problems/find-the-longest-valid-obstacle-course-at-each-position/description/
*/

private int ub(List<Integer> list, int target) {
    int l=0, r=list.size();
    while(l<r) {
        int mid=l+(r-l)/2;
        if(list.get(mid)>target)
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
    int n=obstacles.length;
    int[] dp=new int[n];
    List<Integer> list=new ArrayList();
    for(int i=0;i<n;i++) {
        if(list.isEmpty() || list.getLast()<=obstacles[i]) {
            list.add(obstacles[i]);
            dp[i]=list.size();
        } else {
            int idx=ub(list, obstacles[i]);
            list.set(idx, obstacles[i]);
            dp[i]=idx+1;
        }
    }
    return dp;
}

void main() {
    
}