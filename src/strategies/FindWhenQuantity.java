package strategies;

import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FindWhenQuantity implements Strategy{
    @Override
    public void findDistributor(Distributor distributor, ArrayList<Producer> producers) {
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
}
