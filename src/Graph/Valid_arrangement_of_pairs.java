import java.util.logging.Logger;

/*
You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.

https://leetcode.com/problems/valid-arrangement-of-pairs/description/
*/
int[][] validArrangement(int[][] pairs) {
    int n=pairs.length;
    if(n==1)
        return pairs;
    int[][] ans=new int[n][2];
    Map<Integer, Queue<Integer>> g=new HashMap();
    Map<Integer, Integer> degree=new HashMap();
    for(int[] p:pairs) {
        g.computeIfAbsent(p[0], k->new LinkedList()).add(p[1]);
        degree.put(p[0], degree.getOrDefault(p[0], 0)+1);
        degree.put(p[1], degree.getOrDefault(p[1], 0)-1);
    }
    int s=pairs[0][0];
    for(int node:degree.keySet()) {
        int deg=degree.get(node);
        if(deg==1) {
            s=node;
            break;
        }
    }
    List<Integer> res=new ArrayList();
    dfs(g, s, res);
    Collections.reverse(res);
    n=res.size();
    for(int i=1;i<n;i++)
        ans[i-1]=new int[]{res.get(i-1), res.get(i)};
    return ans;
}

private void dfs(Map<Integer, Queue<Integer>> g, int src, List<Integer> ans) {
    Queue<Integer> q=g.get(src);
    while(q!=null && !q.isEmpty()) {
        int node=q.poll();
        dfs(g, node, ans);
    }
    ans.add(src);
}

void main() {
    int[][] pairs = {{5,1},{4,5},{11,9},{9,4}};
    pairs=validArrangement(pairs);
    for(int[] pair:pairs)
        IO.print(Arrays.toString(pair));
}