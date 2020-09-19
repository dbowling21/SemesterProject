import javax.mail.MessagingException;

public class Driver {
    public static void main(String[] args) throws MessagingException {
        JavaMailUtil.sendMail("daniel_ends@yahoo.com");
    }
}
