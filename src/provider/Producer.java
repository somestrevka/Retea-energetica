package provider;

import entities.EnergyType;

import java.util.ArrayList;

/**
 * The type Producer.
 */
public class Producer extends MotherProducer implements Comparable<Producer> {
    private int numberOfDistributors;
    private ArrayList<Distributor> observers;

    /**
     * Gets current distributors.
     *
     * @return the current distributors
     */
    public ArrayList<Distributor> getObservers() {
        return observers;
    }

    /**
     * Add observer.
     *
     * @param observer the observer
     */
    public void addObserver(Distributor observer) {
        this.observers.add(observer);
    }

     void removeObserver(int idObserver) {
        this.observers.remove(idObserver);
        this.numberOfDistributors--;
    }

    /**
     * Sets current distributors.
     *
     * @param observers the current distributors
     */
    public void setObservers(ArrayList<Distributor> observers) {
        this.observers = observers;
    }

    /**
     * used for debugging
     * @return
     */

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + this.getId()
                + ", priceKW=" + this.getPriceKW()
                + ", energyPerDistributor=" + this.getEnergyPerDistributor()
                + ", numberOfDistributors=" + numberOfDistributors
                + '}';
    }

    /**
     * Notify change array list.
     *
     * @return the array list
     */
    public void notifyChange() { // producatorul isi va scoate
        // din lista toti distribuitorii
        for (Distributor distributor: this.observers
             ) {
            distributor.notifyChange();
        }
    }

    /**
     * Gets number of distributors.
     *
     * @return the number of distributors
     */
    public int getNumberOfDistributors() {
        return numberOfDistributors;
    }

    /**
     * Sets number of distributors.
     *
     * @param numberOfDistributors the number of distributors
     */
    public void setNumberOfDistributors(int numberOfDistributors) {
        this.numberOfDistributors = numberOfDistributors;
    }


    /**
     * Instantiates a new Producer.
     *
     * @param id                   the id
     * @param energyType           the energy type
     * @param maxDistributors      the max distributors
     * @param priceKW              the price kw
     * @param energyPerDistributor the energy per distributor
     */
    public Producer(int id, EnergyType energyType, int maxDistributors,
                    float priceKW, int energyPerDistributor) {
        this.setId(id);
        this.setEnergyType(energyType);
        this.setMaxDistributors(maxDistributors);
        this.setPriceKW(priceKW);
        this.setEnergyPerDistributor(energyPerDistributor);
    }

    /**
     * Instantiates a new Producer.
     *
     * @param id                   the id
     * @param energyPerDistributor the energy per distributor
     */
    public Producer(final int id, final int energyPerDistributor) {
        this.setId(id);
        this.setEnergyPerDistributor(energyPerDistributor);
    }

    /**
     * Has space boolean.
     *
     * @return the boolean
     */
    public boolean hasSpace() {
        if (this.getMaxDistributors() == this.getNumberOfDistributors()) {
            return false;
        }
        return true;
    }

    /**
     * Instantiates a new Producer.
     */
    public Producer() {

    }

    @Override
    public int compareTo(Producer producer1) {
        float decider = producer1.getPriceKW() - this.getPriceKW();
        if (decider > 0)
            return 1;
        return 0;
    }

    /**
     * Adds distributor.
     *
     * @param distributor the distributor
     */
    public void addsObserver(Distributor distributor) {
        if (this.observers == null) {
            this.observers = new ArrayList<>();
        }
        this.observers.add(distributor);
        this.numberOfDistributors += 1;
    }

}
