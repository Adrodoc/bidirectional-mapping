package bidirectional.impl.basic;

import bidirectional.api.ManyToOne;
import bidirectional.api.ManyToOneTest;
import bidirectional.api.OneToMany;
import bidirectional.impl.list.OneToManyListImpl;

public class ManyToOneBasicImpl_OneToManyListImpl_Test extends ManyToOneTest {
  @Override
  protected ManyToOne newManyToOne() {
    return new ManyToOneBasicImpl();
  }

  @Override
  protected OneToMany newOneToMany() {
    return new OneToManyListImpl();
  }
}
