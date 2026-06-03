/*
You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.

Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.

Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

https://leetcode.com/problems/greatest-common-divisor-traversal/description/
*/
class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int a, int b) {
        int pa=find(a), pb=find(b);
        if(pa==pb)
            return false;
        if(rank[pa]>rank[pb])
            parent[pb]=pa;
        else if(rank[pb]>rank[pa])
            parent[pa]=pb;
        else {
            parent[pb]=pa;
            rank[pa]++;
        }
        return true;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}

public boolean canTraverseAllPairs(int[] nums) {
    int n=nums.length;
    if(n==1)
        return true;
    for(int num:nums)
        if(num==1)
            return false;

    int max=0;
    for(int num:nums)
        max=Math.max(max, num);
    DSU ds=new DSU(max+1);
    for(int num:nums) {
        int x=num;
        for(int p=2;p*p<=x;p++) {
            if(x%p!=0) continue;
            ds.union(num, p);
            while(x%p==0)
                x/=p;
        }
        if(x>1)
            ds.union(num, x);
    }
    int root=ds.find(nums[0]);
    for(int num:nums)
        if(root!=ds.find(num))
            return false;
    return true;
}
void main() {
    int[] nums = {4, 3, 12, 8};
    IO.println(canTraverseAllPairs(nums));
}