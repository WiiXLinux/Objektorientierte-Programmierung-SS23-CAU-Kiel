package A7.adressing;

public class Contact {
    public Contact(Name name, Address address) {
        this.name = name;
        this.address = address;
    }
    public Contact(){
        this.name = new Name();
        this.address = new Address();
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private Name name;
    private Address address;

    @Override
    public String toString(){
        return name.toString() + "\n" + address.toString();
    }
}
