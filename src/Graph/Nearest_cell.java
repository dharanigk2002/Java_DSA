void main() {
    int[][] grid={
            {1, 0, 1},
            {1, 1, 0},
            {1, 0, 0},
    };
    final int[][] dirs={{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m=grid.length, n=grid[0].length;
    Queue<int[]> q=new LinkedList<>();
    for(int i=0;i<m;i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == 0)
                grid[i][j] = Integer.MAX_VALUE;
            else {
                grid[i][j] = 0;
                q.add(new int[]{i, j});
            }
        }
    }
    while (!q.isEmpty()) {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= m || nc >= n || grid[nr][nc] == 0)
                    continue;
                if(grid[nr][nc]>grid[r][c]+1) {
                    grid[nr][nc]=1+grid[r][c];
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
    for(int[] row:grid)
        IO.println(Arrays.toString(row));
}