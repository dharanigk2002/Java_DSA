/*
Nearest Exit from Entrance in Maze

You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/
*/

private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int nearestExit(char[][] maze, int[] entrance) {
    int m=maze.length, n=maze[0].length;
    Queue<int[]> q=new LinkedList();
    q.add(new int[]{entrance[0], entrance[1]});
    maze[entrance[0]][entrance[1]]='+';
    boolean[][] visited=new boolean[m][n];
    int level=0;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int[] cell=q.poll();
            int r=cell[0], c=cell[1];
            if(maze[r][c]=='.' && (r==0 || r==m-1 || c==0 || c==n-1))
                return level;
            for(int[] d:DIRS) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr==m || nc==n || visited[nr][nc] || maze[nr][nc]=='+')
                    continue;
                q.add(new int[]{nr, nc});
                visited[nr][nc]=true;
            }
        }
        level++;
    }
    return -1;
}

void main() {
    char[][] maze = {{'+','+','+'},{'.','.','.'},{'+','+','+'}};
    int[] entrance = {1,0};
    System.out.println(nearestExit(maze, entrance));
}