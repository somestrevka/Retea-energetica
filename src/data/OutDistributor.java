package data;

import provider.Contract;
import strategies.EnergyChoiceStrategyType;

import java.util.LinkedList;

/** The type Out distributor. */
public class OutDistributor {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private LinkedList<Contract> contracts;

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
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

  /**
   * Gets contracts.
   *
   * @return the contracts
   */
  public LinkedList<Contract> getContracts() {
        return contracts;
    }

  /**
   * Sets contracts.
   *
   * @param contracts the contracts
   */
  public void setContracts(final LinkedList<Contract> contracts) {
        this.contracts = contracts;
    }
}
