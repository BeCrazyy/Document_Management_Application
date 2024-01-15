import models.Document;
import models.DocumentData;
import models.DocumentState;
import models.User;
import services.DocumentService;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Hello world!");

            UserService userService = UserService.getInstance();

            User newUser1 = userService.createUser("kartikgoel362@gmail.com", "Kartik Goel",
                    "randomPassword");

            System.out.println("User Created : " + newUser1);

            User logInUser = userService.logInUser(newUser1.getUserId(), "randomPassword");

            System.out.println("LoggedIn User : " + logInUser);

            DocumentService documentService = DocumentService.getInstance();

            DocumentData documentData1 = new DocumentData("name1", "content1");

            DocumentData documentData2 = new DocumentData("name2", "content2");

            Document document1 = documentService.createDocument(logInUser.getUserId(), documentData1);

            documentService.setDocumentState(document1.getUserId(), document1.getDocumentId(), DocumentState.PUBLISHED);

            System.out.println("doc1 " + document1);

            Document document2 = documentService.createDocument(logInUser.getUserId(), documentData2);

            documentService.setDocumentState(document2.getUserId(), document2.getDocumentId(), DocumentState.PUBLISHED);


            System.out.println("doc2 " + document2);

            User newUser2 = userService.createUser("user2@gmail.com", "user2",
                    "randomPassword2");

            System.out.println("User Created : " + newUser1);

            documentService.shareViewAccess(newUser2.getUserId(), document1.getDocumentId());

            documentService.shareViewAccess(newUser2.getUserId(), document2.getDocumentId());

            documentService.shareEditAccess(newUser2.getUserId(), document1.getDocumentId());

            documentService.shareEditAccess(newUser2.getUserId(), document2.getDocumentId());

            System.out.println(document1);

            System.out.println(document2);

            DocumentData documentData3 = new DocumentData("name3", "content3");

            DocumentData documentData4 = new DocumentData("name4", "content4");

            documentService.updateDocument(document1.getUserId(), document1.getDocumentId(), documentData3);

            System.out.println(document1);

            documentService.updateDocument(document1.getUserId(), document1.getDocumentId(), documentData4);

            System.out.println(document1);

            documentService.revertToVersion(document1.getUserId(), document1.getDocumentId());

            System.out.println(document1);

            DocumentData documentData5 = new DocumentData("name5", "content5");

            documentService.updateDocument(document1.getUserId(), document1.getDocumentId(), documentData5);

            System.out.println(document1);

            document2 = documentService.getDocument(document2.getUserId(), document2.getDocumentId());

            System.out.println("docccccc --  " + document2);

            documentService.deleteDocument(document2.getUserId(), document2.getDocumentId());

            User newUser3 = userService.createUser("user3@gmail.com", "user3",
                    "randomPassword3");

            boolean view1 = documentService.hasViewAccess(newUser3.getUserId(), document1.getDocumentId());

            System.out.println(view1);

            boolean edit1 = documentService.hasEditAccess(newUser3.getUserId(), document1.getDocumentId());

            System.out.println(edit1);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
