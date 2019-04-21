package leetcode.amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        // Convert the words into HashSet
        Set<String> banWordSet = new HashSet<>();
        for (String word : banned) {
            banWordSet.add(word);
        }

        String[] wordsInParagraph = paragraph.split("[!?',;.\\s]");
        Map<String, Integer> wordFrequency = new HashMap<>();
        // Building the frequency map
        for (String word : wordsInParagraph) {
            String trimedWord = word.replaceAll("[^A-Za-z]", "").toLowerCase();
            if (trimedWord.isEmpty()) {
                continue;
            }
            wordFrequency.put(trimedWord, wordFrequency.getOrDefault(trimedWord, 0) + 1);
        }

        // Build the frequency
        PriorityQueue<WordNode> maxHeap = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            if (banWordSet.contains(entry.getKey())) {
                continue;
            }
            WordNode wordNode = new WordNode(entry.getValue(),entry.getKey());
            maxHeap.add(wordNode);
        }
        return maxHeap.poll().word;
    }

    class WordNode implements Comparable {
        public int frequency;
        public String word;

        public WordNode(int frequency, String word) {
            this.frequency = frequency;
            this.word = word;
        }

        @Override
        public int compareTo (Object o){
            if (o instanceof WordNode) {
                return ((WordNode) o).frequency - this.frequency;
            } else {
                return -1;
            }
        }
    }
}