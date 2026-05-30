/*
You are given two integers n and m that consist of the same number of digits.

You can perform the following operations any number of times:

Choose any digit from n that is not 9 and increase it by 1.
Choose any digit from n that is not 0 and decrease it by 1.
The integer n must not be a prime number at any point, including its original value and after each operation.

The cost of a transformation is the sum of all values that n takes throughout the operations performed.

Return the minimum cost to transform n into m. If it is impossible, return -1.

Explanation:

We perform the following operations:

Increase the first digit, now n = 20.
Increase the second digit, now n = 21.
Increase the second digit, now n = 22.
Decrease the first digit, now n = 12.

https://leetcode.com/problems/digit-operations-to-make-two-integers-equal/description/
*/

private final int INF=10000;
boolean[] prime=new boolean[INF];
void fillPrime() {
    Arrays.fill(prime, true);
    prime[0]=prime[1]=false;
    for(int i=2;i*i<INF;i++)
        if(prime[i]) {
            for(int j=i*i;j<INF;j+=i)
                if(prime[j])
                    prime[j]=false;
        }
}
public int minOperations(int n, int m) {
    fillPrime();
    if(prime[n] || prime[m])
        return -1;
    if(n==m)
        return n;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d->d[1]));
    Map<Integer, Integer> dist=new HashMap();
    q.add(new int[]{n, n});
    dist.put(n, n);
    while(!q.isEmpty()) {
        int node=q.peek()[0], cost=q.poll()[1];
        if(node==m)
            return cost;
        if(cost>dist.get(node)) continue;
        char[] digits=String.valueOf(node).toCharArray();
        int len=digits.length;
        for(int i=0;i<len;i++) {
            char curr=digits[i];
            // increse digit
            if(digits[i]<'9') {
                digits[i]++;
                int next=Integer.parseInt(new String(digits));
                if(!prime[next] && dist.getOrDefault(next, Integer.MAX_VALUE)>next+cost) {
                    dist.put(next, next+cost);
                    q.add(new int[]{next, dist.get(next)});
                }
                digits[i]=curr;
            }
            // decrease digit
            if(digits[i]>'0') {
                digits[i]--;
                if(i!=0 || digits[i]!='0') {
                    int next=Integer.parseInt(new String(digits));
                    if(!prime[next] && dist.getOrDefault(next, Integer.MAX_VALUE)>next+cost) {
                        dist.put(next, next+cost);
                        q.add(new int[]{next, dist.get(next)});
                    }
                }
                digits[i]=curr;
            }
        }
    }
    return -1;
}

void main() {
    int n = 10, m = 12;
    int ops = minOperations(n, m);
    IO.println(ops);
}