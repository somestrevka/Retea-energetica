package data;

import consumer.Consumer;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

/** The type Monthly updates. */
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

    public ArrayList<Distributor> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(ArrayList<Distributor> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public ArrayList<Producer> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<Producer> producerChanges) {
        this.producerChanges = producerChanges;
    }

    /**
   * Instantiates a new Monthly updates.
   *
   * @param newConsumers the new consumers
   */
  public MonthlyUpdates(final ArrayList<Consumer> newConsumers,
                        final ArrayList<Distributor> distributorChanges,
                        final ArrayList<Producer> producerChanges) {
        this.setDistributorChanges(distributorChanges);
        this.setNewConsumers(newConsumers);
        this.setProducerChanges(producerChanges);
    }

  /** Instantiates a new Monthly updates. */
  public MonthlyUpdates() { }
}
