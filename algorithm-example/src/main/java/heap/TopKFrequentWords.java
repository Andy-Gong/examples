package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * source：LeetCode
 * link：https://leetcode-cn.com/problems/top-k-frequent-words
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordAndCount = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (wordAndCount.get(words[i]) == null) {
                wordAndCount.put(words[i], 0);
            }
            wordAndCount.put(words[i], wordAndCount.get(words[i]) + 1);
        }
        PriorityQueue<Entry> wordsQueue = new PriorityQueue<>(
            (entry1, entry2) -> {
                if (entry1.count != entry2.count) {
                    return entry2.count - entry1.count;
                } else {
                    return entry2.word.compareTo(entry1.word);
                }
            });
        for (String word : wordAndCount.keySet()) {
            wordsQueue.add(new Entry(word, wordAndCount.get(word)));
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(wordsQueue.poll().word);
        }
        return result;
    }

    static class Entry {

        String word;
        int count;

        public Entry(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

}
