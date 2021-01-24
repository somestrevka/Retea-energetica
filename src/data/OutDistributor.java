package data;

import provider.Contract;
import strategies.EnergyChoiceStrategyType;

import java.util.LinkedList;

/**
 * The type Out distributor.
 */
public class OutDistributor {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private LinkedList<Contract> contracts;

    /**
     * Gets producer strategy.
     *
     * @return the producer strategy
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * Sets producer strategy.
     *
     * @param producerStrategy the producer strategy
     */
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     * Gets energy needed kw.
     *
     * @return the energy needed kw
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * Sets energy needed kw.
     *
     * @param energyNeededKW the energy needed kw
     */
    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    /**
     * Gets contract cost.
     *
     * @return the contract cost
     */
    public int getContractCost() {
        return contractCost;
    }

    /**
     * Sets contract cost.
     *
     * @param contractCost the contract cost
     */
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
