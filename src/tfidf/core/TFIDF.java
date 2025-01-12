package tfidf.core;

import java.util.Map;

public class TFIDF {
    private final BagOfWords bow;

    public TFIDF(BagOfWords bow) {
        this.bow = bow;
    }

    public double getTF(String docName, String term) {
        Map<String, Integer> doc = bow.getDocument(docName);
        if (doc.isEmpty() || !doc.containsKey(term)) return 0.0;

        int termCount = doc.get(term);
        int totalTerms = doc.values().stream().mapToInt(Integer::intValue).sum();
        return (double) termCount / totalTerms;
    }

    public double getIDF(String term) {
        long docsWithTerm = bow.getDocuments().values().stream()
                .filter(doc -> doc.containsKey(term))
                .count();
        if (docsWithTerm == 0) return 0.0;

        return Math.log((double) bow.getDocuments().size() / docsWithTerm);
    }

    public double getTFIDF(String docName, String term) {
        return getTF(docName, term) * getIDF(term);
    }
}

