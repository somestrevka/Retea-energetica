package data;

import consumer.Consumer;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

/** The type Initial data. */
public class InitialData {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<Producer> producers;

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

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    /**
   * Instantiates a new Initial data.
   *
   * @param consumers the consumers
   * @param distributors the distributors
   */
  public InitialData(final ArrayList<Consumer> consumers, final
  ArrayList<Distributor> distributors, final ArrayList<Producer> producers) {
        this.setConsumers(consumers);
        this.setDistributors(distributors);
        this.setProducers(producers);
    }

  /** Instantiates a new Initial data. */
  public InitialData() {
  }
}
