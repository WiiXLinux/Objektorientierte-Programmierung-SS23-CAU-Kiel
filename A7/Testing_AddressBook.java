package A7;

import A7.adressing.Address;
import A7.adressing.AddressBook;
import A7.adressing.Contact;
import A7.adressing.Name;

public class Testing_AddressBook {
    /**
     * Example program to demonstrate the usage of an AddressBook instance.
     * As you can see by the structure of the project, this file is not in the same package as the "real" project files to demonstrate outer access.
     */
    public static void main(String[]args){
        AddressBook addressBook = new AddressBook();
        addressBook.add(new Contact(new Name("Henri", "Heyden"), new Address("notKiel", 90009, "Sagichnich", 8)));
        addressBook.add(new Contact(new Name("Test", "Tester"), new Address("Quatschsdorf", 99999, "Sagichnich2", 3)));
        addressBook.addContact();
    }
}
