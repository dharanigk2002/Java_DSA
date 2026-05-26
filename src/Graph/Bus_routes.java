/*
You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

https://leetcode.com/problems/bus-routes/description/
*/

int numBusesToDestination(int[][] routes, int source, int target) {
    if(source==target)
        return 0;
    int totalBuses=0;
    int n=routes.length;
    Map<Integer, List<Integer>> stopsToBus=new HashMap();
    boolean[] busTaken=new boolean[n];
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<n;i++)
        for(int route:routes[i])
            stopsToBus.computeIfAbsent(route, k->new ArrayList()).add(i);
    for(int bus:stopsToBus.getOrDefault(source, List.of()))
        if(!busTaken[bus]) {
            q.add(bus);
            busTaken[bus]=true;
        }
    while(!q.isEmpty()) {
        totalBuses++;
        for(int size=q.size();size>0;size--) {
            int bus=q.poll();
            for(int next:routes[bus]) {
                if(next==target) return totalBuses;
                for(int buses:stopsToBus.get(next))
                    if(!busTaken[buses]) {
                        busTaken[buses]=true;
                        q.add(buses);
                    }
            }
        }
    }
    return -1;
}

void main() {
    int[][] routes = {{1, 2, 7}, {3, 6, 7}};
    int source = 1, target = 6;
    int buses = numBusesToDestination(routes, source, target);
    IO.println(buses);
}