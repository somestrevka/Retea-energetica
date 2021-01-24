package provider;

import entities.EnergyType;

import java.util.ArrayList;

public class Producer extends MotherProducer implements Comparable<Producer>{
    private int numberOfDistributors;
    private ArrayList<Distributor> currentDistributors;

    public ArrayList<Distributor> getCurrentDistributors() {
        return currentDistributors;
    }

    public void setCurrentDistributors(ArrayList<Distributor> currentDistributors) {
        this.currentDistributors = currentDistributors;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + this.getId() +
                ", priceKW=" + this.getPriceKW() +
                ", energyPerDistributor=" + this.getEnergyPerDistributor() +
                ", numberOfDistributors=" + numberOfDistributors +
                '}';
    }

    public void notifyChange() { // producatorul isi va scoate din lista toti distribuitorii
        for (Distributor distributor: this.currentDistributors
             ) {
            distributor.removeProducer(this.getId());
        }
    }

    public int getNumberOfDistributors() {
        return numberOfDistributors;
    }

    public void setNumberOfDistributors(int numberOfDistributors) {
        this.numberOfDistributors = numberOfDistributors;
    }


    public Producer(int id, EnergyType energyType, int maxDistributors, float priceKW, int energyPerDistributor) {
        this.setId(id);
        this.setEnergyType(energyType);
        this.setMaxDistributors(maxDistributors);
        this.setPriceKW(priceKW);
        this.setEnergyPerDistributor(energyPerDistributor);
    }

    public Producer(final int id, final int energyPerDistributor) {
        this.setId(id);
        this.setEnergyPerDistributor(energyPerDistributor);
    }

    public boolean hasSpace() {
        if (this.getMaxDistributors() == this.getNumberOfDistributors()) {
            return false;
        }
        return true;
    }

    public Producer() {

    }

    @Override
    public int compareTo(Producer producer1) {
        float decider = producer1.getPriceKW() - this.getPriceKW();
        if (decider > 0)
            return 1;
        return 0;
    }

    public void addsDistributor(Distributor distributor) {
        if (this.currentDistributors == null) {
            this.currentDistributors = new ArrayList<>();
        }
        this.currentDistributors.add(distributor);
        this.numberOfDistributors += 1;
    }

}
