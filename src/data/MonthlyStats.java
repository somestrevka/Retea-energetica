package data;

import java.util.ArrayList;

/**
 * The type Monthly stats.
 */
public class MonthlyStats {
    /**
     * The Month.
     */
    private int month;
    /**
     * The Distributors ids.
     */
    private ArrayList<Integer> distributorsIds;

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Gets distributors ids.
     *
     * @return the distributors ids
     */
    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    /**
     * Sets distributors ids.
     *
     * @param distributorsIds the distributors ids
     */
    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
