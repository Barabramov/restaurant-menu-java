import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RestaurantMenuController {

	@FXML
	private GridPane gridPane1;
	@FXML
	private GridPane gridPane2;
	@FXML
	private GridPane gridPane3;
	@FXML
	private GridPane gridPane4;
	@FXML
	private Button orderBtn;
	@FXML
	private VBox vBox;
	private Menu thisMenu;
	private String[][] amountAppetizersItems;
	private String[][] amountMainDishesItems;
	private String[][] amountDessertsItems;
	private String[][] amountDrinksItems;

	
	public void initialize() {
		thisMenu = new Menu();   // create the menu.
		loadData("menu.txt");	// load the data to the menu from the txt file.		
		/* those 4 lines- create for each list from menu class an array with 2 rows,
		 * the first represent boolean input- if the user chose this item, the second represent integer input- amount of the item.
		 */		
		amountAppetizersItems = new String[2][thisMenu.getAppetizersItems().size()];     
		amountMainDishesItems = new String[2][thisMenu.getMainDishesItems().size()];
		amountDessertsItems = new String[2][thisMenu.getDessertsItems().size()];
		amountDrinksItems = new String[2][thisMenu.getDrinksItems().size()];
		orderBtn.setStyle(" -fx-padding: 10; ");
		
		// those 4 lines- create for each item type a grid pane.
		 	
		buildMenu(thisMenu.getAppetizersItems(), gridPane1, amountAppetizersItems);
		buildMenu(thisMenu.getMainDishesItems(), gridPane2, amountMainDishesItems);
		buildMenu(thisMenu.getDessertsItems(), gridPane3, amountDessertsItems);
		buildMenu(thisMenu.getDrinksItems(), gridPane4, amountDrinksItems);
	}

	
	@FXML
	void order(MouseEvent event) {
		CostumerOrder thisOrder = new CostumerOrder();
		String[] options = { "Accept order", "Update order", "Cancel order" };
		orderCheck(thisMenu.getAppetizersItems(), amountAppetizersItems, thisOrder);
		orderCheck(thisMenu.getMainDishesItems(), amountMainDishesItems, thisOrder);
		orderCheck(thisMenu.getDessertsItems(), amountDessertsItems, thisOrder);
		orderCheck(thisMenu.getDrinksItems(), amountDrinksItems, thisOrder);
		int chosen = JOptionPane.showOptionDialog(null, thisOrder.getOrder().getText(), "user answer",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (chosen == 0)acceptOrder(thisOrder.getOrder());  // The user accept and want to order
		if (chosen == 1); // The user want to update order details. 
		if (chosen == 2)initialize(); //user want to start over the order.
	}

	
	/*
	 * the method activates when the user accept and want to order.
	 * it saves the order details in txt file under the user's name and ID number.
	 */
	
	private void acceptOrder(TextArea text) {
		String str = JOptionPane.showInputDialog("Enter your name and your ID number: \nFor example- Yossi123456789: ");
		try {
			FileWriter fw = new FileWriter(str + ".txt");
			fw.write(text.getText());
			fw.close();
		} catch (IOException e) {
			System.out.println("Error");
		}
		JOptionPane.showMessageDialog(null, "Thanks for your order! ");
		initialize();
	}

	
	/*
	 * the method checks which items the user chose and the amount of them.
	 * the chosen items will add to the this costumer order object. 
	 */
	private void orderCheck(List<Items> items, String[][] field, CostumerOrder thisOrder) {
		for (int i = 0; i < field[0].length; i++) {
			if (field[0][i].equals("true") && !field[1][i].equals(null)) {
				thisOrder.addItems(items.get(i), Integer.parseInt(field[1][i]));
			} else
				continue;
		}
	}

	
	private void buildMenu(List<Items> list, GridPane gridPane, String[][] field) {
		organizeGrid(gridPane);
		for (int i = 0; i < list.size(); i++) {
			Items currentItem = list.get(i);
			createCheckBox(gridPane, field, i, currentItem.getDescription());
			createComboBox(gridPane, field, i);
			Label thisItemPrice = new Label(String.valueOf(currentItem.getPrice()));
			thisItemPrice.setStyle("-fx-text-fill: black; -fx-font-size: 12; -fx-alignment: center;");
			thisItemPrice.setPrefWidth(vBox.getPrefWidth() / 3);
			gridPane.add(thisItemPrice, 1, i + 1);
		}
	}
	
	
	private void createComboBox(GridPane gridPane, String[][] field, int i) {
		ComboBox<String> itemsAmount = new ComboBox<>();
		field[1][i] = "0";
		itemsAmount.setValue("0");
		itemsAmount.getItems().addAll("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
		itemsAmount.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				handleComboInput(e, field, i);
			}
		}); 
		itemsAmount.setStyle(
				"-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 12; -fx-alignment: center; -fx-padding-down: 5;");
		gridPane.add(itemsAmount, 2, i + 1);
	}

	private void createCheckBox(GridPane gridPane, String[][] field, int i, String name) {
		CheckBox thisItemName = new CheckBox(name);
		thisItemName.setSelected(false);
		field[0][i] = "false";
		thisItemName.setStyle(
				"-fx-text-fill: black; -fx-font-size: 12; -fx-alignment: center; -fx-padding-down: 5; -fx-padding-right: 10;");
		thisItemName.setPrefWidth(vBox.getPrefWidth() / 3);
		thisItemName.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				handleCheckInput(e, field, i);
			}
		}); 
		gridPane.add(thisItemName, 0, i + 1);
	}
	

	/* 
	 * sets for each combo box an action event that recognize the value change and update the current amount in our array.
	 */
	private void handleComboInput(ActionEvent event, String[][] field, int ind) {
		ComboBox<String> itemsAmount = (ComboBox<String>) event.getSource();
		if (itemsAmount.getValue() != null) {
			field[1][ind] = itemsAmount.getValue();
		} else
			field[1][ind] = "0";
	}
	
	
	/* 
	 * sets for each check box an mouse event that recognize the "clicks" in the box, and update the it (the strings of true and false) in our array.
	 */
	private void handleCheckInput(MouseEvent event, String[][] field, int ind) {
		CheckBox thisItemName = (CheckBox) event.getSource();
		if (thisItemName.isSelected())
			field[0][ind] = "true";
		else
			field[0][ind] = "false";
	}
	
	
	/*
	 * create the headlines.
	 */
	private void organizeGrid(GridPane gridPane) {
		gridPane.setAlignment(Pos.CENTER);
		Label price = new Label("Price");
		Label select = new Label("Chosen dishes");
		Label amount = new Label("Amount");
		setHeaders(price);
		setHeaders(select);
		setHeaders(amount);
		gridPane.add(select, 0, 0);
		gridPane.add(price, 1, 0);
		gridPane.add(amount, 2, 0);
	}

	private void setHeaders(Label label) {
		label.setPrefWidth(vBox.getPrefWidth() / 3);
		label.setStyle(
				"-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 12;-fx-alignment: center;-fx-padding-down: 5;");
	}

	/*
	 * this method load the items details from the txt file. 
	 * after all 3 lines (that represent an item details), it create new item's object and adds it to the menu object.
	 */
	private void loadData(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String description = scanner.nextLine();
				String type = scanner.nextLine();
				double price = Double.parseDouble(scanner.nextLine());
				Items item = new Items(description, type, price);
				thisMenu.addItem(item);
			}
			scanner.close();
		} catch (IOException e) {
			System.out.println("Error reading file: " + fileName);
		}
	}
}
