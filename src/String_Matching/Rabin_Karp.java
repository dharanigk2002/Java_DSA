private final int MOD=(int)1e9+7;
private final int BASE=256;
private final int MOD2=(int)1e9+33;
private final int BASE2=257;

List<Integer> getStartingIndex(String s, String target) {
    int n=s.length(), m=target.length();
    List<Integer> res=new ArrayList<>(5);
    if(m>n)
        return res;
    long windowHash =getHash(s, BASE, m)%MOD;
    long targetHash=getHash(target, BASE, m)%MOD;
    long maxPower=1;
    for(int i=0;i<m-1;i++)
        maxPower=(maxPower*BASE)%MOD;
    for(int i=0;i<=n-m;i++) {
        if(targetHash== windowHash) {
            for(int j=0;j<m;j++) {
                if (s.charAt(i + j) != target.charAt(j))
                    break;
                if(j==m-1)
                    res.add(i);
            }
        }
        if(i<n-m) {
            int left=s.charAt(i)-97;
            int right=s.charAt(i+m)-97;
            windowHash =(windowHash -left*maxPower%MOD+MOD)%MOD;
            windowHash =(windowHash *BASE+right)%MOD;
        }
    }
    return res;
}

List<Integer> getStartingIndex2(String s, String target) {
    int n=s.length(), m=target.length();
    List<Integer> list=new ArrayList<>();
    if(n<m)
        return list;
    long maxPower1=1, maxPower2=1;
    for(int i=0;i<m-1;i++) {
        maxPower1=(maxPower1*BASE)%MOD;
        maxPower2=(maxPower2*BASE2)%MOD2;
    }
    long[] windowHash=new long[]{getHash(s, BASE, m)%MOD, getHash(s, BASE2, m)%MOD2};
    long[] targetHash=new long[]{getHash(target, BASE, m)%MOD2, getHash(target, BASE2, m)%MOD2};
    for(int i=0;i<=n-m;i++) {
        if(windowHash[0]==targetHash[0] && windowHash[1]==targetHash[1])
            list.add(i);
        if(i<n-m) {
            int left=s.charAt(i)-97;
            int right=s.charAt(i+m)-97;
            windowHash[0]=(windowHash[0]-left*maxPower1+MOD)%MOD;
            windowHash[1]=(windowHash[1]-left*maxPower2+MOD2)%MOD2;
            windowHash[0]=(windowHash[0]*BASE+right)%MOD;
            windowHash[1]=(windowHash[1]*BASE2+right)%MOD2;
        }
    }
    return list;
}

long getHash(String s, int base, int m) {
    long hash=0;
    for(int i=0;i<m;i++) {
        int c=s.charAt(i)-97;
        hash=hash*base+c;
    }
    return hash;
}

void main() {
    String s="ababcabc", target="abc";
    IO.println(getStartingIndex2(s, target));
}