/*
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

https://leetcode.com/problems/perfect-squares/description/
*/

int numSquares(int n) {
    boolean[] visited=new boolean[n+1];
    Queue<Integer> q=new LinkedList();
    q.add(n);
    visited[n]=true;
    int moves=0;
    while(!q.isEmpty()) {
        moves++;
        for(int i=q.size();i>0;i--) {
            int node=q.poll();
            for(int next=1;next*next<=node;next++) {
                int sum=node-next*next;
                if(sum==0)
                    return moves;
                if(sum<0)
                    break;
                if(!visited[sum]) {
                    visited[sum]=true;
                    q.add(sum);
                }
            }
        }
    }
    return -1;
}

void main() {
    int steps=numSquares(12);
    IO.println(steps);
}