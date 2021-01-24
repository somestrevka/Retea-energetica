package provider;

/** The type Contract. */
public class Contract {
    private int consumerId;
    private int price;
    private int remainedContractMonths;

    /**
     * used for debugging
     * @return
     */
    public String toString() {
        return "Contract{"
                + "consumerId=" + consumerId
                + ", price=" + price
                + ", remainedContractMonths="
                + remainedContractMonths
                + '}';
    }

  /**
   * Gets consumer id.
   *
   * @return the consumer id
   */
  public int getConsumerId() {
        return consumerId;
    }

  /**
   * Sets consumer id.
   *
   * @param consumerId the consumer id
   */
  public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

  /**
   * Gets price.
   *
   * @return the price
   */
  public int getPrice() {
        return price;
    }

  /**
   * Sets price.
   *
   * @param price the price
   */
  public void setPrice(final int price) {
        this.price = price;
    }

  /**
   * Gets remained contract months.
   *
   * @return the remained contract months
   */
  public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

  /**
   * Sets remained contract months.
   *
   * @param remainedContractMonths the remained contract months
   */
  public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
