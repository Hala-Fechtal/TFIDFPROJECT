package tfidf.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Document {
    private final String name;
    private final String content;

    public Document(Path filePath) {
        this.name = filePath.getFileName().toString();
        this.content = readContent(filePath);
    }

    private String readContent(Path filePath) {
        try {
            return Files.readAllLines(filePath).toString();
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            return "";
        }
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
