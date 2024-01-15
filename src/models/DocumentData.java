package models;

import java.util.UUID;

public class DocumentData {
    private final String versionId;
    private String name;
    private String content;

    public DocumentData(String name, String content) {
        this.versionId = UUID.randomUUID().toString();
        this.name = name;
        this.content = content;
    }

    // toString method
    @Override
    public String toString() {
        return "DocumentData{" +
                "versionId='" + versionId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
