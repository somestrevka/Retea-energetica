package data;

import consumer.Consumer;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

/**
 * The type Monthly updates.
 */
public class MonthlyUpdates { // lista datelor noi introduse pe parcursul celor n runde
    private ArrayList<Consumer> newConsumers;
    private ArrayList<Distributor> distributorChanges;
    private ArrayList<Producer> producerChanges;


    /**
     * Gets new consumers.
     *
     * @return the new consumers
     */
    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     * Sets new consumers.
     *
     * @param newConsumers the new consumers
     */
    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    /**
     * Gets distributor changes.
     *
     * @return the distributor changes
     */
    public ArrayList<Distributor> getDistributorChanges() {
        return distributorChanges;
    }

    /**
     * Sets distributor changes.
     *
     * @param distributorChanges the distributor changes
     */
    public void setDistributorChanges(ArrayList<Distributor> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    /**
     * Gets producer changes.
     *
     * @return the producer changes
     */
    public ArrayList<Producer> getProducerChanges() {
        return producerChanges;
    }

    /**
     * Sets producer changes.
     *
     * @param producerChanges the producer changes
     */
    public void setProducerChanges(ArrayList<Producer> producerChanges) {
        this.producerChanges = producerChanges;
    }

    /**
     * Instantiates a new Monthly updates.
     *
     * @param newConsumers       the new consumers
     * @param distributorChanges the distributor changes
     * @param producerChanges    the producer changes
     */
    public MonthlyUpdates(final ArrayList<Consumer> newConsumers,
                        final ArrayList<Distributor> distributorChanges,
                        final ArrayList<Producer> producerChanges) {
        this.setDistributorChanges(distributorChanges);
        this.setNewConsumers(newConsumers);
        this.setProducerChanges(producerChanges);
    }

    /**
     * Instantiates a new Monthly updates.
     */
    public MonthlyUpdates() { }
}
