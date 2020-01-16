public class OnlineElection {
  class TopVotedCandidate {
    private NavigableMap<Integer, Integer> winnerAtTimes;
    public TopVotedCandidate(int[] persons, int[] times) {
      Map<Integer, Integer> canV = new HashMap<>();
      winnerAtTimes = new TreeMap<>();
      int winner = -1;
      int most = 0;
      winnerAtTimes = new TreeMap<>();
      winnerAtTimes.put(0, 0);

      // This take O(n * log(n))
      for (int i = 0; i < times.length; i++) { // O(n)
        int vote = persons[i];
        int count = canV.compute(vote, (candidateId, voteCount) -> voteCount == null ? 1 : voteCount + 1);
        if (count >= most) {
          winner = vote;
          most = count;
        }
        int time = times[i];
        winnerAtTimes.put(time, winner); // O(log(n))
      }
    }
    public int q(int t) {
      return winnerAtTimes.floorEntry(t).getValue(); // O(log(n)
    }
  }
}