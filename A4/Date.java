// WARNUNG NICHT DAS PACKAGE KOPIEREN!!!!!!
package A4;
// HOFFENTLICH HAST DU DAS GELESEN!
public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day){
        if (!validate(year, month, day)){
            System.out.println("ERROR: Illegal date.");
            //System.exit(0);
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static int getDaysInMonth(int year, int month){
        initArray(year);
        return monthLength[month-1];
    }

    public int dayOfYear(){
        int diff = 0;
        for (int i = 1; i < month; i++){
            diff += getDaysInMonth(year, i);
        }
        return diff + day;
    }
    public int sameYearDiff(Date other){
        return (other.dayOfYear() + other.daysSinceYearZero()) - (dayOfYear() + daysSinceYearZero());
    }

    private int daysSinceYearZero(){
        int days = 0;
        for (int i = 0; i < year; i++){
            if (isGap(i)){
                days += 366;
            } else days += 365;
        }

        return days;
    }


    public static boolean validate(int year, int month, int day){

        if (1000 > year || year > 9999){
            return false;
        }
        if (1 > month || month > 12){
            return false;
        }
        int max = getDaysInMonth(year, month);

        if (1 > day || day > max){
            return false;
        }
        return true;
    }

    public String toString(){
        return monthNames[month-1] + " " + day + ", " + year;
    }
    private static void initArray(int year){
        if (isGap(year))
            feb = 29;
        // Nike, falls du das hier liest.
        // Dieses else hat mir meine Nerven gekostet.
        else
            feb = 28;
        monthLength = new int[]{31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }
    private static boolean isGap(int year){
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
    private static int feb = 28;
    private static final String[] monthNames = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static int[] monthLength;
}
// DENK DARAN DAS PACKAGE NICHT ZU KOPIEREN!