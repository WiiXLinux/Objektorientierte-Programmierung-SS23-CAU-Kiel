package A7.adressing;

public class Company extends Contact{
    /**
     * Constructor initialising a Company with name, address and name of the CEO.
     * The only way of creating a Company-Contact from public.
     * @param name Name of the company
     * @param address Address of the company's location
     * @param name_ceo Name of the CEO of the company
     */
    public Company(String name, Address address, Name name_ceo) {
        this.name = name_ceo;
        this.address = address;
        this.company_name = name;
    }

    /**
     * Constuctor initialising an empty Company
     */
    public Company() {
        this.name = new Name();
        this.address = new Address();
        this.company_name = "";
    }

    private String company_name;

    /**
     * The following setters and getters are package private to stop unwanted access.
     * toString remains public since it else conflicts with super.toString, this is an exception to the task description telling,
     * that we have to have all methods private or package-private, but there is no other way.
     * There is another way in which we just rename toString to toS and make that method package-private, but to be honest:
     * that's just an ugly solution, and you loose the advantage of toString being called automatically when you want to print the object.
     * Adding to that there are no security problems arising from this since the user should only set the name and address on initialisation of an instance of this class,
     * which is specified in the task description. But with that the need for setters and getters disappears completely.
     */

    String getCompany_name() {
        return company_name;
    }

    void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * Convert this into a simple string representation of a company-contact
     * @return simple string representation of a company-contact
     */
    @Override
    public String toString() {
        return company_name + ": " + address.toString() + ", CEO: " + name.toString();
    }
}
