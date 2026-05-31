/*
There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/description/
*/
int[] restoreArray(int[][] adjacentPairs) {
    int n=adjacentPairs.length+1;
    int[] res=new int[n];
    Map<Integer, List<Integer>> g=new HashMap();
    for(int[] pairs:adjacentPairs) {
        int u=pairs[0], v=pairs[1];
        g.computeIfAbsent(u, k->new ArrayList()).add(v);
        g.computeIfAbsent(v, k->new ArrayList()).add(u);
    }
    int start=-1;
    for(int node:g.keySet())
        if(g.get(node).size()==1) {
            start=node;
            break;
        }
    res[0]=start;
    res[1]=g.get(res[0]).get(0);
    for(int i=2;i<n;i++) {
        int prev=res[i-2], curr=res[i-1];
        for(int next:g.get(curr))
            if(next!=prev)
                res[i]=next;
    }
    return res;
}

void main() {
    int[][] pairs = {{2, 1}, {3, 4}, {3, 2}};
    int[] restoreArray = restoreArray(pairs);
    IO.println(Arrays.toString(restoreArray));
}