package consumer;

/** The type Entity. */
public abstract class Entity {
    private int id;
    private boolean isBankrupt = false;
    private int initialBudget;

  /**
   * Gets initial budget.
   *
   * @return the initial budget
   */
  public int getInitialBudget() {
        return initialBudget;
    }

  /**
   * Sets initial budget.
   *
   * @param initialBudget the initial budget
   */
  public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

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
   * Is bankrupt boolean.
   *
   * @return the boolean
   */
  public boolean isBankrupt() {
        return isBankrupt;
    }

  /**
   * Sets bankrupt.
   *
   * @param bankrupt the bankrupt
   */
  public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
