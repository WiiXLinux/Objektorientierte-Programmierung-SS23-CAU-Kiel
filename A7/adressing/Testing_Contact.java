package A7.adressing;




public class Testing_Contact {
    /**
     * Example program to demonstrate the usage of a Contact instance.
     * Has to be in the same package as the internal project files because it needs to access package-private methods.
     * These have to be package-private or private according to the task description.
     */
    public static void main(String[]args){
        Contact contact = new Contact();
        contact.setName(new Name("Sören", "Domrös"));
        Address b = new Address("Kiel", 24118, "Christian-Albrechst-Platz", 4);
        contact.setAddress(b);
        // System.out.println(b);
        System.out.println(contact);
    }
}
