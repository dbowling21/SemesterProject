import java.util.LinkedList;

public class Customer {

    public static LinkedList<String> list;
    private String email;

    public static LinkedList<String> CustomerFiles() {
        return list;
    }


    public Customer(String email){
        this.email = email;
    }

    public static void add(String email){
        list.add(email);
    }

    @Override public String toString() {
        return email + " ";
    }

}
