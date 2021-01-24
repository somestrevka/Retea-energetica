package data;

import consumer.ConsumerOut;
import provider.MotherProducer;

import java.util.ArrayList;

/**
 * The type Output data.
 */
public class OutputData {
    private ArrayList<ConsumerOut> consumers;
    private ArrayList<OutDistributor> distributors;
    private ArrayList<MotherProducer> energyProducers;

    /**
     * Gets energy producers.
     *
     * @return the energy producers
     */
    public ArrayList<MotherProducer> getEnergyProducers() {
        return energyProducers;
    }

    /**
     * Sets energy producers.
     *
     * @param energyProducers the energy producers
     */
    public void setEnergyProducers(ArrayList<MotherProducer> energyProducers) {
        this.energyProducers = energyProducers;
    }

    /**
     * Gets consumers.
     *
     * @return the consumers
     */
    public ArrayList<ConsumerOut> getConsumers() {
        return consumers;
    }

    /**
     * Sets consumers.
     *
     * @param consumers the consumers
     */
    public void setConsumers(final ArrayList<ConsumerOut> consumers) {
        this.consumers = consumers;
    }

    /**
     * Gets distributors.
     *
     * @return the distributors
     */
    public ArrayList<OutDistributor> getDistributors() {
        return distributors;
    }

    /**
     * Sets distributors.
     *
     * @param distributors the distributors
     */
    public void setDistributors(final ArrayList<OutDistributor> distributors) {
        this.distributors = distributors;
    }

    /**
     * Instantiates a new Output data.
     */
    public OutputData() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
        this.energyProducers = new ArrayList<>();
    }
}
