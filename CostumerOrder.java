
import javafx.scene.control.TextArea;
public class CostumerOrder {
	

	private static double _totalPrice;  //saves the total price of this costumer order.
	private static String _order;  //saves the order details for the bill .
	
	/*
	 * initialize costumer order.
	 */
	public CostumerOrder() {
		_totalPrice=0;  
		_order ="YOUR ORDER IS: \n";
	}
	
	/*
	 * updates the costumer attributes.
	 */
	public void addItems(Items item ,int amount) {
		if (amount>0) {
			_order+= ("Name- "+item.getDescription()+ ",   Price per one- " + item.getPrice()+ ",   Amount- " + amount+ ",   Total for this item- "+(item.getPrice()*amount)+"\n" ); }		
		_totalPrice+= item.getPrice()*amount;
	}
	
	/*
	 * return all the order details.
	 */
	public TextArea  getOrder() {
		TextArea textArea = new TextArea( _order+ "\norder total price- "+_totalPrice);
		textArea.setEditable(false);
		return textArea;
	}
	
	
	
	

}
