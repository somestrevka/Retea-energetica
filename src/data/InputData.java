package data;

import java.util.ArrayList;

/**
 * The type Input data.
 */
public class InputData { // datele primite la intrare
    private InitialData initialData;
    private int numberOfTurns;
    private ArrayList<MonthlyUpdates> monthlyUpdates;

    /**
     * Gets initial data.
     *
     * @return the initial data
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * Sets initial data.
     *
     * @param initialData the initial data
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * Gets number of turns.
     *
     * @return the number of turns
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * Sets number of turns.
     *
     * @param numberOfTurns the number of turns
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * Gets monthly updates.
     *
     * @return the monthly updates
     */
    public ArrayList<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     * Sets monthly updates.
     *
     * @param monthlyUpdates the monthly updates
     */
    public void setMonthlyUpdates(final ArrayList<MonthlyUpdates>
                                        monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     * Instantiates a new Input data.
     *
     * @param initialData    the initial data
     * @param numberOfTurns  the number of turns
     * @param monthlyUpdates the monthly updates
     */
    public InputData(
      final InitialData initialData,
      final int numberOfTurns,
      final ArrayList<MonthlyUpdates> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     * Instantiates a new Input data.
     */
    public InputData() { }
}
