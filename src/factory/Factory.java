package factory;

import consumer.Consumer;
import consumer.Entity;
import provider.Distributor;

/** The type Factory. */
public final class Factory {
    private static Factory factoryEntity;

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static Factory getInstance() {
        if (factoryEntity == null) {
            factoryEntity = new Factory();
        }
        return factoryEntity;
    }

    private Factory() {

    }

  /**
   * Factory entity.
   *
   * @param type the type
   * @param id the id
   * @param initialBudget the initial budget
   * @return the entity
   */
  public static Entity factory(final EntityType type, final int id, final int initialBudget) {
        if (type == EntityType.consumer) {
            return factoryEntity.produceConsumer(id, initialBudget);
        }
        return factoryEntity.produceDistributor(id, initialBudget);
    }

    private Consumer produceConsumer(final int id, final int initialBudget) {
        return new Consumer(id, initialBudget);
    }

    private Distributor produceDistributor(final int id, final int initialBudget) {
        return new Distributor(id, initialBudget);
    }
}
