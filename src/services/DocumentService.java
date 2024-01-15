package services;

import models.Document;
import models.DocumentData;
import models.DocumentState;

import java.util.concurrent.ConcurrentHashMap;

public class DocumentService {
    private ConcurrentHashMap<String, Document> documents = new ConcurrentHashMap<>(); // <documentId, Document>
    private static DocumentService documentServiceServiceInstance = null;

    private DocumentService() {
    }
    public static DocumentService getInstance() {
        if(documentServiceServiceInstance == null) {
            documentServiceServiceInstance = new DocumentService();
        }
        return documentServiceServiceInstance;
    }

    public Document createDocument(String userId, DocumentData documentData) {
        Document document = new Document(userId, documentData);
        documents.put(document.getDocumentId(), document);
        return document;
    }

    public Document getDocument(String userId, String documentId) {
        Document document = documents.get(documentId);
        if(document == null) {
          throw new RuntimeException("Document not present");
        }

        return document;
    }

    public void deleteDocument(String userId, String documentId) {
        Document document = getDocument(userId, documentId);

        if(userId != document.getUserId()) {
            throw new RuntimeException("User don't have the access to delete the document");
        }
        // removing it
        documents.remove(document.getDocumentId());
    }


    public void updateDocument(String userId, String documentId, DocumentData documentData) {
        Document document = getDocument(userId, documentId);
        // checking for author and other users who might have the access
        if(document.getUserId() != userId && !document.hasEditAccess(userId)) {
            throw new RuntimeException("User don't have the permission to update the document : " + userId + " , " + documentId);
        }

        // updating it i.e another version
        document.addDocumentData(documentData);
    }

    public void revertToVersion(String userId, String documentId) {
        Document document = getDocument(userId, documentId);
        if(document.getUserId() != userId) {
            throw new RuntimeException("User don't have the permission to revert the document : " + userId + " , " + documentId);
        }
        document.revertLastVersion();
    }

    public void shareEditAccess(String userId, String documentId) {
        Document document = getDocument(userId, documentId);
        document.shareEditAccess(userId);
    }

    public boolean hasEditAccess(String userId, String documentId) {
        Document document = getDocument(userId, documentId);
        return document.hasEditAccess(userId);
    }

    public void shareViewAccess(String userId, String documentId) {
        Document document = getDocument(userId, documentId);
        document.shareViewAccess(userId);
    }

    public boolean hasViewAccess(String userId, String documentId) {
        Document document = getDocument(userId, documentId);
        return document.hasViewAccess(userId);
    }

    public void setDocumentState(String userId, String documentId, DocumentState documentState) {
        Document document = getDocument(userId, documentId);
        document.setState(documentState);
    }
}
