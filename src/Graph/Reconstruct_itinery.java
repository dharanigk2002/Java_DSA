/*
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

https://leetcode.com/problems/reconstruct-itinerary/description/
*/
List<String> findItinerary(List<List<String>> tickets) {
    Map<String, Queue<String>> g=new HashMap();
    for(List<String> ticket:tickets)
        g.computeIfAbsent(ticket.get(0), k->new PriorityQueue<>()).add(ticket.get(1));
    List<String> itinery=new LinkedList();
    dfs(g, "JFK", itinery);
    return itinery;
}
void dfs(Map<String, Queue<String>> g, String src, List<String> list) {
    Queue<String> pq=g.get(src);
    while(pq!=null && !pq.isEmpty())
        dfs(g, pq.poll(), list);
    list.addFirst(src);
}
void main() {
    List<List<String>> tickets = List.of(
            List.of("JFK", "SFO"),
            List.of("JFK", "ATL"),
            List.of("SFO", "ATL"),
            List.of("ATL", "JFK"),
            List.of("ATL", "SFO")
    );
    IO.println(findItinerary(tickets));
}