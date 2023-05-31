package A7.adressing;

public class Address{
    private String country, city, street;
    private int postingNumber, apartmentNumber;

    public Address(String country, String city, int postingNumber, String street, int apartmentNumber) {
        if (country.equals("")){
            this.country = null;
        } else
            this.country = country;
        this.city = city;
        this.street = street;
        this.postingNumber = postingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public Address(String city, int postingNumber, String street, int apartmentNumber) {
        this.country = null;
        this.city = city;
        this.street = street;
        this.postingNumber = postingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public Address(){
        this.country = null;
        this.city = "";
        this.street = "";
        this.postingNumber = -1;
        this.apartmentNumber = -1;
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

    String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    String getCity() {
        return city;
    }

    void setCity(String city) {
        this.city = city;
    }

    String getStreet() {
        return street;
    }

    void setStreet(String street) {
        this.street = street;
    }

    int getPostingNumber() {
        return postingNumber;
    }

    void setPostingNumber(int postingNumber) {
        this.postingNumber = postingNumber;
    }

    int getApartmentNumber() {
        return apartmentNumber;
    }

    void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString(){
        return country == null ?
                postingNumber + " " + city + ", " + street + " " + apartmentNumber :
                country + ", "  + postingNumber + " " + city + ", " + street + " " + apartmentNumber;


    }
}