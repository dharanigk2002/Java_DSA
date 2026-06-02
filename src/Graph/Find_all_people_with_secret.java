/*
You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

https://leetcode.com/problems/find-all-people-with-secret/description/
*/

public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    int[] timeKnown=new int[n];
    Arrays.fill(timeKnown, Integer.MAX_VALUE);
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] meet:meetings) {
        adj.get(meet[0]).add(new int[]{meet[1], meet[2]});
        adj.get(meet[1]).add(new int[]{meet[0], meet[2]});
    }
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    q.add(new int[]{0, 0});
    q.add(new int[]{0, firstPerson});
    timeKnown[0]=timeKnown[firstPerson]=0;
    while(!q.isEmpty()) {
        int time=q.peek()[0], person=q.poll()[1];
        if(time>timeKnown[person]) continue;
        for(int[] next:adj.get(person)) {
            int meetTime=next[1], p=next[0];
            if(meetTime>=time && timeKnown[p]>meetTime) {
                timeKnown[p]=meetTime;
                q.add(new int[]{meetTime, p});
            }
        }
    }
    List<Integer> ans=new ArrayList();
    for(int i=0;i<n;i++)
        if(timeKnown[i]!=Integer.MAX_VALUE)
            ans.add(i);
    return ans;
}

void main() {
    int n = 6;
    int[][] meetings = {{1,2,5},{2,3,8},{1,5,10}};
    int firstPerson = 1;
    IO.println(findAllPeople(n, meetings, firstPerson));
}