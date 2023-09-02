import java.util.List;
import java.util.ArrayList;

public class Menu {
	
	
	private   List<Items> _restaurantAppetizersItems;
	private  List<Items> _restaurantMainDishesItems;
	private  List<Items> _restaurantDessertsItems;
	private   List<Items> _restaurantDrinksItems;
	final String APPETIZER= "Appetizer";
	final String MAIN_DISH= "Main Dish";
	final String DESSERT= "Dessert";
	final String DRINK= "Drink";
	
	
	/*
	 * initialize 4 lists to new ArrayLists- according to item's type.
	 */
	public Menu() {
       
		_restaurantAppetizersItems= new ArrayList<>();
		_restaurantMainDishesItems= new ArrayList<>();
		_restaurantDessertsItems= new ArrayList<>();
		_restaurantDrinksItems= new ArrayList<>();
               
    }	
	
	public List<Items> getAppetizersItems() {
		 return _restaurantAppetizersItems;
	}	
	
	public List<Items> getMainDishesItems() {
		 return _restaurantMainDishesItems;
	}
	public List<Items> getDessertsItems() {
		 return _restaurantDessertsItems;
	}
	public List<Items> getDrinksItems() {
		 return _restaurantDrinksItems;
	}
	
			 
	/*
	 * adds the new item object to the correct list.
	 */
	public void addItem(Items item) {
		getType2(item).add(item);
	}
 
	
//	public void removeItem(Items item) {
//		getType2(item).remove(item);
//	}
	
	
	/*
	 * returns the list's type.
	 */
	public List<Items> getType2(Items item){
		String type= item.getType();
		if (type.equals(APPETIZER)) return _restaurantAppetizersItems;
		if (type.equals(MAIN_DISH))return  _restaurantMainDishesItems;
		if (type.equals(DESSERT))return  _restaurantDessertsItems;
		if (type.equals(DRINK))return _restaurantDrinksItems;
		else return null;
	}

	
}
