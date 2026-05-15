void solve(char[][] board, int row) {
    if(row==board.length){
        for(char[] q:board)
            IO.println(Arrays.toString(q));
        IO.println("--".repeat(2*board.length+1));
        return;
    }
    for(int col=0;col<board.length;col++) {
        if(isSafe(board, row, col)) {
            board[row][col]='Q';
            solve(board, row+1);
            board[row][col]='.';
        }
    }
}

boolean isSafe(char[][] board, int row, int col) {
    int n=board.length;
    for(int i=0;i<col;i++)
        if(board[row][i]=='Q')
            return false;
    for(int i=0;i<row;i++)
        if(board[i][col]=='Q')
            return false;
    for(int i=row-1, j=col-1;i>=0 && j>=0;i--, j--)
        if(board[i][j]=='Q')
            return false;
    for(int i=row-1, j=col+1;i>=0 && j<n;i--, j++)
        if(board[i][j]=='Q')
            return false;
    return true;
}
private int rMap=0, cMap=0, lDiag=0, rDiag=0;
void solveBit(char[][] board, int row) {
    if(row==board.length){
        for(char[] q:board)
            IO.println(Arrays.toString(q));
        IO.println("--".repeat(2*board.length+1));
        return;
    }
    for(int col=0;col< board.length;col++) {
        if((rMap&(1<<row))==0 && (cMap&(1<<col))==0 && (lDiag&(1<<(row+col)))==0 && (rDiag&(1<<(row-col+board.length-1)))==0) {
            board[row][col]='Q';
            rMap^=(1<<row);
            cMap^=(1<<col);
            lDiag^=(1<<row+col);
            rDiag^=(1<<row-col+board.length-1);
            solve(board, row+1);
            board[row][col]='.';
            rMap^=(1<<row);
            cMap^=(1<<col);
            lDiag^=(1<<row+col);
            rDiag^=(1<<row-col+board.length-1);
        }
    }
}

void main() {
    int n=4;
    char[][] board=new char[n][n];
    for(char[] b:board)
        Arrays.fill(b, '.');
//    solve(board, 0);
    solveBit(board, 0);
}