package models;

import java.util.*;

public class Document {
    private final String documentId;
    private final String userId;

    private DocumentState state;
//    1 -- N(10) -> 5th

//   <key(1),  DocumentData>

//    <1  --- 10 - 5>

    List<DocumentData> documentDataList = new ArrayList<>();

    HashSet<String> sharedViewUsers; // private access to other user's

    HashSet<String> sharedEditUsers; // private access to other user's

    public Document(String userId, DocumentData documentData) {
        this.documentId = UUID.randomUUID().toString();
        this.userId = userId;
        this.documentDataList.add(documentData);
        sharedViewUsers = new HashSet<>();
        sharedEditUsers = new HashSet<>();
        this.state = DocumentState.DRAFT;
    }

    public String getUserId() {
        return userId;
    }

    public void addDocumentData(DocumentData documentData) {
        documentDataList.add(documentData);
    }
    public void shareEditAccess(String userId) {
        sharedEditUsers.add(userId);
    }

    public boolean hasEditAccess(String userId) {
        if(state == DocumentState.DRAFT) {
            throw new RuntimeException("Can't give edit access because document is in draft state");
        }
        return sharedEditUsers.contains(userId);
    }

    public void shareViewAccess(String userId) {
        if(state == DocumentState.DRAFT) {
          throw new RuntimeException("Can't give share access because document is in draft state");
        }
        sharedViewUsers.add(userId);
    }

    public boolean hasViewAccess(String userId) {
        return sharedViewUsers.contains(userId);
    }

    public void revertLastVersion() {
      if(documentDataList.size() == 0) {
        throw new RuntimeException("Can't revert the version, no version left");
      }
      documentDataList.remove(documentDataList.size() - 1);
    }

    // toString method
    @Override
    public String toString() {
        return "Document{" +
                "documentId='" + documentId + '\'' +
                ", userId='" + userId + '\'' +
                ", documentDataList=" + documentDataList +
                ", sharedViewUsers=" + sharedViewUsers +
                ", sharedEditUsers=" + sharedEditUsers +
                '}';
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

}

//all users -> view it
// owner ->

// 5 users editing it
