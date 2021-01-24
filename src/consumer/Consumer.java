package consumer;

import provider.Distributor;

import java.util.ArrayList;

/** The type Consumer. */
public class Consumer extends Entity {
    private int monthlyIncome;
    private Boolean hasContract = false;
    private int contractPrice;
    private int distributorId;
    private int numberOfMonths;
    private int behindPay = 0;
    private int budget;
  /**
   * aduce bugetul
   *
   * @return the budget
   */
  public int getBudget() {
        return budget;
    }
  /**
   * seteaza bugetul
   *
   * @param budget the budget
   */
  public void setBudget(final int budget) {
        this.budget = budget;
    }

  /**
   * Gets behind pay.
   *
   * @return the behind pay
   */
  public int getBehindPay() {
        return behindPay;
    }

  /**
   * Sets behind pay.
   *
   * @param behindPay the behind pay
   */
  public void setBehindPay(final int behindPay) {
        this.behindPay = behindPay;
    }

  /**
   * Pay.
   *
   * @param distributors the distributors
   */
  public void pay(final ArrayList<Distributor> distributors) {
        Distributor distributor = distributors.get(this.getDistributorId());
        double number = 1.2;
        int bill = (int) (Math.floor(number * this.getBehindPay()) + this.getContractPrice());
        distributor.payProduction(); // distribuitorul plateste costul de productie
        int sum = this.getBudget() - bill;
        if (sum >= 0) { // daca sum este pozitiv, atunci consumatorul isi permite
            // sa plateasca factura
            this.setBudget(sum);
            this.setNumberOfMonths(this.getNumberOfMonths() - 1);
            distributor.setBudget(distributor.getBudget() + bill); // distribuitorul incaseaza banii
            distributor.notifyContract(this.getId());
            if (this.getNumberOfMonths() == 0) {
                this.setHasContract(false);
            }
        } else {
            if (this.getBehindPay() == 0) {
                this.setBehindPay(this.getContractPrice());
                this.setNumberOfMonths(this.getNumberOfMonths() - 1);
                distributor.notifyContract(this.getId());
                if (this.getNumberOfMonths() == 0) {
                    this.setHasContract(false);
                }
            } else {
                declareBankrupt(distributor);
                distributor.setLastMonthCostumers(distributor.getLastMonthCostumers() - 1);
            }
        }

    }

  /**
   * Instantiates a new Consumer.
   *
   * @param id the id
   * @param initialBudget the initial budget
   */
  public Consumer(final int id, final int initialBudget) {
        this.setId(id);
        this.setInitialBudget(initialBudget);
    }

  /** Update. */
  public void update() {
        this.setBudget(this.getInitialBudget());
    }

  /**
   * Remove contract.
   *
   * @param distributors the distributors
   */
  public void removeContract(final ArrayList<Distributor> distributors) {
        Distributor distributor = distributors.get(this.getDistributorId());
        distributor.removeContract(this.getId());
    }

    /**
     *
     * metothod used for debugging
     */

    public String toString() {
        return "Consumer{"
                + "id=" + this.getId()
                + ", budget=" + this.getBudget()
                + ", monthlyIncome=" + monthlyIncome
                + ", hasContract=" + hasContract
                + ", contractPrice=" + contractPrice
                + ", distributorId=" + distributorId
                + ", numberOfMonths=" + numberOfMonths
                + ", behindPay=" + behindPay
                + '}';
    }

  /**
   * Declare bankrupt.
   *
   * @param distributor the distributor
   */
  public void declareBankrupt(final Distributor distributor) {
        this.setBankrupt(true);
        distributor.removeContract(this.getId());
    }

  /** Income. */
  public void income() {
        this.setBudget(this.getBudget() + this.getMonthlyIncome());

    }

  /**
   * Gets distributor id.
   *
   * @return the distributor id
   */
  public int getDistributorId() {
        return distributorId;
    }

  /**
   * Sets distributor id.
   *
   * @param distributorId the distributor id
   */
  public void setDistributorId(final int distributorId) {
        this.distributorId = distributorId;
    }

  /**
   * Gets number of months.
   *
   * @return the number of months
   */
  public int getNumberOfMonths() {
        return numberOfMonths;
    }

  /**
   * Sets number of months.
   *
   * @param numberOfMonths the number of months
   */
  public void setNumberOfMonths(final int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

  /**
   * Gets contract price.
   *
   * @return the contract price
   */
  public int getContractPrice() {
        return contractPrice;
    }

  /**
   * Sets contract price.
   *
   * @param contractPrice the contract price
   */
  public void setContractPrice(final int contractPrice) {
        this.contractPrice = contractPrice;
    }

  /**
   * Gets has contract.
   *
   * @return the has contract
   */
  public Boolean getHasContract() {
        return hasContract;
    }

  /**
   * Sets has contract.
   *
   * @param hasContract the has contract
   */
  public void setHasContract(final Boolean hasContract) {
        this.hasContract = hasContract;
    }

  /**
   * Gets monthly income.
   *
   * @return the monthly income
   */
  public int getMonthlyIncome() {
        return monthlyIncome;
    }

  /**
   * Sets monthly income.
   *
   * @param monthlyIncome the monthly income
   */
  public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

  /**
   * Instantiates a new Consumer.
   *
   * @param id the id
   * @param initialBudget the initial budget
   * @param monthlyIncome the monthly income
   */
  public Consumer(final int id, final int initialBudget, final int monthlyIncome) {
        this.setId(id);
        this.setInitialBudget(initialBudget);
        this.setMonthlyIncome(monthlyIncome);
    }
  /** Instantiates a new Consumer. */
  public Consumer() {

  }
}
