import java.util.InputMismatchException;

/**
 * Represents one single row from a csv file.
 */

/*
 * TO DO'S
 * -Add proper documentation for constructor
 * -Add comment above this. variables after constructor
 * -Add proper documentation for getters & setters
 * -Add proper documentation for hashCode
 * -Add proper documentation for toReadableString
 *  (Elaborate on what this function is doing)
 * */
public class CustomerOrder {
    // Final Strings for IllegalArgumentExceptions in constructor.


    public static final String BAD_QUANTITY_STRING =
            "The given sale price is less than zero\n";

    public static final int PRODUCT_ID_LENGTH = 12;
    public static final String BAD_PRODUCT_ID_LENGTH =
            "Product ID is not " + PRODUCT_ID_LENGTH + " characters long";

    public static final int DATE_LENGTH = 11;
    public static final String BAD_DATE =
            "Date exceeds " + DATE_LENGTH + " characters long";


    private String productId;
    private Integer quantity;
    private String email;
    private String shippingAddr;
    private String date;


    public CustomerOrder(
            String productId,
            Integer quantity,
            String email,
            String shippingAddr,
            String date
    ) throws IllegalArgumentException {
        if (productId.length() != 12
                || quantity < 0) {
            throw new IllegalArgumentException() {
                @Override
                public String getMessage() {
                    String str = "";
                    str += productId.length() != PRODUCT_ID_LENGTH ?
                            BAD_PRODUCT_ID_LENGTH : "";

                    str += quantity < 0 ? BAD_QUANTITY_STRING : "";

                    str += date.length() != DATE_LENGTH ?
                            BAD_DATE : "";

                    return str;
                }
            };
        }
        this.productId = productId;
        this.quantity = quantity;
        this.email = email;
        this.shippingAddr = shippingAddr;
        this.date = date;
    }

    public CustomerOrder(String[] row) {
        if(row.length != 5) {
            throw new InputMismatchException();
        }
        CustomerOrder customerOrder = new CustomerOrder(
                row[0],
                Integer.parseInt(row[1]),
                row[2],
                row[3],
                row[4]);
        this.productId = customerOrder.getProductId();
        this.quantity = customerOrder.getQuantity();
        this.email = customerOrder.getEmail();
        this.shippingAddr = customerOrder.getShippingAddr();
        this.date = customerOrder.getDate();
    }

    @Override public String toString() {
        return "" + productId + ","
                + quantity + ","
                + email + ","
                + shippingAddr + ","
                + date;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getEmail() {
        return email;
    }

    public String getShippingAddr() {
        return shippingAddr;
    }

    public String getDate() {
        return date;
    }
}

