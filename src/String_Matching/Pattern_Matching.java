// Worst case TC: O(n1 * n2) because it keeps searching same pattern again and again by moving both i and j

void main() {
    String s="aababcdcababc", target="abc";
    int n1=s.length(), n2=target.length();
    List<Integer> indices=new ArrayList<>();
    for(int i=0;i<n1;i++) {
        int j=0;
        while(i+j<n1 && j<n2 && s.charAt(i+j)==target.charAt(j))
            j++;
        if(j==n2)
            indices.add(i);
    }
    IO.println(indices);
}