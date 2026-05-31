/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

https://leetcode.com/problems/word-search/description/
*/
final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public boolean exist(char[][] board, String word) {
    int m=board.length, n=board[0].length;
    boolean[][] visited=new boolean[m][n];
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            if(board[i][j]==word.charAt(0) && dfs(board, i, j, 0, word, visited))
                return true;
    return false;
}
private boolean dfs(char[][] board, int i, int j, int src, String word, boolean[][] visited) {
    int m=board.length, n=board[0].length;
    if(src==word.length())
        return true;
    char c=word.charAt(src);
    if(i<0 || j<0 || i>=m || j>=n || visited[i][j] || board[i][j]!=c)
        return false;
    visited[i][j]=true;
    for(int[] d:DIRS)
        if(dfs(board, i+d[0], j+d[1], src+1, word, visited))
            return true;
    return visited[i][j]=false;
}
void main() {
    char[][] board = {
            {'a', 'b', 'c', 'd'},
            {'s', 'f', 'c', 's'},
            {'a', 'd', 'e', 'e'},
    };
    IO.println(exist(board, "abcced"));
}