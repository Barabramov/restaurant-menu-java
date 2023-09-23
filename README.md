# Restaurant Menu Ordering System (Java)

This Java-based program facilitates the ordering process for restaurant customers. It extracts the menu from a text file and offers an intuitive interface for customers to make their selections and place orders.

## Menu Structure:

The menu is contained in a text file, with each menu item spanning three lines:

1. **Description:** A brief text description of the menu item.
2. **Type:** The category to which the item belongs (e.g., appetizer, main course, dessert, drink).
3. **Price:** The cost of the menu item.

## Features & Functionalities:

### 1. Order Placement:

- After selecting desired items, clicking the "Order" button will display a dialog box summarizing the chosen items along with the total price.
- Post review, users have options to either confirm, update, or cancel their order.

### 2. Order Update:

- If users opt to amend their selections, they can do so and then re-click the "Order" button to view the updated summary.

### 3. Order Confirmation:

- Upon confirming the order, users will be prompted to provide their name coupled with an ID number (e.g., `Yossi123456789`).
- The system will then save the order details in a text file named after the user's entered name (assuming name uniqueness).
- Subsequently, it reverts to the main screen, ready to accept another order.

### 4. Order Cancellation:

- If an order is cancelled, the system promptly returns to the main screen, allowing for a new order to be initiated.

_Note: An example menu file is provided with the repository for reference._

## Visual Example:
Below are visual demonstrations of the menu:
- On the left, you can see a menu after selecting several items and their quantity.
- On the right, you can see the system response after clicking the order button.

![ChooseInMenu1](https://github.com/Barabramov/restaurant-menu-java/assets/93996218/59cfa874-02da-466a-86e1-9a5de25c5477)
 &nbsp;&nbsp;&nbsp;![Order](https://github.com/Barabramov/restaurant-menu-java/assets/93996218/ecb37875-afbe-4517-a3a1-e42a35a9c259)


