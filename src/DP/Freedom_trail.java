/*
Freedom Trail

In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.

Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.

At the stage of rotating the ring to spell the key character key[i]:

You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.

https://leetcode.com/problems/freedom-trail/description/
*/

private final List<Integer>[] list=new List[26];
private int n, m;
private Integer[][] dp;
private final int f(String ring, String key, int idx, int pos) {
    if(dp[idx][pos]!=null)
        return dp[idx][pos];
    if(idx==m)
        return dp[idx][pos]=0;
    int min=Integer.MAX_VALUE;
    for(int target:list[key.charAt(idx)-97]) {
        int diff=Math.abs(target-pos);
        int clock=n-diff;
        int rotate=Math.min(diff, clock);
        min=Math.min(min, 1+rotate+f(ring, key, idx+1, target));
    }
    return dp[idx][pos]=min;
}

public int findRotateSteps(String ring, String key) {
    this.n=ring.length();
    this.m=key.length();
    int[][] dp=new int[m+1][n];
    for(int i=0;i<26;i++)
        list[i]=new ArrayList();

    for(int i=0;i<n;i++)
        list[ring.charAt(i)-97].add(i);

    for(int idx=m-1;idx>=0;idx--) {
        for(int pos=n-1;pos>=0;pos--) {
            int min=Integer.MAX_VALUE;
            for(int target:list[key.charAt(idx)-97]) {
                int diff=Math.abs(target-pos);
                int clock=n-diff;
                int rotate=Math.min(diff, clock);
                min=Math.min(min, 1+rotate+dp[idx+1][target]);
            }
            dp[idx][pos]=min;
        }
    }
    return dp[0][0];
}

void main() {
    String ring = "godding";
    String key = "gd";
    IO.println(findRotateSteps(ring, key));
}