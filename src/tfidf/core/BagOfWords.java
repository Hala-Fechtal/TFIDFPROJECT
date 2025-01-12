package tfidf.core;

import tfidf.models.Document;
import tfidf.utils.TextCleaner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BagOfWords {
    private Map<String, Map<String, Integer>> documents;

    public BagOfWords(Path folderPath) throws IOException {
        this.documents = Files.list(folderPath)
                .filter(Files::isRegularFile)
                .map(Document::new)
                .collect(Collectors.toMap(
                        Document::getName,
                        doc -> TextCleaner.tokenizeAndCount(doc.getContent())
                ));
    }

    public Map<String, Integer> getDocument(String docName) {
        return documents.getOrDefault(docName, new HashMap<>());
    }

    public Map<String, Map<String, Integer>> getDocuments() {
        return documents;
    }
}
