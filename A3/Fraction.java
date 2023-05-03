public class Fraction {
    private static int ggt(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a == 0)
            return b;
        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }

        return a;
    }


    // Konstruktor (Division durch 0 verhindern, bei 0 den Nenner auf 1 setzen.)
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        if (denominator == 0) {
            this.denominator = 1;
        } else {
            this.denominator = denominator;
        }


    }

    private int numerator;
    private int denominator;


    // Gibt es Wert des Zählers zurück
    public int getNumerator() {
        return numerator;
    }

    // Gibt es Wert des Nenners zurück
    public int getDenominator() {
        return denominator;
    }

    // Setzt den Wert des Zählers
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    // Setzt den Wert des Nenners
    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    // Gibt den Wert des Bruchs als double zurück
    public double value() {
        return (double) numerator / (double) denominator;
    }

    // Erweitert den Bruch um eine ganze Zahl.
    public void expand(int x) {
        if (x == 0) ;
        else {
            numerator = numerator * x;
            denominator = denominator * x;
        }
    }

    // Kürzt den Bruch so weit es geht. Es bietet sich an für den ggt eine Hilfsmethode zu definieren. (https://de.wikipedia.org/wiki/Größter_gemeinsamer_Teiler).
    // Bei negativen Brüchen steht das Vorzeichen nach dem Kürzen im Zähler.
    public void shorten() {
        int vorzeichen = 1;
        if (this.value() < 0){
            vorzeichen = -1;
        }

        int ggT = ggt(numerator, denominator);
        numerator = vorzeichen * Math.abs(numerator) / ggT;
        denominator = Math.abs(denominator) / ggT;
    }

    // Gibt eine sinnvolle textuelle Beschreibung des Bruchs zurück.
    public String toString() {
        return "" + numerator + '/' + denominator;
    }

    // Addiert einen anderen Bruch hinzu, und kürzt das Ergebnis.
    public void addMut(final Fraction x) {
        this.numerator = this.numerator * x.getDenominator() + x.getNumerator() * this.denominator;
        this.denominator = this.denominator * x.getDenominator();
        this.shorten();
    }

    // Multiplikation mit einem anderen Bruch, hier darf nicht gekürzt werden.
    public void multMut(final Fraction x) {
        numerator *= x.getNumerator();
        denominator *= x.getDenominator();
    }

    // gibt -1 zurück, wenn x kleiner ist, 0, wenn gleich und 1, wenn x größer ist.
    public int compareTo(final Fraction x) {
        if (x.value() < this.value())
            return -1;
        else if (x.value() > this.value())
            return 1;
        else return 0;
    }

}