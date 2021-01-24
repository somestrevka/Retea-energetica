package utils;

import consumer.Consumer;
import consumer.ConsumerOut;
import data.Data;
import data.InputData;
import data.OutDistributor;
import data.OutputData;
import entities.EnergyType;
import factory.EntityType;
import factory.Factory;
import provider.Contract;
import provider.Distributor;
import provider.MotherProducer;
import provider.Producer;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.LinkedList;

/** The type Utils. */
public class Utils {
  /**
   * functie utilizata pentru a transfera datele de intrare in formatul de iesire @param data the
   * data
   *
   * @param outputData the output data
   */
  public void transferFinal(final Data data, final OutputData outputData) {
    transferConsumers(data.getConsumers(), outputData.getConsumers());
    transferDistributors(data.getDistributors(), outputData.getDistributors());
    moveMonthlyStats(data);
    transferProducersFinal(data.getProducersForEnd(), outputData.getEnergyProducers());
  }

  public void moveMonthlyStats(final Data data) {
      for (Producer producer: data.getProducersForEnd()
           ) {
          findById(producer, data.getProducers());
      }
  }

  public void findById(final Producer producer, final ArrayList<Producer> producers){
      for (Producer producerAux: producers
           ) {
          if (producerAux.getId() == producer.getId()) {
              producer.setMonthlyStats(producerAux.getMonthlyStats());
              producer.setEnergyPerDistributor(producerAux.getEnergyPerDistributor());
          }
      }
  }

  public void transferProducersFinal(ArrayList<Producer> producers,
                                     ArrayList<MotherProducer> outProducers) {
      for (Producer producer : producers) {
          MotherProducer aux = new MotherProducer();
          aux.setId(producer.getId());
          aux.setMaxDistributors(producer.getMaxDistributors());
          aux.setPriceKW(producer.getPriceKW());
          aux.setEnergyType(producer.getEnergyType());
          aux.setEnergyPerDistributor(producer.getEnergyPerDistributor());
          aux.setMonthlyStats(producer.getMonthlyStats());
          outProducers.add(aux);
      }
  }

  /**
   * Transfer consumers.
   *
   * @param list1 the list 1
   * @param list2 the list 2
   */
  public void transferConsumers(final ArrayList<Consumer> list1,
                                final ArrayList<ConsumerOut> list2) {

    for (int i = 0; i < list1.size(); i++) {
      ConsumerOut aux = new ConsumerOut();
      aux.setIsBankrupt(list1.get(i).isBankrupt());
      aux.setBudget(list1.get(i).getBudget());
      aux.setId(list1.get(i).getId());
      list2.add(aux);
    }
  }

  /**
   * Transfer from input.
   *
   * @param data the data
   * @param inputData the input data
   */
  public void transferFromInput(final Data data, final InputData inputData) {
      Factory factory = Factory.getInstance();
      ArrayList<Producer> producers = new ArrayList<>();
      ArrayList<Distributor> distributors = new ArrayList<>();
      for (Distributor distributor : inputData.getInitialData().getDistributors()) {
          int id = distributor.getId();
          int initialBudget = distributor.getInitialBudget();
          int energyNeededKW = distributor.getEnergyNeededKW();
          EnergyChoiceStrategyType strategyType = distributor.getProducerStrategy();
          Distributor aux = (Distributor) factory.factory(EntityType.distributor, id, initialBudget);
          aux.setInitialInfrastructureCost(distributor.getInitialInfrastructureCost());
          aux.setInitialProductionCost(distributor.getInitialProductionCost());
          aux.setContractLength(distributor.getContractLength());
          aux.setProducerStrategy(strategyType);
          aux.setEnergyNeededKW(energyNeededKW);
          distributors.add(aux);
      }
      data.setDistributors(distributors);

      // transferam producatorii

      transferProducers(inputData.getInitialData().getProducers(), producers);
      data.setProducers(producers);

      ArrayList<Consumer> consumers = new ArrayList<>();
      for (Consumer consumer : inputData.getInitialData().getConsumers()) {
          int id = consumer.getId();
          int initialBudget = consumer.getInitialBudget();
          Consumer aux = (Consumer) factory.factory(EntityType.consumer, id, initialBudget);
          aux.setMonthlyIncome(consumer.getMonthlyIncome());
          consumers.add(aux);
      }
      data.setConsumers(consumers);
  }

  public void transferProducers(final ArrayList<Producer> list1,
                                final ArrayList<Producer> list2) {
      for (Producer producer: list1
      ) {
          int id = producer.getId();
          EnergyType energyType = producer.getEnergyType();
          int maxDistributors = producer.getMaxDistributors();
          float priceKW = producer.getPriceKW();
          int energyPerDistributor = producer.getEnergyPerDistributor();

          Producer producerAux = new Producer();

          producerAux.setId(id);
          producerAux.setEnergyType(energyType);
          producerAux.setMaxDistributors(maxDistributors);
          producerAux.setPriceKW(priceKW);
          producerAux.setEnergyPerDistributor(energyPerDistributor);

          list2.add(producerAux);
      }
  }

  /**
   * Transfer distributors.
   *
   * @param list1 the list 1
   * @param list2 the list 2
   */
  public void transferDistributors(final ArrayList<Distributor> list1,
                                   final ArrayList<OutDistributor> list2) {
    Distributor auxGet;
    for (int i = 0; i < list1.size(); i++) {
      OutDistributor aux = new OutDistributor();
      LinkedList<Contract> contracts = new LinkedList<>();
      auxGet = list1.get(i);
      if (auxGet.getNumberOfCostumers() != 0) {
        contracts = auxGet.getContracts();
      }
      aux.setContracts(contracts);
      aux.setIsBankrupt(auxGet.isBankrupt());
      aux.setId(auxGet.getId());
      aux.setBudget(auxGet.getBudget());
      aux.setEnergyNeededKW(auxGet.getEnergyNeededKW());
      aux.setContractCost(auxGet.getContractPrice());
      aux.setProducerStrategy(auxGet.getProducerStrategy());
      list2.add(aux);
    }
  }
}
