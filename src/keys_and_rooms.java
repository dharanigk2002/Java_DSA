/*
Keys and Rooms

There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

https://leetcode.com/problems/keys-and-rooms/description/
*/
public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int n=rooms.size();
    boolean[] visited=new boolean[n];
    visited[0]=true;
    Queue<Integer> q=new LinkedList();
    for(int keys:rooms.get(0)) {
        q.add(keys);
        visited[keys]=true;
    }
    while(!q.isEmpty()) {
        int key=q.poll();
        for(int next:rooms.get(key))
            if(!visited[next]) {
                visited[next]=true;
                q.add(next);
            }
    }
    for(boolean room:visited)
        if(!room)
            return false;
    return true;
}


void main() {
    List<List<Integer>> rooms = List.of(
            List.of(1, 3),
            List.of(3, 0, 1),
            List.of(2),
            List.of(0)
    );
    System.out.println(canVisitAllRooms(rooms));
}