package A7.testing;

import A7.adressing.*;


public class ContactTesting {
    public static void main(String[]args){
        Contact contact = new Contact();
        contact.setName(new Name("Sören", "Domrös"));
        Address b = new Address("Kiel", 24118, "Christian-Albrechst-Platz", 4);
        contact.setAddress(b);
        // System.out.println(b);
        System.out.println(contact);
    }
}
