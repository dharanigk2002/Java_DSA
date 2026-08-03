/*
Minimum Jumps to Reach End via Prime Teleportation

You are given an integer array nums of length n.

You start at index 0, and your goal is to reach index n - 1.

From any index i, you may perform one of the following operations:

Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that nums[j] % p == 0.
Return the minimum number of jumps required to reach index n - 1.

https://leetcode.com/problems/minimum-jumps-to-reach-end-via-prime-teleportation/description/
*/

public int minJumps(int[] nums) {
    int max=nums[0], n=nums.length;
    Map<Integer, List<Integer>> map=new HashMap();
    for(int i=0;i<n;i++) {
        max=Math.max(max, nums[i]);
        map.computeIfAbsent(nums[i], k->new ArrayList()).add(i);
    }
    boolean[] isComposite=new boolean[max+1];
    if(max>=0)
        isComposite[0]=true;
    if(max>=1)
        isComposite[1]=true;
    for(int i=2;i*i<=max;i++) {
        if(!isComposite[i])
            for(int j=i*i;j<=max;j+=i)
                isComposite[j]=true;
    }
    Queue<Integer> q=new LinkedList();
    int level=0;
    q.add(0);
    boolean[] vis=new boolean[n];
    boolean[] used=new boolean[max+1];
    vis[0]=true;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int node=q.poll();
            if(node==n-1)
                return level;
            if(nums[node]>=2 && !isComposite[nums[node]] && !used[nums[node]]) {
                used[nums[node]]=true;
                for(int i=nums[node];i<=max;i+=nums[node])
                    for(int next:map.getOrDefault(i, Collections.emptyList()))
                        if(!vis[next]) {
                            vis[next]=true;
                            q.add(next);
                        }
            }
            if(node>0 && !vis[node-1]) {
                q.add(node-1);
                vis[node-1]=true;
            }
            if(node<n-1 && !vis[node+1]) {
                q.add(node+1);
                vis[node+1]=true;
            }

        }
        level++;
    }
    return -1;
}

void main() {
    int[] nums = {2,3,4,7,9};
    System.out.println(minJumps(nums));
}