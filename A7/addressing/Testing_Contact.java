package A7.addressing;

/**
 * Class serving as a namespace for an example program testing inside the addressing package.
 * Demonstrates usage of a Person instance.
 */
public class Testing_Contact {
    /**
     * Example program to demonstrate the usage of a Person instance.
     * Has to be in the same package as the internal project files because it needs to access package-private methods.
     * These have to be package-private or private according to the task description.
     */
    public static void main(String[]args){
        Person contact = new Person();
        contact.setName(new Name("Sören", "Domrös"));
        Address b = new Address("Kiel", 24118, "Christian-Albrechst-Platz", 4);
        contact.setAddress(b);
        // System.out.println(b);
        System.out.println(contact);
    }
}
