/*
On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given the puzzle board board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

https://leetcode.com/problems/sliding-puzzle/description/
*/
int slidingPuzzle(int[][] board) {
    StringBuilder b=new StringBuilder();
    for(int[] arr:board)
        for(int i=0;i<3;i++)
            b.append(arr[i]);
    String src=b.toString(), target="123450";
    Queue<String> q=new LinkedList();
    int moves=0;
    Set<String> vis=new HashSet();
    vis.add(src);
    q.add(src);
    int[][] swaps={{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
    while(!q.isEmpty()) {
        for(int i=q.size();i>0;i--) {
            String node=q.poll();
            if(node.equals(target))
                return moves;
            int zero=node.indexOf('0');
            for(int swap:swaps[zero]) {
                char[] ch=node.toCharArray();
                char temp=ch[zero];
                ch[zero]=ch[swap];
                ch[swap]=temp;
                String next=new String(ch);
                if(!vis.contains(next)) {
                    vis.add(next);
                    q.add(next);
                }
            }
        }
        moves++;
    }
    return -1;
}
void main() {
    int[][] board = {
            {1, 2, 3},
            {4, 0, 5},
    };
    IO.println(slidingPuzzle(board));
}