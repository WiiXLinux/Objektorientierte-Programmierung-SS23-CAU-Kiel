package A7.addressing;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing an address book.
 * Can add Contacts to address book and remove them.
 * Inherits methods from ArrayList(Contact)
 */
public class AddressBook extends ArrayList<Contact> {
    /**
     * Empty constructor to initialize an empty AddressBook.
     * There isn't another constructor.
     * I think it's more elegant like this.
     */
    public AddressBook(){
        super();
    }

    /**
     *  Public method to print the Contacts stored inside the address book.
     */
    public void printContacts(){
        String response = "List of contacts in address book:";
        int counter = 0;
        // Go through each contact. Count them and add their string representations to the response.
        for (Contact contact : this) {
            counter++;
            response += "\n\t" + counter + ": " + contact.toString();
        } if (counter == 0){
            System.out.println("It seems the address book is empty\nGet some friends...");
        }
        System.out.println(response);
    }

    /**
     * Public method to delete a Contact at an index which is read from System.in
     */
    public void deleteContact(){
        // Use System.in as user input
        Scanner scanner = new Scanner(System.in);
        // Repeat until break statement comes.
        while (true){
            // Show the user the current address book and ask them which contact to delete.
            System.out.println("Current address book:");
            printContacts();
            System.out.print("---------------------------------------------------\nPlease specify the index of which contact to delete.\n---------------------------------------------------\n> ");
            int index = -1;
            // Try to get a valid index
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException n) {
                System.out.println("Please specify the index as a number");
                // Try again
                continue;
            }
            // Try to remove the element at index-1
            // (it's index-1 because index will start at 1, because we start at 1 when displaying the address book)
            try{
                this.remove(index-1);
                // Notify the user
                System.out.println("Current address book after removing element " + index + ":");
                this.printContacts();
                // End this subroutine
                break;
            } catch (IndexOutOfBoundsException i){
                // If the user is too stupid to give a valid index, start again.
                // continue doesn't have to be used since this is the last thing before the loop repeats
                System.out.println("Could not remove element " + index + ": IndexOutOfBoundsException");
            }
        }
    }

    /**
     * Public method to add a Contact whose parameters are read from System.in
     * It is possible to leave the parameters empty.
     */
    public void addContact(){
        // Use System.in as user input
        Scanner scanner = new Scanner(System.in);
        // Repeat until break statement comes.
        while (true){
            // Show the user the current address book
            System.out.println("Current address book:");
            printContacts();
            System.out.print("Do you want to add the details of a person or a company?\nP -> Person, C -> Company\n>");
            // Get their response and interpret it
            String nl = scanner.nextLine();
            if (nl.equals("P") || nl.equals("Person")){
                // This is quite self-explanatory, read all parameters.
                System.out.println("Please specify the parameters of the person.\nIf parameters are being left blank, the contact's parameters will so too.");
                String firstname = "";
                String name = "";
                String country = "";
                String city = "";
                int post_num = -1;
                String street = "";
                int apm_num = -1;
                // We have to "try" because in the case that the user inserts some characters when they should actually give a number, the program would crash
                try {
                    System.out.print("First name: ");
                    firstname = scanner.nextLine();
                    System.out.print("Name: ");
                    name = scanner.nextLine();
                    System.out.print("Country: ");
                    country = scanner.nextLine();
                    System.out.print("City: ");
                    city = scanner.nextLine();
                    System.out.print("Posting number: ");
                    post_num = Integer.parseInt(scanner.nextLine());
                    System.out.print("Street: ");
                    street = scanner.nextLine();
                    System.out.print("House number: ");
                    apm_num = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException n){
                    System.out.println("You stated illegal data, try again.");
                    // Important: Start over don't literally continue with what follows after this.
                    continue;
                }
                // Create a Contact containing the parameters.
                Person c = new Person(new Name(firstname, name), new Address(country, city, post_num, street, apm_num));
                // Add it to the address book.
                this.add(c);
                // Notify the user
                System.out.println("Current address book after adding element " + c + ":");
                this.printContacts();
                // Escape
                break;
            } else if (nl.equals("C") || nl.equals("Company")){
                // This is quite self-explanatory, read all parameters.
                System.out.println("Please specify the parameters of the company.\nIf parameters are being left blank, the contact's parameters will so too.");
                String firstname = "";
                String name = "";
                String country = "";
                String city = "";
                int post_num = -1;
                String street = "";
                int apm_num = -1;
                String name_of_company = "";
                // We have to "try" because in the case that the user inserts some characters when they should actually give a number, the program would crash
                try {
                    System.out.print("Name of company: ");
                    name_of_company = scanner.nextLine();
                    System.out.print("First name of CEO: ");
                    firstname = scanner.nextLine();
                    System.out.print("Name of CEO: ");
                    name = scanner.nextLine();
                    System.out.print("Country: ");
                    country = scanner.nextLine();
                    System.out.print("City: ");
                    city = scanner.nextLine();
                    System.out.print("Posting number: ");
                    post_num = Integer.parseInt(scanner.nextLine());
                    System.out.print("Street: ");
                    street = scanner.nextLine();
                    System.out.print("House number: ");
                    apm_num = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException n){
                    // Important: Start over don't literally continue with what follows after this.
                    System.out.println("You stated illegal data, try again.");
                    continue;
                }
                // Create a Contact containing the parameters.
                Company c = new Company(name_of_company, new Address(country, city, post_num, street, apm_num), new Name(firstname, name));
                this.add(c);
                // Notify the user
                System.out.println("Current address book after adding element " + c + ":");
                this.printContacts();
                // Escape
                break;
            }

        }
    }

    /**
     * Searches for a given string s inside the address book and tells where instances have been found.
     * If there are multiple instances of s in one entry it will only count as one match which is useful,
     * since there are companies that have the name of their ceo in their own name.
     * @param s Search queri
     */
    public void search(String s){
        // Generate response and count matches
        String response = "";
        int counter = 0;
        // Repeat for every contact in list:
        for (Contact contact : this) {
            // If the contact contains what's to be searched,
            if (contact.toString().contains(s)){
                // Add this contact to the response and iterate the counter
                response += "Found \"" + s + "\" in entry: " + contact + "\n";
                counter++;
            }
        }
        // This is just for grammar
        if (counter == 0){
            response += "There are 0 matches";
        } else if (counter == 1){
            response += "There is 1 match";
        } else {
            response += "There are " + counter + " matches";
        }
        response += " for \"" + s + "\"";
        // Print the response
        System.out.println(response);
    }
}
