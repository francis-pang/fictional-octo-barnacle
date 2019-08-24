package crackingthecodinginterview.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

public class SparseSimilarity {
  public List<WordPair> generateDocumentSimilarity(int[][] documents) {
    // Create word to document map
    Map<Integer, List<Integer>> documentListByWord = Collections.unmodifiableMap(
        generateDocumentListForWord(documents));
    // Generate document to document similarity map
    List<WordPair> wordPairList = Collections.unmodifiableList(calculateDocumentSimilarityFor(documentListByWord,
        documents));
    // Return the generated list
    return wordPairList;
  }

  private List<WordPair> calculateDocumentSimilarityFor(Map<Integer, List<Integer>> documentListByWord,
                                                        int[][] documents) {
    Map<Pair, Integer> occurrenceMapForDocumentPair = new HashMap<>();
    documentListByWord.forEach((word, documentLists) -> {
      if (documentLists.size() == 1) {
        return;
      }
      Integer previousDocumentIndex = null;
      ListIterator<Integer> documentIterator = documentLists.listIterator();
      do {
        Integer documentIndex = documentIterator.next();
        if (previousDocumentIndex != null) {
          occurrenceMapForDocumentPair.compute(new Pair(previousDocumentIndex, documentIndex),
              (key, value) -> (value == null) ? 1 : value + 1);
        }
        previousDocumentIndex = documentIndex;
      } while (documentIterator.hasNext());
    });

    List<WordPair> wordPairList = new ArrayList<>();
    occurrenceMapForDocumentPair.forEach((pair, count) -> {
      float union = (documents[pair.firstDocumentId].length + documents[pair.secondDocumentId].length) - count;
      float similarity = count / union;
      wordPairList.add(new WordPair(pair.firstDocumentId, pair.secondDocumentId, similarity));
    });
    return wordPairList;
  }

  private Map<Integer, List<Integer>> generateDocumentListForWord(int[][] documents) {
    Map<Integer, List<Integer>> newDocumentMap = new HashMap<>();
    for (int index = 0; index < documents.length; index++) {
      for (int word : documents[index]) {
        List<Integer> documentList = newDocumentMap.getOrDefault(word, new ArrayList<>());
        documentList.add(index);
      }
    }
    return newDocumentMap;
  }

  class Pair {
    public int firstDocumentId;
    public int secondDocumentId;

    public Pair(int firstDocumentId, int secondDocumentId) {
      this.firstDocumentId = firstDocumentId;
      this.secondDocumentId = secondDocumentId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Pair)) return false;
      Pair pair = (Pair) o;
      return firstDocumentId == pair.firstDocumentId &&
          secondDocumentId == pair.secondDocumentId;
    }

    @Override
    public int hashCode() {
      return Objects.hash(firstDocumentId, secondDocumentId);
    }
  }

  class WordPair {
    public int firstDocumentId;
    public int secondDocumentId;
    public float similarity;

    public WordPair(int firstDocumentId, int secondDocumentId, float similarity) {
      this.firstDocumentId = firstDocumentId;
      this.secondDocumentId = secondDocumentId;
      this.similarity = similarity;
    }
  }
}
