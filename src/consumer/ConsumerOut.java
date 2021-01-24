package consumer;

/** The type Consumer out. */
public class ConsumerOut {
    private int id;
    private boolean isBankrupt;
    private int budget;

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
        return id;
    }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(final int id) {
        this.id = id;
    }

  /**
   * Gets is bankrupt.
   *
   * @return the is bankrupt
   */
  public boolean getIsBankrupt() {
        return isBankrupt;
    }

  /**
   * Sets is bankrupt.
   *
   * @param bankrupt the bankrupt
   */
  public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

  /**
   * Gets budget.
   *
   * @return the budget
   */
  public int getBudget() {
        return budget;
    }

  /**
   * Sets budget.
   *
   * @param budget the budget
   */
  public void setBudget(final int budget) {
        this.budget = budget;
    }
}
