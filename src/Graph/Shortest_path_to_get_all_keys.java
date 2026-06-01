/*
You are given an m x n grid grid where:

'.' is an empty cell.
'#' is a wall.
'@' is the starting point.
Lowercase letters represent keys.
Uppercase letters represent locks.
You start at the starting point and one move consists of walking one space in one of the four cardinal directions. You cannot walk outside the grid, or walk into a wall.

If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.

For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid. This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys. If it is impossible, return -1.

https://leetcode.com/problems/shortest-path-to-get-all-keys/description/
*/

private final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int shortestPathAllKeys(String[] grid) {
    int totalKeys=0, m=grid.length, n=grid[0].length();
    int start=-1, end=-1;
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++) {
            char c=grid[i].charAt(j);
            if(c=='@') {
                start=i;
                end=j;
            } else if(c>='a' && c<='f')
                totalKeys++;
        }
    int target=(1<<totalKeys)-1;
    Queue<int[]> q=new LinkedList();
    int moves=0;
    q.add(new int[]{start, end, 0});
    boolean[][][] visited=new boolean[m][n][64];
    visited[start][end][0]=true;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int r=q.peek()[0], col=q.peek()[1], mask=q.poll()[2];
            if(mask==target)
                return moves;
            for(int[] d:DIRS) {
                int nr=r+d[0], nc=col+d[1];
                if(nr<0 || nc<0 || nr>=m || nc>=n)
                    continue;
                char c=grid[nr].charAt(nc);
                int newMask=mask;
                if(c>='a' && c<='f')
                    newMask|=(1<<(c-'a'));
                else if(c>='A' && c<='F' && (mask&(1<<(c-'A')))==0 || c=='#')
                    continue;
                if(!visited[nr][nc][newMask]) {
                    visited[nr][nc][newMask]=true;
                    q.add(new int[]{nr, nc, newMask});
                }
            }
        }
        moves++;
    }
    return -1;
}

void main() {
    String[] grid = {"@.a..","###.#","b.A.B"};
    IO.println(shortestPathAllKeys(grid));
}