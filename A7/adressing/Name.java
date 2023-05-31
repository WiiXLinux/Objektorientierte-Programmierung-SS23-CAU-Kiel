package A7.adressing;

public class Name {
    private String firstName;
    private String name;

    /**
     * Constructor initialising a full name for a person.
     * The only way of creating a Name from public.
     * @param name name of the person
     * @param firstName first Name of the person
     */
    public Name(String firstName, String name) {
        this.name = name;
        this.firstName = firstName;
    }

    /**
     * Constructor initialising an empty name for a person.
     */
    public Name(){
        this.firstName = "";
        this.name = "";
    }

    /**
     * The following setters and getters are package private to stop unwanted access.
     * toString remains public since it else conflicts with super.toString, this is an exception to the task description telling,
     * that we have to have all methods private or package-private, but there is no other way.
     * There is another way in which we just rename toString to toS and make that method package-private, but to be honest:
     * that's just an ugly solution, and you loose the advantage of toString being called automatically when you want to print the object.
     * Adding to that there are no security problems arising from this since the user should only set the parameters on initialisation of an instance of this class,
     * which is specified in the task description. But with that the need for setters and getters disappears completely.
     */

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }


    String getFirstName() {
        return firstName;
    }

    void setFirstName(String prename) {
        this.firstName = prename;
    }

    /**
     * Overrides Object.toString. Gives a nice String representation of a Name.
     * @return nice String representation of a Name.
     */
    @Override
    public String toString() {
        return firstName + " " + name;
    }
}
