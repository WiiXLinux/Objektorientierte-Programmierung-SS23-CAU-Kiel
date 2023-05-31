package A7.adressing;

public class Address{
    private String country, city, street;
    private int postingNumber, apartmentNumber;

    public Address(String country, String city, int postingNumber, String street, int apartmentNumber) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostingNumber() {
        return postingNumber;
    }

    public void setPostingNumber(int postingNumber) {
        this.postingNumber = postingNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString(){
        return country == null ?
                postingNumber + " " + city + ", " + street + " " + apartmentNumber :
                country + ", "  + postingNumber + " " + city + ", " + street + " " + apartmentNumber;


    }
}