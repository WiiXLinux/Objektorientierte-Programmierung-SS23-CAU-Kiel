package A7.adressing;

public class Person extends Contact {
    /**
     * Constructor initialising a Person with name and address.
     * The only way of creating a Person-Contact from public.
     * @param name Name of the person
     * @param address Address of the person
     */
    public Person(Name name, Address address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Default constructor
     */
    public Person() {
        this.name = new Name();
        this.address = new Address();
    }

    /**
     * Convert this into a simple string representation of a person-contact
     * @return simple string representation of a person-contact
     */
    @Override
    public String toString(){
        return name.toString() + ": " + address.toString();
    }
}
