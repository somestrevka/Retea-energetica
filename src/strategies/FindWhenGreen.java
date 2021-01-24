package strategies;

import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;
import java.util.Comparator;

public class FindWhenGreen implements Strategy {
    @Override
    public void findDistributor(Distributor distributor, ArrayList<Producer> producers) {
        producers.sort(Comparator.comparing(Producer::getEnergyType)
                .thenComparing(Producer::getPriceKW).thenComparing(Producer::getEnergyPerDistributor));
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
