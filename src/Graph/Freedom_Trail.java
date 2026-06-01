/*
In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.

Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.

At the stage of rotating the ring to spell the key character key[i]:

You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.

https://leetcode.com/problems/freedom-trail/description/
*/
public int findRotateSteps(String ring, String key) {
    int n=ring.length(), m=key.length();
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    // cost, ringIndex, keyIndex
    q.add(new int[]{0, 0, 0});
    Set<String> vis=new HashSet();
    ArrayList<Integer>[] keyIndex=new ArrayList[26];
    for(int i=0;i<26;i++)
        keyIndex[i]=new ArrayList();
    for(int i=0;i<n;i++)
        keyIndex[ring.charAt(i)-97].add(i);
    while(!q.isEmpty()) {
        int cost=q.peek()[0], rInd=q.peek()[1], kInd=q.poll()[2];
        if(kInd==m)
            return cost;
        String state=rInd+"+"+kInd;
        if(vis.contains(state)) continue;
        vis.add(state);
        char target=key.charAt(kInd);
        for(int next:keyIndex[target-97]) {
            int rotation=Math.abs(next-rInd);
            int min=Math.min(rotation, n-rotation);
            q.add(new int[]{cost+1+min, next, kInd+1});
        }
    }
    return -1;
}
void main() {
    String ring="godding";
    String key="gd";
    IO.println(findRotateSteps(ring, key));
}