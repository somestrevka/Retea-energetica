package strategies;

import provider.Distributor;
import provider.Producer;

import java.util.ArrayList;

public interface Strategy {
    public void findDistributor(Distributor distributor, ArrayList<Producer> producers);
}
