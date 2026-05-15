private boolean solve(char[][] board, int row, int col) {
    if(row==9)
        return true;
    int nextRow=row, nextCol=col+1;
    if(nextCol==9) {
        nextCol=0;
        nextRow++;
    }
    if(board[row][col]!='.')
        return solve(board, nextRow, nextCol);
    for(char num='1';num<='9';num++)
        if(isSafe(board, row, col, num)) {
            board[row][col]=num;
            if(solve(board, nextRow, nextCol))
                return true;
            board[row][col]='.';
        }
    return false;
}
private boolean isSafe(char[][] board, int row, int col, char num) {
    // board[row][i]==num -> checking row
    // board[i][col]==num -> check column
    // board[3*(row/3)+i/3][3*(col/3)+i%3]==num -> check 3x3 matrix
    for(int i=0;i<9;i++)
        if(board[row][i]==num || board[i][col]==num || board[3*(row/3)+i/3][3*(col/3)+i%3]==num)
            return false;
    return true;
}

void main() {
    char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
    };
    solve(board, 0, 0);
    for(char[] b:board)
        IO.println(Arrays.toString(b));
}