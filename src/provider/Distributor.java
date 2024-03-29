package provider;


import consumer.Entity;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The type Distributor.
 */
public class Distributor extends Entity {
    private int contractLength;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private LinkedList<Contract> contracts;
    private int contractPrice;
    private int infrastructureCost;
    private int productionCost;
    private int numberOfCostumers;
    private int lastMonthCostumers;
    private int budget;
    private int energyNeededKW;
    private int energyStillNeeded;
    private int energyReceived;
    private float priceKW;
    private EnergyChoiceStrategyType producerStrategy;
    private ArrayList<Producer> producers;

    /**
     * Gets energy still needed.
     *
     * @return the energy still needed
     */
    public int getEnergyStillNeeded() {
        return energyStillNeeded;
    }

    public void notifyChange() {
        this.producerChanged = true;
    }

    /**
     * Sets energy still needed.
     *
     * @param energyStillNeeded the energy still needed
     */
    public void setEnergyStillNeeded(int energyStillNeeded) {
        this.energyStillNeeded = energyStillNeeded;
    }

    /**
     * Gets producers.
     *
     * @return the producers
     */
    public ArrayList<Producer> getProducers() {
        return producers;
    }

    /**
     * Sets producers.
     *
     * @param producers the producers
     */
    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    /**
     * Is producer changed boolean.
     *
     * @return the boolean
     */
    public boolean isProducerChanged() {
        return producerChanged;
    }

    /**
     * Sets producer changed.
     *
     * @param producerChanged the producer changed
     */
    public void setProducerChanged(boolean producerChanged) {
        this.producerChanged = producerChanged;
    }

    private boolean producerChanged;

    /**
     * Add producers.
     *
     * @param producer the producer
     */
    public void addProducers(Producer producer) {
        if (this.producers == null) {
            producers = new ArrayList<>();
            this.energyStillNeeded = this.energyNeededKW;
            this.energyReceived = 0;
        }
        this.producers.add(producer);
        this.energyReceived += producer.getEnergyPerDistributor();
        this.energyStillNeeded = this.energyStillNeeded
                - producer.getEnergyPerDistributor();
    }

    /**
     * Still need int.
     *
     * @return the int
     */
    public int stillNeed() {
        return this.energyStillNeeded;
    }

    /**
     * Gets energy received.
     *
     * @return the energy received
     */
    public int getEnergyReceived() {
        return energyReceived;
    }

    /**
     * Sets energy received.
     *
     * @param energyReceived the energy received
     */
    public void setEnergyReceived(int energyReceived) {
        this.energyReceived = energyReceived;
    }

    /**
     * Gets price kw.
     *
     * @return the price kw
     */
    public float getPriceKW() {
        return priceKW;
    }

    /**
     * Sets price kw.
     *
     * @param priceKW the price kw
     */
    public void setPriceKW(float priceKW) {
        this.priceKW = priceKW;
    }

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
     * Instantiates a new Distributor.
     *
     * @param id            the id
     * @param initialBudget the initial budget
     */
    public Distributor(final int id, final int initialBudget) {
        this.setInitialBudget(initialBudget);
        this.setId(id);
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
     * Gets last month costumers.
     *
     * @return the last month costumers
     */
    public int getLastMonthCostumers() {
        return lastMonthCostumers;
    }

    /**
     * Sets last month costumers.
     *
     * @param lastMonthCostumers the last month costumers
     */
    public void setLastMonthCostumers(final int lastMonthCostumers) {
        this.lastMonthCostumers = lastMonthCostumers;
    }

    /**
     * used for debugging
     * @return
     */
    public String toString() {
        return "Distributor{"
                + "id=" + this.getId()
                + ", budget=" + this.getBudget()
                + ", isBankrupt" + this.isBankrupt()
                + ", contractLength=" + contractLength
                + ", contracts=" + contracts
                + ", contractPrice=" + contractPrice
                + ", infrastructureCost=" + infrastructureCost
                + ", productionCost=" + productionCost
                + ", numberOfCostumers=" + numberOfCostumers
                + ", producers=" + this.producers
                + ", energyNeededKW=" + this.energyNeededKW
                + '}';
    }

    /**
     * Gets production cost.
     *
     * @return the production cost
     */
    public int getProductionCost() {
        this.productionCost = 0;
        double result = 0;
      for (Producer producer: this.producers
           ) {
          result += (producer.getEnergyPerDistributor() * producer.getPriceKW()) / 10;
      }
        this.productionCost = (int) Math.floor(result);
        return productionCost;
    }

    /**
     * Add contract.
     *
     * @param contract the contract
     */
    public void addContract(final Contract contract) {
        this.getContracts().add(contract);
        this.setNumberOfCostumers(this.getNumberOfCostumers() + 1);
    }

    /**
     * Sets production cost.
     *
     * @param productionCost the production cost
     */
    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
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
     * Gets infrastructure cost.
     *
     * @return the infrastructure cost
     */
    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * Sets infrastructure cost.
     *
     * @param infrastructureCost the infrastructure cost
     */
    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     * Pay infrastructure.
     */
    public void payInfrastructure() {
        int sum = this.getInfrastructureCost();
        sum = this.getBudget() - sum;
        this.setBudget(sum);
        if (this.getBudget() < 0) {
            this.declareBankrupt();
        }
    }

    /**
     * Declare bankrupt.
     */
    public void declareBankrupt() {
        this.setBankrupt(true);
    }

    /**
     * Pay production.
     */
    public void payProduction() {
        this.setBudget(this.getBudget() - this.getProductionCost());
    }

    /**
     * Notify contract.
     *
     * @param id the id
     */
    public void notifyContract(final int id) {
        for (Contract contract : this.getContracts()) {
            if (contract.getConsumerId() == id) {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            }
        }
    }

    /**
     * Gets number of costumers.
     *
     * @return the number of costumers
     */
    public int getNumberOfCostumers() {
        return numberOfCostumers;
    }

    /**
     * Sets number of costumers.
     *
     * @param numberOfCostumers the number of costumers
     */
    public void setNumberOfCostumers(final int numberOfCostumers) {
        this.numberOfCostumers = numberOfCostumers;
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
     * Remove contract.
     *
     * @param consumerId the consumer id
     */
    public void removeContract(final int consumerId) {
        int index = -1;
        for (int i = 0; i < this.getContracts().size(); i++) {
            if (this.getContracts().get(i).getConsumerId() == consumerId) {
                index = i;
            }
        }
        if (this.getContracts().size() != 0 && index > -1) {
            this.getContracts().remove(index);
            this.setNumberOfCostumers(this.getNumberOfCostumers() - 1);
        }
    }

    /**
     * Sets contract price.
     */
    public void setContractPrice() {
        double infrastructure;
        int production, profit;
        production = getProductionCost();
        profit = (int) (Math.floor(production * 0.2));
        if (this.getLastMonthCostumers() == 0) {
            infrastructure = this.infrastructureCost;
        } else {
            infrastructure = Math.floor((double) (this.infrastructureCost
                    / this.lastMonthCostumers));
        }
        this.contractPrice = (int) Math.abs(infrastructure) + Math.abs(production)
                + Math.abs(profit);
    }

    public void removeProducers() {
        for (Producer producer: this.getProducers()
             ) {
            // producatorul isi sterge distribuitorul din lista
            producer.removeObserver(this.getId());
        }
        // reinitializam lista de producatori
        this.producers = new ArrayList<>();
        // setam energia primita la 0
        this.energyReceived = 0;
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

    /**
     * Gets contract length.
     *
     * @return the contract length
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     * Sets contract length.
     *
     * @param contractLength the contract length
     */
    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * Gets initial infrastructure cost.
     *
     * @return the initial infrastructure cost
     */
    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * Sets initial infrastructure cost.
     *
     * @param initialInfrastructureCost the initial infrastructure cost
     */
    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * Gets initial production cost.
     *
     * @return the initial production cost
     */
    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    /**
     * Sets initial production cost.
     *
     * @param initialProductionCost the initial production cost
     */
    public void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    /**
     * Instantiates a new Distributor.
     *
     * @param id                        the id
     * @param contractLength            the contract length
     * @param initialBudget             the initial budget
     * @param initialInfrastructureCost the initial infrastructure cost
     * @param initialProductionCost     the initial production cost
     * @param energyNeededKW            the energy needed kw
     * @param producerStrategy          the producer strategy
     */
    public Distributor(
      final int id,
      final int contractLength,
      final int initialBudget,
      final int initialInfrastructureCost,
      final int initialProductionCost,
      final int energyNeededKW,
      final EnergyChoiceStrategyType producerStrategy) {
        this.setId(id);
        this.setContractLength(contractLength);
        this.setInitialBudget(initialBudget);
        this.setInitialInfrastructureCost(initialInfrastructureCost);
        this.setInitialProductionCost(initialProductionCost);
        this.setEnergyNeededKW(energyNeededKW);
        this.setProducerStrategy(producerStrategy);
    }

    /**
     * Instantiates a new Distributor.
     *
     * @param id                 the id
     * @param infrastructureCost the infrastructure cost
     * @param productionCost     the production cost
     */
    public Distributor(final int id, final int infrastructureCost, final int productionCost) {
        this.setId(id);
        this.setInitialInfrastructureCost(infrastructureCost);
        this.setInitialProductionCost(productionCost);
    }

    /**
     * Instantiates a new Distributor.
     */
    public Distributor() { }

    /**
     * Finds producer.
     *
     * @param producer the producer
     */
    public void findsProducer(Producer producer) {
      this.setEnergyReceived(producer.getEnergyPerDistributor());
      this.setPriceKW(producer.getPriceKW());
  }
}
