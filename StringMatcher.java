import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class StringMatcher {

    public static void main(String[] args) {

        if(args.length !=2) {
            System.out.println("Not enough arguments provided, please provide Predefinedwords filename and Input filename (with extension) separated by space.");
            System.exit(0);
        }
        //first argument should be the name of the file containing predefined words
        String predefinedWordsFile = args[0];

        //second argument should be the name of the file containing the text against which
        // we will check the predined words
        String inputFileName = args[1];

        //predefined words in file "PredefinedWords.txt" in this same directory
        //scan all these words and add it to a Map, with key being the trimmed and lowercase word and value being the original word
        Map<String, String> predefinedWordMap = new HashMap<>();
        try {
            File file = new File(predefinedWordsFile);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String originalWord = fileScanner.nextLine();

                String processedWord = originalWord.trim().toLowerCase();
                if(!predefinedWordMap.containsKey(processedWord)) {
                    predefinedWordMap.put(processedWord, originalWord);
                }

            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error scanning Predefined words text file!");
            e.printStackTrace();
        }

        //scan words from Input.txt
        //add matching words to a TreeMap sorted by most frequent word first
        Map<String, Integer> freqMap = new HashMap<>();
        try {
            File file = new File(inputFileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) {
                    String word = lineScanner.next().trim().toLowerCase();
                    
                    //check if word is in predefinedSet
                    if(predefinedWordMap.containsKey(word)) {
                        
                        //if it is, add it to the treemap updating its frequency
                        if(!freqMap.containsKey(word)) {
                            freqMap.put(word, 0);
                        }
                        freqMap.put(word, freqMap.get(word)+1);
                    }
                }
                lineScanner.close();
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Sort the words by their freqency in a priority queue
        Queue<String> priorityQ = new PriorityQueue<>((a,b) -> freqMap.get(b) - freqMap.get(a));
        priorityQ.addAll(freqMap.keySet());

        //print matching words
        System.out.println("Predfined word \t\t\t\t  Match count");
        System.out.println("___________________________________________________");
        while(!priorityQ.isEmpty()) {
            String currWord = priorityQ.poll();
            //use original word stored in map while printing
            System.out.println(predefinedWordMap.get(currWord)  + "\t\t\t\t\t\t" + freqMap.get(currWord));
        }
    }

}
