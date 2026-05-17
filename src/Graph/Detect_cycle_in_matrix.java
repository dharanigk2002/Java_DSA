/*
Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.
https://leetcode.com/problems/detect-cycles-in-2d-grid/description/
*/

private final int[][] dirs={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
public boolean containsCycle(char[][] grid) {
    Queue<int[]> q=new LinkedList();
    int m=grid.length, n=grid[0].length;
    boolean[] visited=new boolean[m*n];
    for(int i=0;i<m;i++) {
        for(int j=0;j<n;j++) {
            if(grid[i][j]=='*') continue;
            char curr=grid[i][j];
            q.add(new int[]{i, j});
            while(!q.isEmpty()) {
                int size=q.size();
                while(size-->0) {
                    int[] cell=q.poll();
                    int r=cell[0], c=cell[1];
                    grid[r][c]='*';
                    for(int[] d:dirs) {
                        int nr=r+d[0], nc=c+d[1];
                        if(nr<0 || nc<0 || nr>=m || nc>=n || grid[nr][nc]!=curr) continue;
                        if(visited[nr*n+nc])
                            return true;
                        visited[nr*n+nc]=true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }
    return false;
}

void main() {
    char[][] grid = {{'a', 'a', 'a', 'a'},{'a', 'b', 'b', 'a'},{'a', 'b', 'b', 'a'},{'a', 'a', 'a', 'a'}};
    boolean isCycle=containsCycle(grid);
    IO.println(isCycle);
}