/*
Parallel Courses III

You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.

You must find the minimum number of months needed to complete all the courses following these rules:

You may start taking a course at any time if the prerequisites are met.
Any number of courses can be taken at the same time.
Return the minimum number of months needed to complete all the courses.

Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).

https://leetcode.com/problems/parallel-courses-iii/description/
*/

public int minimumTime(int n, int[][] relations, int[] time) {
    List<List<Integer>> adj=new ArrayList();
    int[] indegree=new int[n];
    int[] finish=new int[n];
    for(int i=0;i<n;i++) {
        adj.add(new ArrayList());
        finish[i]=time[i];
    }
    for(int[] edge:relations) {
        adj.get(edge[0]-1).add(edge[1]-1);
        indegree[edge[1]-1]++;
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        for(int next:adj.get(node)) {
            finish[next]=Math.max(finish[next], finish[node]+time[next]);
            if(--indegree[next]==0)
                q.add(next);
        }
    }
    int answer=0;
    for(int t:finish)
        answer=Math.max(answer, t);
    return answer;
}

void main() {
    int n = 5;
    int[][] relations = {{1,5},{2,5},{3,5},{3,4},{4,5}};
    int[] time = {1,2,3,4,5};
    System.out.println(minimumTime(n, relations, time));
}