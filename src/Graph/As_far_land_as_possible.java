/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

https://leetcode.com/problems/as-far-from-land-as-possible/description/
*/
// Similar to rotting oranges
public int maxDistance(int[][] grid) {
    int n=grid.length;
    final int[][] dirs={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    Queue<Integer> q=new LinkedList();
    int zeros=0;
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            if(grid[i][j]==1)
                q.add(i*n+j);
            else
                zeros++;
    int count=0;
    // If grid has no entry point
    if(zeros==0)
        return -1;
    while(!q.isEmpty()) {
        boolean hasZero=false;
        for(int size=q.size();size>0;size--) {
            int node=q.poll();
            int r=node/n, c=node%n;
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=n || nc>=n || grid[nr][nc]==1) continue;
                q.add(nr*n+nc);
                zeros--;
                grid[nr][nc]=1;
                hasZero=true;
            }
        }
        if(hasZero)
            count++;
    }
    // If grid contains only zeros
    return zeros==0 ? count : -1;
}

void main() {
    int[][] grid = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
    };
    int maxDist = maxDistance(grid);
    IO.println(maxDist);
}