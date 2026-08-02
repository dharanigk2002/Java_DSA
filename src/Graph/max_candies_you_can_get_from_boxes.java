/*
Maximum Candies You Can Get from Boxes

You have n boxes labeled from 0 to n - 1. You are given four arrays: status, candies, keys, and containedBoxes where:

status[i] is 1 if the ith box is open and 0 if the ith box is closed,
candies[i] is the number of candies in the ith box,
keys[i] is a list of the labels of the boxes you can open after opening the ith box.
containedBoxes[i] is a list of the boxes you found inside the ith box.
You are given an integer array initialBoxes that contains the labels of the boxes you initially have. You can take all the candies in any open box and you can use the keys in it to open new boxes and you also can use the boxes you find in it.

Return the maximum number of candies you can get following the rules above.

https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes/description/
*/

public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
    int total=0, n=status.length;
    Queue<Integer> q=new LinkedList();
    boolean[] opened=new boolean[n];
    boolean[] owned=new boolean[n];
    for(int box:initialBoxes) {
        owned[box]=true;
        if(status[box]==1)
            q.add(box);
    }
    while(!q.isEmpty()) {
        int box=q.poll();
        if(opened[box]) continue;
        opened[box]=true;
        total+=candies[box];
        for(int next:containedBoxes[box]) {
            owned[next]=true;
            if(status[next]==1 && !opened[next])
                q.add(next);
        }
        for(int key:keys[box]) {
            status[key]=1;
            if(owned[key] && !opened[key])
                q.add(key);
        }
    }
    return total;
}

void main() {
    int[] status = {1,0,1,0}, candies = {7,5,4,100}, initialBoxes = {0};
    int[][] keys = {{},{},{1},{}}, containedBoxes = {{1,2},{3},{},{}};

    System.out.println(maxCandies(status, candies, keys, containedBoxes, initialBoxes));
}