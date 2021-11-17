// Erik Rodriguez
// CS 1B Project

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// This class takes in a text file and reports specific traits about the book, it also tests the performance of different hash maps
public class MapPerformance {

    public static void main(String[] args) throws FileNotFoundException {
        File sourceFile = new File("src/frankensteinBook.txt");
        String[] wordsInBook = readFile(sourceFile);

        // Create a HashMap
        Map<String, Integer> hashMap = new HashMap<>();
        long hashMapTime = mapPerformance(wordsInBook, hashMap);
        hashMap = fillMap(wordsInBook, hashMap);


        // Create a TreeMap
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        long treeMapTime = mapPerformance(wordsInBook, treeMap);
        treeMap = (TreeMap<String, Integer>) fillMap(wordsInBook, treeMap);

        // Create a LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        long linkedHashMapTime = mapPerformance(wordsInBook, linkedHashMap);
        linkedHashMap = (LinkedHashMap<String, Integer>) fillMap(wordsInBook, linkedHashMap);

        int totalWords = totalWords(hashMap);

        System.out.println("Filename: " + sourceFile.getName() + "\n" +
                "Build HashMap: " + hashMapTime + " millisecs\n" +
                "Build LinkedHashMap: " + linkedHashMapTime + " millisecs\n" +
                "Build TreeMap: " + treeMapTime + " millisecs\n" +
                "Total Words: " + totalWords + "\n" +
                "Unique words: " + hashMap.size() + "\n" +
                "HashMap: " + showPairs(hashMap, 15) + "\n" + // the second argument is how many values you want shown
                "LinkedHashMap: " + showPairs(linkedHashMap, 15) + "\n" +
                "TreeMap: " + showPairs(treeMap, 15) + "\n"
        );


    }

    // tests how many milliseconds the map gets filled
    private static long mapPerformance(String[] wordsInBook, Map<String, Integer> map) {
        long startTime = System.currentTimeMillis();
        fillMap(wordsInBook, map);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    // fills the map with the words from the text file given
    private static Map<String, Integer> fillMap(String[] wordsInBook, Map<String, Integer> map) {
        for (String word :
                wordsInBook) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1); // adds one to the value which is map.get(word)
            } else
                map.put(word, 1);
        }
        return map;
    }

    // reads the file and returns the result into one array of strings
    private static String[] readFile(File file) throws FileNotFoundException {
        Scanner scnr = new Scanner(file);
        String str = "";

        while (scnr.hasNext()) {
            str += scnr.next() + " ";
        }
        str = str.replace("_", "");
        str = str.toLowerCase();
        String[] result = str.split("\\W+");

        return result;
    }

    // returns the total words in the whole text file
    private static int totalWords(Map<String, Integer> map) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            total += entry.getValue();
        }
        return total;
    }

    // returns a pair of key and values and takes in an argument for how many pairs to be shown
    private static String showPairs(Map<String, Integer> map, int howManyValues) {
        String mapPairs = "";
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            mapPairs += entry.getKey() + "=" + entry.getValue() + " ";
            count++;
            if (count == howManyValues) {
                return mapPairs;
            }
        }
        return null;
    }
}
