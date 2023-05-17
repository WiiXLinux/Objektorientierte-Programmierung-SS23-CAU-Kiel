package A5;

/**
 * class for managing trains. I like trains.
 */
public class Traincar {

    /***
     *
     * @param seats number of seats in this traincar
     */
    Traincar(int seats) {
        this.seats = seats;
        nextCar = null;
    }

    /**
     * number of seats in this train car
     */
    private int seats;
    /**
     * link to the next car
     */
    private Traincar nextCar;

    /**
     *
     * @param seats set the number of seats
     */
    public void setSeats(int seats){
        this.seats = seats;
    }

    /**
     *
     * @return the number of seats in this car
     */
    public int getSeats() {
        return seats;
    }

    /**
     *
     * @param next put the next Traincar behind the current
     */
    public void connectCar(Traincar next) {
        nextCar = next;
    }

    /**
     * remove the link to the next Traincar
     */
    public void disconnect() {
        nextCar = null;
    }

    /**
     *
     * @return the next Traincar if available, else null
     */
    public Traincar getNext() {
        return nextCar;
    }

    /**
     *
     * @return if a next Traincar is connected/exists after this car
     */
    public boolean hasNext() {
        return null != nextCar;
    }

    /**
     *
     * @return the capacity of the train starting with this car
     */
    // HIER GEHT'S LOS
    public int capacity(){
        int temp = 0;
        Traincar currentObj = this;
        while (currentObj != null){
            temp += currentObj.seats;
            currentObj = currentObj.getNext();
        }
        return temp;
    }

    /**
     * add a train car directly behind this one
     * @param n number of seats
     */

    public void insertNext(int n){
        Traincar oldNextCar = nextCar;
        nextCar = new Traincar(n);
        nextCar.nextCar = oldNextCar;
    }
    // HIER SIND WIR DURCH

    public String toString(){
        String temp = "";
        Traincar currentObj = this;
        while (currentObj != null){
            temp +=  "(" + currentObj.seats + ")\n";
            currentObj = currentObj.getNext();
        }
        return temp;
    }

    public static void main(String[]args){
        System.out.println("Starting tests");
        Traincar SBahn = new Traincar(20);
        SBahn.connectCar(new Traincar(30));
        SBahn.getNext().connectCar(new Traincar(32));

        System.out.println(SBahn);
        System.out.println(SBahn.capacity());
        SBahn.getNext().insertNext(25);
        System.out.println(SBahn);
        System.out.println(SBahn.capacity());
    }
}