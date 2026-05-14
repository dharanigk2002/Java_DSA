// TC: O(m+n)
// SC: O(m+n)

void main() {
    String s="aababcdcababc", target="abc";
    String pattern=target+"#"+s;
    int n=pattern.length();
    int p=target.length();
    // lps=longest prefix that matches the suffix
    int[] lps=new int[n];
    for(int i=1;i<n;i++) {
        int prev=lps[i-1];
        while(prev>0 && pattern.charAt(i)!=pattern.charAt(prev))
            prev=lps[prev-1];
        lps[i]=prev+(pattern.charAt(i)==pattern.charAt(prev)?1:0);
    }
    IO.println(Arrays.toString(lps)); // [0, 0, 0, 0, 1, 1, 2, 1, 2, 3, 0, 0, 1, 2, 1, 2, 3]
    List<Integer> indices=new ArrayList<>();
    for(int i=0;i<lps.length;i++)
        if(lps[i]==p)
            indices.add(i-2*p);
    IO.println(indices);
}