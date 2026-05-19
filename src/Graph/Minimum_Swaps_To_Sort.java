void main() {
    int[] arr={4, 3, 2, 1};
    int n=arr.length;
    int[][] pair=new int[n][2];
    for(int i=0;i<n;i++)
        pair[i]=new int[]{arr[i], i};
    Arrays.sort(pair, Comparator.comparingInt(a -> a[0]));
    boolean[] visited=new boolean[n];
    int swaps=0;
    for(int i=0;i<n;i++) {
        if(visited[i] || pair[i][1]==i) continue;
        int cycles=0, j=i;
        while(!visited[j]) {
            visited[j]=true;
            cycles++;
            j=pair[i][1];
        }
        if(cycles>1)
            swaps+=(cycles-1);
    }
    IO.println(swaps);
}