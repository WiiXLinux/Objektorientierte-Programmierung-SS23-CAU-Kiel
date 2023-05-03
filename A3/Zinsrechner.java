public class Zinsrechner {
    private static final double milli = 1000000;

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getEndCapital() {
        return endCapital;
    }

    public void setEndCapital(double endCapital) {
        this.endCapital = endCapital;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int berechneJahre() {
        for (double capital = this.capital; capital < milli; capital += this.interest * capital)
            this.years += 1;
        return this.years;
    }

    private double capital;
    private double interest;
    private double endCapital;
    private int years;

    public Zinsrechner() {

    }
}