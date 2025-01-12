package ManuelTest;
import tfidf.utils.TextCleaner;
import java.util.Map;
import tfidf.models.Document;
import java.nio.file.Paths;
import tfidf.core.BagOfWords;
import tfidf.core.TFIDF;


public class ManuelT {



        public static void main(String[] args) {
            //textcleanerclass

            String sampleText = "test test. classe et test classe";
            Map<String, Integer> wordCounts = TextCleaner.tokenizeAndCount(sampleText);

            System.out.println("Tokenized Word Counts:");
            wordCounts.forEach((word, count) -> System.out.println(word + ": " + count));
            //document class
            Document doc = new Document(Paths.get("corpus/Culture et société.txt"));

            System.out.println("Document Name: " + doc.getName());
            System.out.println("Document Content: " + doc.getContent());
            //bagofwords class
            try {
                BagOfWords bow = new BagOfWords(Paths.get("corpus"));

                System.out.println("Documents Word Counts:");
                bow.getDocuments().forEach((docName,wordCount) -> {
                    System.out.println("Document: " + docName);
                    wordCounts.forEach((word, count) -> System.out.println("  " + word + ": " + count));
                });
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            //TFIDF class
            try {
                BagOfWords bow = new BagOfWords(Paths.get("corpus"));
                TFIDF tfidf = new TFIDF(bow);

                String term = "dont";
                String docName = "Culture et société.txt";

                double tf = tfidf.getTF(docName, term);
                double idf = tfidf.getIDF(term);
                double tfidfValue = tfidf.getTFIDF(docName, term);

                System.out.printf("TF: %.4f, IDF: %.4f, TF-IDF: %.4f%n", tf, idf, tfidfValue);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            //full processing
            try {
                // Step 1: Load Documents
                BagOfWords bow = new BagOfWords(Paths.get("corpus"));

                // Step 2: Display Tokenized Word Counts
                System.out.println("Documents Word Counts:");
                bow.getDocuments().forEach((docName, wordCount) -> {
                    System.out.println("Document: " + docName);
                    wordCounts.forEach((word, count) -> System.out.println("  " + word + ": " + count));
                });

                // Step 3: Calculate TF-IDF for a Term
                TFIDF tfidf = new TFIDF(bow);
                String term = "dont";
                String docName = "Culture et société.txt";

                double tfidfValue = tfidf.getTFIDF(docName, term);
                System.out.printf("TF-IDF for term '%s' in document '%s': %.4f%n", term, docName, tfidfValue);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }


