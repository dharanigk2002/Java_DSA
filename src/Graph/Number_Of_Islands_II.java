/*
You have a 2D grid of ‘N’ rows and ‘M’ columns which are initially filled with water. You are given ‘Q’ queries each consisting of two integers ‘X’ and ‘Y’ and in each query operation, you have to turn the water at position (‘X’, ‘Y’) into a land. You are supposed to find the number of islands in the grid after each query.

An island is a group of lands surrounded by water horizontally, vertically, or diagonally.

https://www.naukri.com/code360/problems/number-of-islands-ii_1266048?leftPanelTabValue=PROBLEM
*/

class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for (int i = 0; i < n; i++)
            parent[i]=i;
    }
    boolean union(int u, int v) {
        int pu=findParent(u), pv=findParent(v);
        if(pu==pv)
            return false;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else {
            parent[pu]=pv;
            rank[pv]++;
        }
        return true;
    }
    int findParent(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }
}
public int[] numOfIslandsII(int n, int m, int[][] q) {
    DSU ds=new DSU(m*n);
    final int[][] dirs={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    int[][] grid=new int[n][m];
    int qns=q.length;
    int[] res=new int[qns];
    int count=0;
    for(int i=0;i<qns;i++) {
        int r=q[i][0], c=q[i][1];
        if(grid[r][c]==1) {
            res[i]=count;
            continue;
        }
        count++;
        grid[r][c]=1;
        for(int[] d:dirs) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=m || grid[nr][nc]==0) continue;
            if(ds.union(r*m+c, nr*m+nc))
                count--;
        }
        res[i]=count;
    }
    return res;
}

void main() {
    int n=3, m=3;
    int[][] q={{0, 0}, {0, 1}, {1, 2}, {2, 1}};
    int[] islands=numOfIslandsII(n, m, q);
    IO.println(Arrays.toString(islands));
}