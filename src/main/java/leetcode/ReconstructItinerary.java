package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReconstructItinerary {
  public List<String> findItinerary(List<List<String>> tickets) {
    // Keep a map to know which route is visited
    Map<String, PriorityQueue<String>> sourceToDestMap = new HashMap<>();
    tickets.forEach(ticket -> {
      String source = ticket.get(0);
      String dest = ticket.get(1);
      if (sourceToDestMap.containsKey(source)) {
        PriorityQueue<String> priorityQueue = sourceToDestMap.get(source);
        priorityQueue.add(dest);
      } else {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(dest);
        sourceToDestMap.put(source, priorityQueue);
      }
    });

    // Keep
    List<String> airports = new ArrayList<>();
    airports.add("JFK");
    visitAirport(sourceToDestMap, "JFK", airports);
    return airports;
  }

  private boolean visitAirport(Map<String, PriorityQueue<String>> srcDestMap, String src,
                               List<String> airports) {
    if (srcDestMap.isEmpty()) {
      return true;
    }

    PriorityQueue<String> destQueue = srcDestMap.get(src);
    if (destQueue == null) {
      return false;
    }

    List<String> tempDestStore = new ArrayList<>();
    int counter = 0;
    while (destQueue.size() > counter) {
      String dest = destQueue.poll();
      for (int i = 0; i < counter; i++) {
        tempDestStore.add(dest);
        dest = destQueue.poll();
      }
      tempDestStore.forEach(item -> destQueue.add(item));

      if (destQueue.isEmpty()) {
        srcDestMap.remove(src);
      }
      airports.add(dest);
      boolean canCompleteRoute = visitAirport(srcDestMap, dest, airports);
      if (canCompleteRoute) {
        return true;
      }
      airports.remove(airports.size() - 1);
      // If can't work, add back the entry
      destQueue.add(dest);
      srcDestMap.put(src, destQueue);
      counter++;
    }
    return false;
  }

  public static void main(String[] args) {
    List<List<String>> questions = new ArrayList<>();
    List<String> jfkKul = new ArrayList<>();
    jfkKul.add("JFK");
    jfkKul.add("KUL");

    List<String> jfkNrt = new ArrayList<>();
    jfkNrt.add("JFK");
    jfkNrt.add("NRT");

    List<String> nrtJfk = new ArrayList<>();
    nrtJfk.add("NRT");
    nrtJfk.add("JFK");

    questions.add(jfkKul);
    questions.add(nrtJfk);
    questions.add(jfkNrt);

    ReconstructItinerary reconstructItinerary = new ReconstructItinerary();
    List<String> answer = reconstructItinerary.findItinerary(questions);
    System.out.println(answer);
  }
}
