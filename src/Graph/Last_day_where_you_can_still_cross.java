/*
There is a 1-based binary matrix where 0 represents land and 1 represents water. You are given integers row and col representing the number of rows and columns in the matrix, respectively.

Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water. You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates) will be covered with water (i.e., changed to 1).

You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells. You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).

Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

https://leetcode.com/problems/last-day-where-you-can-still-cross/
*/
class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return true;
        if(rank[pu]>rank[pv])
            parent[pu]=pv;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pv]++;
        }
        return false;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int latestDayToCross(int row, int col, int[][] cells) {
    int total=row*col;
    final int TOP=total+1, BOTTOM=total+2;
    DSU ds=new DSU(BOTTOM+1);
    boolean[][] grid=new boolean[row][col];
    for(int i=cells.length-1;i>=0;i--) {
        int r=cells[i][0]-1, c=cells[i][1]-1;
        int id=r*col+c;
        grid[r][c]=true;
        if(r==0)
            ds.union(id, TOP);
        else if(r==row-1)
            ds.union(id, BOTTOM);
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=row || nc>=col || !grid[nr][nc])
                continue;
            grid[nr][nc]=true;
            ds.union(id, nr*col+nc);
        }
        if(ds.find(TOP)==ds.find(BOTTOM))
            return i;
    }
    return -1;
}
void main() {
    int row = 3, col = 3;
    int[][] cells = {{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}};
    IO.println(latestDayToCross(row, col, cells));
}