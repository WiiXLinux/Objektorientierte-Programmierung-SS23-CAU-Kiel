package A6; // TODO: Remove this

import java.util.Arrays;

/**
 * A set of operations for a list of values.
 */
public class StatisticsCollector {
    /**
     * The double values that are the content of the regarded list.
     */
    private double[] values;

    /**
     * Constructor initialising the object with an empty values array.
     */
    public StatisticsCollector(){
        values = new double[]{};
    }

    /**
     * Adds values to the set of values.
     * @param toAdd Values to be added.
     */
    public void addItems(double[] toAdd){
        int len = values.length + toAdd.length;
        double[] newValues = new double[len];

        System.arraycopy(values, 0, newValues, 0, values.length);                   // I don't know how
        System.arraycopy(toAdd, 0, newValues, values.length, len - values.length);   // but it just works.

        this.values = newValues;
    }

    /**
     * Get the count of Elements in the list.
     * @return count of values
     */
    public int getCount(){
        return values.length;
    }

    /**
     * Get the sum of Elements in the list.
     * @return sum of values
     */
    public double getSum(){
        double sum = 0;
        for (double value : values) sum += value;
        return sum;
    }
    /**
     * Get the min of Elements in the list.
     * POSITIVE_INFINITY in case of an empty list.
     * @return min of values
     */
    public double getMinimum(){
        double cMin = Double.POSITIVE_INFINITY;
        for (double value : values){
            cMin = Math.min(cMin, value);
        }
        return cMin;
    }
    /**
     * Get the max of Elements in the list.
     * NEGATIVE_INFINITY in case of an empty list.
     * @return max of values
     */
    public double getMaximum(){
        double cMax = Double.NEGATIVE_INFINITY;
        for (double value : values){
            cMax = Math.max(cMax, value);
        }
        return cMax;
    }

    /**
     * Get the average of Elements in the list.
     * 0 in case of an empty list.
     * @return average of values
     */
    public double getAverage(){
        return values.length == 0 ? 0 : getSum() / getCount();
    }

    /**
     * Get the standard derivation of Elements in the list.
     * 0 in case of an empty list.
     * @return standard derivation of values
     */
    public double getStandardDeviation(){
        double[] diffs = new double[values.length];
        final double avg = getAverage();
        double var = 0;
        for (int i = 0; i < values.length; i++){
            diffs[i] = Math.pow(avg - values[i], 2);
        }
        for (double value : diffs){
            var += value / values.length;
        }

        return Math.sqrt(var);

    }

    // TODO: Remove this
    // Useful for testing.
    public String toString(){
        return "Elements: " + Arrays.toString(values) + "\nWith count: " + getCount() + "\nWith sum: " + getSum()
                + "\nWith min: " + getMinimum() + "\nWith max: " + getMaximum() + "\nWith average: " + getAverage()
                + "\nWith standard derivation: " + getStandardDeviation();
    }

    // TODO: Remove this
    // Tester
    public static void main(String[]args){
        StatisticsCollector s = new StatisticsCollector();
        s.addItems(new double[]{});
        System.out.println(s);

    }
}
