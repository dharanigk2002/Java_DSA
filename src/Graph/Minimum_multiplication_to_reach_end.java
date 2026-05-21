/*
Given two integers, start and end, along with an array of integers arr[]. In one operation, you can multiply the current value by any element from arr[], and then take the result modulo 1000 to obtain a new value.

Find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
*/

void main() {
    int[] arr = {3, 4, 65};
    int start = 7, end = 175;
    final int MOD=1000;
    int[] dist=new int[MOD];
    Arrays.fill(dist, MOD);
    dist[start]=0;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d->d[0])); // d[] = {level, node}
    q.add(new int[]{0, start});
    while (!q.isEmpty()) {
        int level=q.peek()[0], node=q.poll()[1];
        if(level>dist[node]) continue;
        if(node==end)
            break;
        for(int i:arr) {
            int val=(i*node)%MOD;
            if(dist[val]>level+1) {
                dist[val]=level+1;
                q.add(new int[]{level+1, val});
            }
        }
    }
    int steps=dist[end]!=MOD?dist[end]:-1;
    IO.println(steps);
}