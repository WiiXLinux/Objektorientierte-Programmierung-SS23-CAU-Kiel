package A7.adressing;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook extends ArrayList<Contact> {
    /**
     * Empty constructor to initialize an empty AddressBook.
     */
    public AddressBook(){

    }

    /**
     *  Public method to print the Contacts stored inside the address book.
     */
    public void printContacts(){
        String stackTrace = "List of contacts in address book:";
        int counter = 1;
        for (Contact contact : this) {
            stackTrace += "\n\t" + counter + ": " + contact.toString();
            counter++;
        }
        System.out.println(stackTrace);
    }

    /**
     * Public method to delete a Contact at an index which is read from System.in
     */
    public void deleteContact(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Current address book:");
            printContacts();
            System.out.print("---------------------------------------------------\nPlease specify the index of which contact to delete.\n---------------------------------------------------\n> ");
            int index = -1;
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException n) {
                System.out.println("Please specify the index as a number");
                continue;
            }

            try{
                this.remove(index-1);
                System.out.println("Current address book after removing element " + index + ":");
                this.printContacts();
                break;
            } catch (IndexOutOfBoundsException i){
                System.out.println("Could not remove element " + index + ": IndexOutOfBoundsException");
            }
        }
    }

    /**
     * Public method to add a Contact whose parameters are read from System.in
     * It is possible to leave the parameters empty.
     */
    public void addContact(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Current address book:");
            printContacts();
            System.out.println("Please specify the parameters of the contact.\nIf parameters are being left blank, the contact's parameters will so too.");
            String firstname = "";
            String name = "";
            String country = "";
            String city = "";
            int post_num = -1;
            String street = "";
            int apm_num = -1;
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
                System.out.print("Apartment number: ");
                apm_num = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException n){
                System.out.println("You stated illegal data, try again.");
                continue;
            }
            Contact c = new Contact(new Name(firstname, name), new Address(country, city, post_num, street, apm_num));
            this.add(c);
            System.out.println("Current address book after adding element " + c + ":");
            this.printContacts();
            break;
        }
    }
}
