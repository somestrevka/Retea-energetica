package utils;

import consumer.Consumer;
import data.*;
import provider.Contract;
import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * The type Procesare.
 */
public class Procesare {

  /**
   * All turns.
   *
   * @param inputData  the input data
   * @param outputData the output data
   */
  public void allTurns(final InputData inputData, final OutputData outputData) {
    Data data = new Data(); // programul pentru toate turele
    Utils utilities = new Utils();

    utilities.transferFromInput(data, inputData);

    int check = 1;
    moveStart(data); // mutarea de start, din initialBudget in budget
    // toti distribuitorii isi vor seta flagul producerChanged in true
    changeFlag(data.getDistributors());
    for (int i = 0; i <= inputData.getNumberOfTurns(); i++) {
      if (i > 0) { // verificam daca avem update-uri
        checkForUpdates(data, inputData.getMonthlyUpdates(), i);
        addstats(data.getProducers(), i);
      }
      // distribuitorii isi cauta producatori
      lookingForProducer(data);
      //System.out.println(data.getProducers()+"\n");
      //System.out.println(data.getDistributors());
      cleanContracts(data.getConsumers(), data.getDistributors());

      receiveIncomeConsumers(data.getConsumers()); // platim consumatorii

      findDistributors(data); // gasim distribuitori pentru consumatorii
      payTimeCosumers(data.getConsumers(), data.getDistributors()); // fiecare
      // consumator care mai e in joc plateste
      check = payTimeDistributors(data); // distribuitorii platesc infrastructura
      saveLastMonthConsumers(data.getDistributors()); // salvam numarul de contracte
      // pe care distribuitorul le are
      // daca toti distrbuitorii au dat faliment, jocul se incheie
      if (check == 0) {
        break;
      }
    }

    utilities.transferFinal(data, outputData); // la final, dupa ce am parcurs toate turele
    // transferam datele pentru afisare
  }

  public void changeFlag(ArrayList<Distributor> distributors) {
    for (Distributor distributor: distributors
         ) {
      distributor.setProducerChanged(true);
    }
  }

  /**
   * Looking for producer.
   *
   * @param data the data
   */
  public void lookingForProducer(final Data data) {
    for (Distributor distributor: data.getDistributors()
    ) {  // fiecare distribuitor isi gaseste producatorul/ producatorii
      if (distributor.isProducerChanged()) {
        if (distributor.getProducers() != null) {
          distributor.setEnergyStillNeeded(distributor.getEnergyNeededKW());
          distributor.removeProducers();
        }
        findProducers(distributor, data.getProducers());
      }
    }
  }

  /**
   * Addstats.
   *
   * @param producers the producers
   * @param month     the month
   */
// aici aduagam pe fiecare luna la fiecare producator lista lui
  // cu id-urile distribuitorilor care iau energie de la el
  public void addstats(ArrayList<Producer> producers, int month) {
    for (Producer producer: producers
         ) {
      ArrayList<Integer> ids = new ArrayList<>();
      if (producer.getObservers() != null) {
        addIds(ids, producer.getObservers());
      }
      MonthlyStats monthlyStats = new MonthlyStats();
      monthlyStats.setMonth(month);
      monthlyStats.setDistributorsIds(ids);
      if (producer.getMonthlyStats() == null) {
        producer.setMonthlyStats(new ArrayList<>());
      }
      producer.getMonthlyStats().add(monthlyStats);
    }
  }

  /**
   * Add ids.
   *
   * @param ids          the ids
   * @param distributors the distributors
   */
  public void addIds(ArrayList<Integer> ids, ArrayList<Distributor> distributors) {
    for (Distributor distributor: distributors
         ) {
      ids.add(distributor.getId());
    }
  }

  /**
   * Find producers.
   *
   * @param distributor the distributor
   * @param producers   the producers
   */
  public void findProducers(Distributor distributor,
                            ArrayList<Producer> producers) {
      if (distributor.getProducerStrategy().label.equals("GREEN")) {
        findProducerByEnergy(distributor, producers);
      }
      if (distributor.getProducerStrategy().label.equals("PRICE")) {
        findProducerByPrice(distributor, producers);
      }
      if (distributor.getProducerStrategy().label.equals("QUANTITY")) {
        findProducerByQuantity(distributor, producers);
      }

  }

  /**
   * Find producer by price.
   *
   * @param distributor the distributor
   * @param producers   the producers
   */
// fiecare distribuitor isi gaseste producatorul
  public void findProducerByPrice(Distributor distributor,
                                  ArrayList<Producer> producers) {
    producers.sort(Comparator.comparing(Producer::getPriceKW)
            .thenComparing(Producer::getEnergyPerDistributor, Collections.reverseOrder()));
    for (Producer producer: producers
         ) {
      distributor.addProducers(producer);
      producer.addsObserver(distributor);
      if (distributor.stillNeed() <= 0) {
        break;
      }
    }
  }

  /**
   * Find producer by quantity.
   *
   * @param distributor the distributor
   * @param producers   the producers
   */
  public void findProducerByQuantity(Distributor distributor,
                                     ArrayList<Producer> producers) {
    producers.sort(Comparator.comparing(Producer::getEnergyPerDistributor,
            Collections.reverseOrder()));
    for (Producer producer: producers
         ) {
      distributor.addProducers(producer);
      producer.addsObserver(distributor);
      if (distributor.stillNeed() <= 0) {
        break;
      }
    }
  }

  /**
   * Find producer by energy.
   *
   * @param distributor the distributor
   * @param producers   the producers
   */
  public void findProducerByEnergy(Distributor distributor,
                                   ArrayList<Producer> producers) {

    producers.sort(Comparator.comparing(Producer::getEnergyType)
            .thenComparing(Producer::getPriceKW).thenComparing(Producer::getEnergyPerDistributor));
    for (Producer producer: producers
    ) {
      distributor.addProducers(producer);
      producer.addsObserver(distributor);
      System.out.println(distributor.stillNeed());
      if (distributor.stillNeed() <= 0) {
        break;
      }
    }
    System.out.println("end\n");
  }

  /**
   * Pay time distributors int.
   *
   * @param data the data
   * @return the int
   */
  public int payTimeDistributors(final Data data) {
    int count = 0;
    for (Distributor distributor : data.getDistributors()) {
        if (!distributor.isBankrupt()) {
          distributor.payInfrastructure();
          if (distributor.isBankrupt()) { // daca da faliment, trebuie sa
            // eliminam contractele pe care le are
            for (int i = distributor.getContracts().size() - 1; i >= 0; i--) {
              int idConsumer = distributor.getContracts().get(i).getConsumerId();
              data.getConsumers().get(idConsumer).removeContract(data.getDistributors());
            }
          }
        } else {
          count++;
        }
    }
    if (count == data.getDistributors().size()) {
      return 0;
    }
    return 1;
  }

  /**
   * Pay time cosumers.
   *
   * @param consumers    the consumers
   * @param distributors the distributors
   */
  public void payTimeCosumers(final ArrayList<Consumer> consumers,
                              final ArrayList<Distributor> distributors) {
    for (Consumer consumer : consumers) {
        if (!consumer.isBankrupt()) {
          consumer.pay(distributors);
        }
    }
  }

  /**
   * Receive income consumers.
   *
   * @param consumers the consumers
   */
  public void receiveIncomeConsumers(final ArrayList<Consumer> consumers) {
    for (Consumer consumer : consumers) {
      if (!consumer.isBankrupt()) {
        consumer.income();
      }
    }
  }

  /**
   * Clean contracts.
   *
   * @param consumers    the consumers
   * @param distributors the distributors
   */
  public void cleanContracts(final ArrayList<Consumer> consumers,
                             final ArrayList<Distributor> distributors) {
    for (Consumer consumer : consumers) {
      if (consumer.getNumberOfMonths() == 0) {
        consumer.removeContract(distributors);
      }
    }
  }

  /**
   * Check for updates.
   *
   * @param data    the data
   * @param updates the updates
   * @param month   the month
   */
  public void checkForUpdates(final Data data, final ArrayList<MonthlyUpdates> updates,
                              int month) {
    month--;
    int id, infrastructure, production;
    if (updates.get(month) != null) {
      MonthlyUpdates update = updates.get(month);
      if (update.getNewConsumers() != null) {
        for (int i = 0; i < update.getNewConsumers().size(); i++) {
          data.getConsumers().add(update.getNewConsumers().get(i));
          data.getConsumers().get(data.getConsumers().size() - 1).update();
        }
      }
      if (update.getDistributorChanges() != null) {
        for (int i = 0; i < update.getDistributorChanges().size(); i++) {
          id = update.getDistributorChanges().get(i).getId();
          infrastructure = update.getDistributorChanges().get(i).getInfrastructureCost();
          production = update.getDistributorChanges().get(i).getProductionCost();
          data.getDistributors().get(id).setInfrastructureCost(infrastructure);
          data.getDistributors().get(id).setProductionCost(production);
        }
      }
      if (update.getProducerChanges() != null) {
        ArrayList<Producer> producers = update.getProducerChanges();
        for (int i = 0; i < producers.size(); i++) {
          id = producers.get(i).getId();
          int energyPerDistributor = producers.get(i).getEnergyPerDistributor();
          // anuntam mai intai distribuitorii ca
          // producator va suferi modificari
          data.getProducers().get(id).notifyChange();
          // modificam producatorul
          data.getProducers().get(id).setEnergyPerDistributor(energyPerDistributor);

        }
      }
    }
  }

  /**
   * Move start.
   *
   * @param data the data
   */
  public void moveStart(final Data data) {
    for (Consumer consumer : data.getConsumers()) {
      consumer.setBudget(consumer.getInitialBudget());
    }
    for (Distributor distributor : data.getDistributors()) {
      LinkedList<Contract> contracts = new LinkedList<>();
      distributor.setBudget(distributor.getInitialBudget());
      distributor.setContracts(contracts);
      distributor.setProductionCost(distributor.getInitialProductionCost());
      distributor.setInfrastructureCost(distributor.getInitialInfrastructureCost());
    }
    copyProducersForLater(data);
  }

  /**
   * Copy producers for later.
   *
   * @param data the data
   */
  public void copyProducersForLater(final Data data) {
    ArrayList<Producer> auxProducers = new ArrayList<>();
    for (Producer producer: data.getProducers()
         ) {
      Producer auxProducer = new Producer();
      auxProducer.setId(producer.getId());
      auxProducer.setPriceKW(producer.getPriceKW());
      auxProducer.setEnergyPerDistributor(producer.getEnergyPerDistributor());
      auxProducer.setMaxDistributors(producer.getMaxDistributors());
      auxProducer.setEnergyType(producer.getEnergyType());
      auxProducers.add(auxProducer);
    }
    data.setProducersForEnd(auxProducers);
  }

  /**
   * Find cheap distributor int.
   *
   * @param data the data
   * @return the int
   */
  public int findCheapDistributor(final Data data) {
    int idDistr = -1, price, minPrice = 10000;
    // pe fiecare tura, parcurgem distribuitorii si
    // stabilim pretul contractelor
    // cautam contractul cel mai ieftin si retin id-ul distribuitorului
    for (Distributor distributor : data.getDistributors()) {
      if (!distributor.isBankrupt()) {
        // mai intai, vedem daca distribuitorul are suficienta energie
        if (distributor.getEnergyReceived() < distributor.getEnergyNeededKW()) {
          findProducers(distributor, data.getProducers());
        }
        distributor.setContractPrice();
        price = distributor.getContractPrice();
        if (price < minPrice) {
          minPrice = price;
          idDistr = distributor.getId();
        }
      }
    }

    return idDistr;
  }

  /**
   * Find distributors.
   *
   * @param data the data
   */
  public void findDistributors(final Data data) {
    int idDistr = findCheapDistributor(data);
    // consumatorii in cautare de contract, vor merge la distribuitorul cel
    // mai ieftin

    Distributor cheapDistributor = data.getDistributors().get(idDistr);
    saveLastMonthConsumers(data.getDistributors());
    // la finalul vizitei pe la fiecare consumator, acestia isi platesc contractul
    for (Consumer consumer : data.getConsumers()) {
      if (!consumer.isBankrupt()) {
        if (!consumer.getHasContract()) {
          Contract contract = new Contract();
          consumer.setHasContract(true);
          consumer.setDistributorId(idDistr);
          consumer.setContractPrice(cheapDistributor.getContractPrice());
          consumer.setNumberOfMonths(cheapDistributor.getContractLength());

          contract.setRemainedContractMonths(cheapDistributor.getContractLength());
          contract.setConsumerId(consumer.getId());
          contract.setPrice(cheapDistributor.getContractPrice());

          data.getDistributors().get(idDistr).getContracts().add(contract);
          cheapDistributor.setNumberOfCostumers(cheapDistributor.getNumberOfCostumers() + 1);
        }
      }
    }
  }

  /**
   * Save last month consumers.
   *
   * @param distributors the distributors
   */
  public void saveLastMonthConsumers(final ArrayList<Distributor> distributors) {
    for (Distributor distributor : distributors) {
      if (!distributor.isBankrupt()) {
        distributor.setLastMonthCostumers(distributor.getNumberOfCostumers());
        }
    }
  }

}
