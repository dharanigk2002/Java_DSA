/*
Maximum Number of Moves to Kill All Pawns

There is a 50 x 50 chessboard with one knight and some pawns on it. You are given two integers kx and ky where (kx, ky) denotes the position of the knight, and a 2D array positions where positions[i] = [xi, yi] denotes the position of the pawns on the chessboard.

Alice and Bob play a turn-based game, where Alice goes first. In each player's turn:

The player selects a pawn that still exists on the board and captures it with the knight in the fewest possible moves. Note that the player can select any pawn, it might not be one that can be captured in the least number of moves.
In the process of capturing the selected pawn, the knight may pass other pawns without capturing them. Only the selected pawn can be captured in this turn.
Alice is trying to maximize the sum of the number of moves made by both players until there are no more pawns on the board, whereas Bob tries to minimize them.

Return the maximum total number of moves made during the game that Alice can achieve, assuming both players play optimally.

Note that in one move, a chess knight has eight possible positions it can move to, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.

https://leetcode.com/problems/maximum-number-of-moves-to-kill-all-pawns/description/
*/

private static final int[][] DIRS={{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
        {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

private void calcMinDist(int index, int[][] minDist, int[][] pos) {
    int[] dist=new int[2500];
    Arrays.fill(dist, -1);
    Queue<int[]> q=new LinkedList();
    q.add(pos[index]);
    dist[pos[index][0]*50+pos[index][1]]=0;
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int moves=dist[node[0]*50+node[1]];
        for(int[] d:DIRS) {
            int nr=node[0]+d[0], nc=node[1]+d[1];
            if(nr<0 || nc<0 || nr>=50 || nc>=50 || dist[nr*50+nc]!=-1)
                continue;
            dist[nr*50+nc]=1+moves;
            q.add(new int[]{nr, nc});
        }
    }
    for(int i=index+1, n=pos.length;i<n;i++)
        minDist[index][i]=minDist[i][index]=dist[pos[i][0]*50+pos[i][1]];
}
private Integer[][][] dp;
private int f(int idx, int[][] pos, int state, int[][] minDist, int n, int alice) {
    if(dp[idx][state][alice]!=null)
        return dp[idx][state][alice];
    if(state==0)
        return dp[idx][state][alice]=0;
    int result=(alice==1) ? 0 : Integer.MAX_VALUE;
    for(int i=1;i<=n;i++) {
        if((state&(1<<(i-1)))!=0) {
            if(alice==1) {
                result=Math.max(result, minDist[idx][i]+f(i, pos, (state^(1<<(i-1))), minDist, n, alice^1));
            } else {
                result=Math.min(result, minDist[idx][i]+f(i, pos, (state^(1<<(i-1))), minDist, n, alice^1));
            }
        }
    }
    return dp[idx][state][alice]=result;
}

public int maxMoves(int kx, int ky, int[][] positions) {
    int n=positions.length;
    dp=new Integer[n+1][1<<n][2];
    int[][] pos=new int[n+1][2];
    pos[0]=new int[]{kx, ky};
    for(int i=0;i<n;i++)
        pos[i+1]=positions[i];
    int[][] minDist=new int[n+1][n+1];
    for(int i=0;i<n;i++)
        calcMinDist(i, minDist, pos);
    return f(0, pos, (1<<n)-1, minDist, n, 1);
}

void main() {
    int kx = 0;
    int ky = 2;
    int[][] positions = new int[][]{{1, 1}, {2, 2}, {3, 3}};
    IO.println(maxMoves(kx, ky, positions));
}