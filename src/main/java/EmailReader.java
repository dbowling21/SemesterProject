import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailReader {

    public static void main(String[] args){
    Properties pro = System.getProperties();
    pro.setProperty("mail.store.protocol", "imaps");
    try {
        Session session = Session.getDefaultInstance(pro, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", "mycrowsawftburner@gmail.com", "CS3250TEAM4");
        System.out.println(store);

        Folder inbox = store.getFolder("Inbox");
        inbox.open(Folder.READ_ONLY);
        Message messages[] = inbox.getMessages();
            for(Message message : messages) {
                System.out.println(message.getContent());
            }
        } catch (MessagingException e){
            e.printStackTrace();
            System.exit(2);
        } catch (Exception e){
            e.printStackTrace();
            System.exit(2);
        }
    }
}
