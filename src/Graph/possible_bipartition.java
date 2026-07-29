/*
Possible Bipartition

We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

https://leetcode.com/problems/possible-bipartition/description/
*/

public boolean possibleBipartition(int n, int[][] dislikes) {
    List<List<Integer>> adj=new ArrayList();
    int[] color=new int[n+1];

    for(int i=0;i<=n;i++) {
        adj.add(new ArrayList());
        color[i]=-1;
    }

    for(int[] dislike:dislikes) {
        adj.get(dislike[0]).add(dislike[1]);
        adj.get(dislike[1]).add(dislike[0]);
    }

    for(int person=1;person<=n;person++) {
        if(color[person]!=-1) continue;
        Queue<Integer> q=new LinkedList();
        color[person]=0;
        q.add(person);
        while(!q.isEmpty()) {
            int node=q.poll();
            for(int next:adj.get(node)) {
                if(color[next]==-1) {
                    color[next]=color[node]^1;
                    q.add(next);
                } else if(color[node]==color[next])
                    return false;
            }
        }
    }
    return true;
}

void main() {
    int n = 4;
    int[][] dislikes = {{1,2},{1,3},{2,4}};
    System.out.println(possibleBipartition(n, dislikes));
}