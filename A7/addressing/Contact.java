package A7.addressing;

/**
 * Class representing a template for a contact.
 * We'll inherit the class Person and Company from it.
 */
public abstract class Contact {
    /**
     * Empty constructor. Should not be called by anybody but this package, so it's package-private.
     */
    Contact(){

    }

    // name and address are package-private and not private since it would be impossible to inherit them if they weren't.
    Name name;
    Address address;

    /*
     * The following setters and getters are package private to stop unwanted access.
     * toString remains public since it else conflicts with super.toString, this is an exception to the task description telling,
     * that we have to have all methods private or package-private, but there is no other way.
     * There is another way in which we just rename toString to toS and make that method package-private, but to be honest:
     * that's just an ugly solution, and you loose the advantage of toString being called automatically when you want to print the object.
     * Adding to that there are no security problems arising from this since the user should only set the name and address on initialisation of an instance of this class,
     * which is specified in the task description. But with that the need for setters and getters disappears completely.
     */


    Name getName() {
        return name;
    }

    void setName(Name name) {
        this.name = name;
    }

    Address getAddress() {
        return address;
    }

    void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Override the default toString by making it abstract, which notifies subclasses of Contact to implement it.
     * Should return a nice string representation of the contact.
     * @return a nice string representation of the contact
     */
    @Override
    public abstract String toString();
}
