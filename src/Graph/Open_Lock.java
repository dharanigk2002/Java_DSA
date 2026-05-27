/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

https://leetcode.com/problems/open-the-lock/description/
*/

public int openLock(String[] deadends, String target) {
    Set<String> set=new HashSet(Arrays.asList(deadends));
    String src="0000";
    if(set.contains(src) || set.contains(target)) return -1;
    if(target.equals(src)) return 0;
    Set<String> visited=new HashSet();
    Queue<String> q=new LinkedList();
    q.add(src);
    visited.add(src);
    int moves=0;
    while(!q.isEmpty()) {
        moves++;
        for(int i=q.size();i>0;i--) {
            String node=q.poll();
            char[] ch=node.toCharArray();
            for(int j=0;j<4;j++) {
                char curr=ch[j];
                int n=curr-48;
                ch[j]=(char)(48+(n+1)%10);
                String s1=new String(ch);
                ch[j]=(char)(48+(n+9)%10);
                String s2=new String(ch);
                ch[j]=curr;
                if(s1.equals(target) || s2.equals(target))
                    return moves;
                if(!set.contains(s1) && !visited.contains(s1)) {
                    q.add(s1);
                    visited.add(s1);
                }
                if(!set.contains(s2) && !visited.contains(s2)) {
                    q.add(s2);
                    visited.add(s2);
                }
            }
        }
    }
    return -1;
}

void main() {
    String[] deadEnds = {"0201", "0101", "0102", "1212", "2002"};
    String target = "0202";
    int ways = openLock(deadEnds, target);
    IO.println(ways);
}