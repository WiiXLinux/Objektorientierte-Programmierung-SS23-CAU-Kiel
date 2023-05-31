package A7.adressing;

public class Name {
    private String firstName;
    private String name;

    public Name(String prename, String name) {
        this.name = name;
        this.firstName = prename;
    }
    public Name(){
        this.firstName = "";
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String prename) {
        this.firstName = prename;
    }

    @Override
    public String toString() {
        return firstName + " " + name;
    }
}
