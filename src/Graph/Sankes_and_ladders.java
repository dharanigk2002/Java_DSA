/*
You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.

You start on square 1 of the board. In each move, starting from square curr, do the following:

Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
The game ends when you reach the square n2.
A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.

Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.

For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.

https://leetcode.com/problems/snakes-and-ladders/
*/

public int snakesAndLadders(int[][] board) {
    int n=board.length;
    int target=n*n;
    Queue<Integer> q=new LinkedList();
    boolean[] visited=new boolean[target+1];
    q.add(1);
    visited[1]=true;
    int moves=0;
    while(!q.isEmpty()) {
        int size=q.size();
        while(size-->0) {
            int node=q.poll();
            if(node==target)
                return moves;
            for(int dice=1; dice<=6 && dice+node<=target; dice++) {
                int next=dice+node;
                int pos=next-1;
                int row=pos/n, col=pos%n;
                int boardRow=n-1-row;
                int boardCol=((row&1)==0) ? col : n-1-col;
                if(board[boardRow][boardCol]!=-1)
                    next=board[boardRow][boardCol];
                if(!visited[next]) {
                    visited[next]=true;
                    q.add(next);
                }
            }
        }
        moves++;
    }
    return -1;
}

void main() {
    int[][] arr = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
    };
    int moves = snakesAndLadders(arr);
    IO.println(moves);
}