package A7;

import A7.adressing.*;

public class Testing_AddressBook {
    /**
     * Example program to demonstrate the usage of an AddressBook instance.
     * As you can see by the structure of the project, this file is not in the same package as the "real" project files to demonstrate outer access.
     */
    public static void main(String[]args){
        // Initialise an empty address book and add some Contacts to it
        AddressBook addressBook = new AddressBook();
        addressBook.add(new Person(new Name("Henri", "Heyden"), new Address("notKiel", 90009, "Sagichnich", 8)));
        addressBook.add(new Person(new Name("Test", "Tester"), new Address("Quatschsdorf", 99999, "Sagichnich2", 3)));
        addressBook.add(new Company("Moe Tester Company", new Address("Kiel", 666, "Hellstreet", 6), new Name("Moe", "Tester")));
        // Search for Tester
        addressBook.search("Tester");
        // Let the tester add something
        addressBook.addContact();
        // Let the tester delete something
        addressBook.deleteContact();
    }
}
