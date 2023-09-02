

public class Items {
	
	private String _itemDescription;  //saves the item name.
	private String _itemType; //saves the item type.
	private double _itemPrice;  //saves the item price.
	
	/*
	 * initialize the item details.
	 */
	public Items(String itemDescription,String itemType, double itemPrice ) {
		_itemDescription=itemDescription;
		_itemType=itemType;
		_itemPrice=itemPrice;
		
	}
	
	
	public String getDescription() {
		return _itemDescription;
	}
		
	
	public String getType() {
		return _itemType;
	}
	
	
	
	public double getPrice() {
		return _itemPrice;
	}
	
}
