// The backup class for a pseudo-database
public class DataEntry {
	public static final int SUPPLIER_ID_LENGTH = 8;
	public static final String BAD_SUPPLIER_ID_LENGTH =
	 "Supplier ID is not " + SUPPLIER_ID_LENGTH + " characters long";
	public static final String BAD_QUANTITY_STRING = "The input quantity is less than zero\n";
	public static final String BAD_WHOLESALE_COST = "The given wholesale cost is less than zero\n";
	public static final String BAD_SALE_PRICE = "The given sale price is less than zero\n";
	public static final int PRODUCT_ID_LENGTH = 12;
	public static final String BAD_PRODUCT_ID_LENGTH =
	 "Product ID is not " + PRODUCT_ID_LENGTH + " characters long";
	private String productId;
	private Integer quantity;
	private double wholesaleCost;
	private float salePrice;
	private final String supplierId;
	
	public DataEntry(
	 String productId,
	 Integer quantity,
	 double wholesaleCost,
	 float salePrice,
	 String supplierId
	) throws IllegalArgumentException {
		if(productId.length() != 12
		 || quantity < 0
		 || wholesaleCost < 0
		 || salePrice < 0
		 || supplierId.length() != 8) {
			throw new IllegalArgumentException() {
				@Override public String getMessage() {
					String str = "";
					str += productId.length() != PRODUCT_ID_LENGTH?
					 BAD_PRODUCT_ID_LENGTH:"";
					
					str += supplierId.length() != SUPPLIER_ID_LENGTH
					 ?BAD_SUPPLIER_ID_LENGTH:"";
					
					str += quantity < 0?BAD_QUANTITY_STRING:"";
					
					str += wholesaleCost < 0?BAD_WHOLESALE_COST:"";
					
					str += salePrice < 0?BAD_SALE_PRICE:"";
					return str;
				}
			};
		}
		this.productId = productId;
		this.quantity = quantity;
		this.wholesaleCost = wholesaleCost;
		this.salePrice = salePrice;
		this.supplierId = supplierId;
	}
	
	@Override public String toString() {
		return "" + productId
		 + quantity
		 + wholesaleCost
		 + salePrice
		 + supplierId + "\n";
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setquantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public double getWholesaleCost() {
		return wholesaleCost;
	}
	
	public void setWholesaleCost(double wholesaleCost) {
		this.wholesaleCost = wholesaleCost;
	}
	
	public float getSalePrice() {
		return salePrice;
	}
	
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
	}
	
	public String getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(String supplierId) {
		supplierId = supplierId;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Override public int hashCode() {
		return productId.hashCode();
	}
	
	@Override public boolean equals(Object obj) {
		if(obj instanceof DataEntry) {
			DataEntry de = (DataEntry)obj;
			return this.getProductId().equals(de.getProductId())
			 && this.getSalePrice() == de.getSalePrice()
			 && this.getQuantity() == de.getQuantity()
			 && this.getWholesaleCost() == de.getWholesaleCost()
			 && this.getSupplierId().equals(de.getSupplierId());
		}
		return false;
	}
}
