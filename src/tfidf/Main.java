package tfidf;

import tfidf.core.TFIDF;
import tfidf.core.BagOfWords;
import tfidf.models.Document;
import tfidf.utils.TextCleaner;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Path to the folder containing .txt files
            String folderPath = "corpus";

            // Initialize BagOfWords and TFIDF
            BagOfWords bow = new BagOfWords(Paths.get(folderPath));
            TFIDF tfidf = new TFIDF(bow);

            // Calculate and print TF-IDF values for a term in a document
            String term = "dont";
            String docName = "Culture et société.txt";
            double tfidfValue = tfidf.getTFIDF(docName, term);

            System.out.printf("TF-IDF for term '%s' in document '%s': %.4f%n", term, docName, tfidfValue);

        } catch (IOException e) {
            System.err.println("Error initializing application: " + e.getMessage());
        }
    }
}