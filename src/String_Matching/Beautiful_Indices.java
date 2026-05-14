/*
Find Beautiful Indices in the Given Array II

You are given a 0-indexed string s, a string a, a string b, and an integer k.

An index i is beautiful if:

0 <= i <= s.length - a.length
s[i..(i + a.length - 1)] == a
There exists an index j such that:
0 <= j <= s.length - b.length
s[j..(j + b.length - 1)] == b
|j - i| <= k
Return the array that contains beautiful indices in sorted order from smallest to largest.
*/

public List<Integer> beautifulIndices(String s, String a, String b, int k) {
    String p1=a+"#"+s, p2=b+"#"+s;
    int[] lps1=buildLPS(p1);
    int[] lps2=buildLPS(p2);
    List<Integer> list1=getIndex(p1, a.length(), lps1);
    List<Integer> list2=getIndex(p2, b.length(), lps2);
    List<Integer> res=new ArrayList();
    for(int i:list1) {
        int lb=lowerBound(list2, i);
        if(lb<list2.size() && Math.abs(list2.get(lb)-i)<=k)
            res.add(i);
        else if(lb>0 && Math.abs(list2.get(lb-1)-i)<=k)
            res.add(i);
    }
    return res;
}

private int lowerBound(List<Integer> list, int n) {
    int l=0, r=list.size();
    while(l<r) {
        int mid=l+(r-l)/2;
        if(list.get(mid)<n)
            l=mid+1;
        else
            r=mid;
    }
    return l;
}

private int[] buildLPS(String p) {
    int n=p.length();
    int[] lps=new int[n];
    for(int i=1;i<n;i++) {
        int prev=lps[i-1];
        while(prev>0 && p.charAt(i)!=p.charAt(prev))
            prev=lps[prev-1];
        lps[i]=prev+(p.charAt(i)==p.charAt(prev)?1:0);
    }
    return lps;
}

private List<Integer> getIndex(String s, int p, int[] lps) {
    int n=s.length();
    List<Integer> res=new ArrayList();
    for(int i=0;i<n;i++)
        if(lps[i]==p)
            res.add(i-2*p);
    return res;
}

void main() {
    String s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel";
    int k = 15;
    IO.println(beautifulIndices(s, a, b, k));
}