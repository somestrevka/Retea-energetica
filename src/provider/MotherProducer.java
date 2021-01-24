package provider;

import data.MonthlyStats;
import entities.EnergyType;

import java.util.ArrayList;

/**
 * The type Mother producer.
 */
public class MotherProducer {
    private int id;
    private int maxDistributors;
    private float priceKW;
    private EnergyType energyType;
    private int energyPerDistributor;
    private ArrayList<MonthlyStats> monthlyStats;

    /**
     * Gets monthly stats.
     *
     * @return the monthly stats
     */
    public ArrayList<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * Sets monthly stats.
     *
     * @param monthlyStats the monthly stats
     */
    public void setMonthlyStats(ArrayList<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
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
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets max distributors.
     *
     * @return the max distributors
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * Sets max distributors.
     *
     * @param maxDistributors the max distributors
     */
    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
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
     * Gets energy type.
     *
     * @return the energy type
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * Sets energy type.
     *
     * @param energyType the energy type
     */
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    /**
     * Gets energy per distributor.
     *
     * @return the energy per distributor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * Sets energy per distributor.
     *
     * @param energyPerDistributor the energy per distributor
     */
    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
