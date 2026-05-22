/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

https://leetcode.com/problems/making-a-large-island/description/
*/

class DSU {
    public int[] parent, size;

    public DSU(int n) {
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
            size[i]=1;
        }
    }

    boolean union(int u, int v) {
        int pu=findParent(u), pv=findParent(v);
        if(pu==pv)
            return true;
        if(size[pu]>size[pv]) {
            parent[pv]=pu;
            size[pu]+=size[pv];
        } else {
            parent[pu]=pv;
            size[pv]+=size[pu];
        }
        return false;
    }

    int findParent(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }
}

final int[][] dirs={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
public int largestIsland(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    DSU ds=new DSU(m*n);
    for(int r=0;r<m;r++) {
        for(int c=0;c<n;c++) {
            if(grid[r][c]==0) continue;
            int node=r*n+c;
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=m || nc>=n || grid[nr][nc]==0) continue;
                ds.union(node, nr*n+nc);
            }
        }
    }
    int max=0;
    for(int r=0;r<m;r++) {
        for(int c=0;c<n;c++) {
            Set<Integer> set=new HashSet();
            if(grid[r][c]==1) continue;
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=m || nc>=n || grid[nr][nc]==0) continue;
                set.add(ds.findParent(nr*n+nc));
            }
            int islands=0;
            for(int parent:set)
                islands+=ds.size[parent];
            max=Math.max(max, islands+1);
        }
    }
    for(int i=0;i<m*n;i++)
        max=Math.max(max, ds.size[i]);
    return max;
}

void main() {
    int[][] grid={
            {1, 1},
            {1, 0}
    };
    int largestIsland=largestIsland(grid);
    IO.println(largestIsland);
}