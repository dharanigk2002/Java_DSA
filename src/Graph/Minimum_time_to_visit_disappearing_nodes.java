/*
There is an undirected graph of n nodes. You are given a 2D array edges, where edges[i] = [ui, vi, lengthi] describes an edge between node ui and node vi with a traversal time of lengthi units.

Additionally, you are given an array disappear, where disappear[i] denotes the time when the node i disappears from the graph and you won't be able to visit it.

Note that the graph might be disconnected and might contain multiple edges.

Return the array answer, with answer[i] denoting the minimum units of time required to reach node i from node 0. If node i is unreachable from node 0 then answer[i] is -1.

https://leetcode.com/problems/minimum-time-to-visit-disappearing-nodes/description/
*/
public int[] minimumTime(int n, int[][] edges, int[] disappear) {
    int[] ans=new int[n];
    List<List<int[]>> adj=new ArrayList();
    for(int i=0;i<n;i++) {
        ans[i]=Integer.MAX_VALUE;
        adj.add(new ArrayList());
    }
    for(int[] edge:edges) {
        adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        adj.get(edge[1]).add(new int[]{edge[0], edge[2]});
    }
    ans[0]=0;
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    q.add(new int[]{0, 0});
    while(!q.isEmpty()) {
        int node=q.peek()[1], time=q.poll()[0];
        if(time>ans[node]) continue;
        for(int[] next:adj.get(node)) {
            int curTime=time+next[1];
            if(disappear[next[0]]<=curTime)
                continue;
            if(ans[next[0]]>curTime) {
                ans[next[0]]=curTime;
                q.add(new int[]{curTime, next[0]});
            }
        }
    }
    for(int i=0;i<n;i++)
        if(ans[i]==Integer.MAX_VALUE)
            ans[i]=-1;
    return ans;
}
void main() {
    int n = 3;
    int[][] edges = {{0,1,2},{1,2,1},{0,2,4}};
    int[] disappear = {1,3,5};
    int[] minTime = minimumTime(n, edges, disappear);
    IO.println(Arrays.toString(minTime));
}