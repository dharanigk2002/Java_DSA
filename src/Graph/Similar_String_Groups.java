/*
Two strings, X and Y, are considered similar if either they are identical or we can make them equivalent by swapping at most two letters (in distinct positions) within the string X.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many groups are there?

https://leetcode.com/problems/similar-string-groups/description/
*/

class DSU {
    int[] parent, rank;
    DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    boolean union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return true;
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pv]>rank[pu])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return false;
    }
    int find(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=find(parent[node]);
    }
}
private boolean isSimilar(String a, String b) {
    int diff=0, n=a.length();
    for(int i=0;i<n;i++) {
        if(a.charAt(i)!=b.charAt(i))
            diff++;
        if(diff>2)
            return false;
    }
    return diff==0 || diff==2;
}
public int numSimilarGroups(String[] strs) {
    int n=strs.length;
    DSU ds=new DSU(n);
    for(int i=0;i<n;i++)
        for(int j=i+1;j<n;j++)
            if(isSimilar(strs[i], strs[j]))
                ds.union(i, j);
    int comp=0;
    for(int i=0;i<n;i++)
        if(i==ds.find(i))
            comp++;
    return comp;
}


void main() {
    
}