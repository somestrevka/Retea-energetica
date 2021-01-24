package strategies;

import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Distributor distributor, ArrayList<Producer> producers) {
        strategy.findDistributor(distributor, producers);
    }
}
