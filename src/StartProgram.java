import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.ListItemHelper;
import model.ListItem;
/**
 * @author Tom Sorteberg - tsorteberg
 * CIS175 - Spring 2021
 * Feb 11, 2021
 */
public class StartProgram {
		
		// Class variable declaration and initialization.
		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();
		
		/**
		 * Method that adds an instance to a database.
		 */
		private static void addAnItem() {
			
			// Prompt for user input.
			System.out.print("Enter a brand: ");
			String brand = in.nextLine();
			System.out.print("Enter an model: ");
			String model = in.nextLine();
			
			// Object instantiation.
			ListItem toAdd = new ListItem(brand, model);
			
			// Method call.
			lih.insertItem(toAdd);

		}
		
		/**
		 * Method that removes an instance from a database.
		 */
		private static void deleteAnItem() {
			
			// Local variable declaration and initialization.
			List<ListItem> foundItems1 = null;
			List<ListItem> foundItems2 = null;
			
			// Prompt for user input.
			System.out.print("Enter the brand to delete: ");
			String brand = in.nextLine();
			System.out.print("Enter the model to delete: ");
			String model = in.nextLine();
			
			// Check if item for deletion exists.
			foundItems1 = lih.searchForItemByBrand(brand);
			foundItems2 = lih.searchForItemByModel(model);
			
			// Selection logic to determine outcome.
			if (foundItems1.isEmpty() || foundItems2.isEmpty()) {
				// Error message.
				System.out.println("\n---- No results found");
			} else {
				ListItem toDelete = new ListItem(brand, model);
				lih.deleteItem(toDelete);
			}
		}
		
		/**
		 * Method that edits and updates an instance from a database.
		 */
		private static void editAnItem() {
			
			// Local variable declaration and initialization.
			List<ListItem> foundItems = null;
			List<Integer> itemList = new ArrayList<Integer>();
			boolean loop = true;
			String searchBy = null;
			String idToEdit = null;
			int idInt = 0;
			
			// While loop for continuous input validation.
			while (loop) {
				// Prompt for user input.
				System.out.println("\nHow would you like to search? ");
				System.out.println("1: Search by brand:");
				System.out.println("2: Search by model:");
				System.out.print("*  Enter selection: ");
				searchBy = in.next();
				in.nextLine();
				
				// Selection logic to determine selection response.
				if (searchBy.equals("1")) {
					System.out.print("\nEnter the brand name: ");
					String brandName = in.nextLine();
					foundItems = lih.searchForItemByBrand(brandName);
					loop = false;
				} else if (searchBy.equals("2")) {
					System.out.print("\nEnter the model: ");
					String modelName = in.nextLine();
					foundItems = lih.searchForItemByModel(modelName);
					loop = false;
				} else {
					// Error message.
					System.out.println("Error: Please enter a valid option.");
				}
			}
			
			// If table is not empty.
			if (!foundItems.isEmpty()) {
				// Reset loop variable.
				loop = true;
				
				// Prompt user.
				System.out.println("\nFound Results.\n");
				
				// Selection logic that lists located objects to console.
				if (searchBy.equals("1")) {
					for (ListItem l : foundItems) {
						System.out.println("ID: " + l.getId() + " * Model: " + l.getModel());
						itemList.add(l.getId());
					}
				}
				else if (searchBy.equals("2")) {
					for (ListItem l : foundItems) {
						System.out.println("ID: " + l.getId() + " * Brand: " + l.getBrand());
						itemList.add(l.getId());
					}
				}
				
				// While loop for continuous input validation.
				while (loop) {
					// Prompt for user input.
					System.out.print("\nEnter the ID you wish to edit: ");
					idToEdit = in.next();
					
					// Try catch block for exception handling.
					try {
						idInt = Integer.parseInt(idToEdit);
						if (itemList.contains(idInt)) {
							loop = false;
						} else {
							// Error message.
							System.out.println("\nError: ID is invalid.\n");
							for (ListItem l : foundItems) {
								System.out.println("ID: " + l.getId() + " * Brand: " + l.getBrand());
							}
						}	
					} catch (NumberFormatException exception) {
						// Error message.
						System.out.println("\nError: ID must be a number.\n");
						for (ListItem l : foundItems) {
							System.out.println("ID: " + l.getId() + " * Brand: " + l.getBrand());
						}
					}
				}
				
				// New ListItem object created via persistence.
				ListItem toEdit = lih.searchForItemById(idInt);
				
				// Reset loop variable.
				loop = true;
				
				// While loop for continuous input validation.
				while (loop) {
					
					// Prompt for user input.
					System.out.println("\nRetrieved " + toEdit.getModel() + " from " + toEdit.getBrand() + "\n");
					System.out.println("1: Update Brand:");
					System.out.println("2: Update Model:");
					System.out.print("*  Enter selection: ");
					String update = in.next();
					in.nextLine();
					
					// Selection logic to determine selection response.
					if (update.equals("1")) {
						System.out.print("New Brand: ");
						String newBrand = in.nextLine();
						toEdit.setBrand(newBrand);
						loop = false;
					} else if (update.equals("2")) {
						System.out.print("New Model: ");
						String newModel = in.nextLine();
						toEdit.setModel(newModel);
						loop = false;
					} else {
						// Error message.
						System.out.println("Error: Please enter a valid option.");
					}
					// Update item via persistence.
					lih.updateItem(toEdit);
				}	
			} else {
				// Error message.
				System.out.println("\n---- No results found");
			}
		}
		
		/**
		 * Method that provides menu selections for the application.
		 */
		public static void runMenu() {
			// Local variable declaration and initialization.
			boolean goAgain = true;
			
			// While loop to generate menu output to console.
			while (goAgain) {
				System.out.println("\n--- Studio Equipment List ---");
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item:");
				System.out.println("*  2 -- Edit an item:");
				System.out.println("*  3 -- Delete an item:");
				System.out.println("*  4 -- View the list:");
				System.out.println("*  5 -- Exit");
				System.out.print("*  Enter selection: ");
				String selection = in.next();
				in.nextLine();
				
				// Selection logic for menu options including input validation.
				if (selection.equals("1")) {
					addAnItem();
				} else if (selection.equals("2")) {
					editAnItem();
				} else if (selection.equals("3")) {
					deleteAnItem();
				} else if (selection.equals("4")) {
					viewTheList();
				} else if (selection.equals("5")) {
					lih.cleanUp();
					System.out.println("   Thank you for using Studio Equipment List.   ");
					goAgain = false;
				} else {
					System.out.println("Error: Please enter a valid option from the list: ");
				}
			}
		}
		
		/**
		 * Default print to console method that generates a list of instances from a database.
		 */
		private static void viewTheList() {
			// Retrieve list via persistence.
			List<ListItem> allItems = lih.showAllItems();
			
			// Blank Line.
			System.out.println("\n");
			System.out.println("Current inventory:");
			System.out.println("\n");
			for(ListItem singleItem : allItems) { 
				System.out.println(singleItem.returnItemDetails());
			}
			// Blank line.
			System.out.println("\n");
			System.out.println("End of inventory:");
		}
		
		/**
		 * Main method.
		 * @param args: String optional.
		 */
		public static void main(String[] args) {
			runMenu();
		}
	}