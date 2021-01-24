package data;

import consumer.Consumer;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

/** The type Data. */
public class Data {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;
    private ArrayList<Producer> producersForEnd;

    public ArrayList<Producer> getProducersForEnd() {
        return producersForEnd;
    }

    public void setProducersForEnd(ArrayList<Producer> producersForEnd) {
        this.producersForEnd = producersForEnd;
    }

    public ArrayList<Producer> getProducers() {
        return producers;
    }

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
