/*
You are given a positive integer k. You are also given:

a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
The two arrays contain integers from 1 to k.

You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.

The matrix should also satisfy the following conditions:

The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.

https://leetcode.com/problems/build-a-matrix-with-conditions/submissions/2019228360/
*/
public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
    List<Integer> row=topo(k, rowConditions);
    List<Integer> col=topo(k, colConditions);
    if(row.size()!=k || col.size()!=k)
        return new int[0][0];
    int[][] grid=new int[k][k];
    int[] rowPos=new int[k+1];
    int[] colPos=new int[k+1];
    for(int i=0;i<k;i++) {
        rowPos[row.get(i)]=i;
        colPos[col.get(i)]=i;
    }
    for(int num=1;num<=k;num++)
        grid[rowPos[num]][colPos[num]]=num;
    return grid;
}

private List<Integer> topo(int k, int[][] conditions) {
    List<List<Integer>> adj=new ArrayList();
    List<Integer> order=new ArrayList();
    for(int i=0;i<k;i++)
        adj.add(new ArrayList());
    int[] indegree=new int[k];
    for(int[] c:conditions) {
        adj.get(c[0]-1).add(c[1]-1);
        indegree[c[1]-1]++;
    }
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<k;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        order.add(node+1);
        for(int next:adj.get(node))
            if(--indegree[next]==0)
                q.add(next);
    }
    return order;
}
void main() {
    int k = 3;
    int[][] rowConditions = {{1,2},{3,2}};
    int[][] colConditions = {{2,1},{3,2}};
    int[][] matrix=buildMatrix(k, rowConditions, colConditions);
    for(int[] row:matrix)
        IO.println(Arrays.toString(row));
}