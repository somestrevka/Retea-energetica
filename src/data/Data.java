package data;

import consumer.Consumer;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

/**
 * The type Data.
 */
public class Data {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;
    private ArrayList<Producer> producersForEnd;

    /**
     * Gets producers for end.
     *
     * @return the producers for end
     */
    public ArrayList<Producer> getProducersForEnd() {
        return producersForEnd;
    }

    /**
     * Sets producers for end.
     *
     * @param producersForEnd the producers for end
     */
    public void setProducersForEnd(ArrayList<Producer> producersForEnd) {
        this.producersForEnd = producersForEnd;
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
     * Gets consumers.
     *
     * @return the consumers
     */
    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    /**
     * Sets consumers.
     *
     * @param consumers the consumers
     */
    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    /**
     * Gets distributors.
     *
     * @return the distributors
     */
    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    /**
     * Sets distributors.
     *
     * @param distributors the distributors
     */
    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

}
