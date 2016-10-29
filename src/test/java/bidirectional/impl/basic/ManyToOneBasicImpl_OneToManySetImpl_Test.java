package bidirectional.impl.basic;

import bidirectional.api.ManyToOne;
import bidirectional.api.ManyToOneTest;
import bidirectional.api.OneToMany;
import bidirectional.impl.set.OneToManySetImpl;

public class ManyToOneBasicImpl_OneToManySetImpl_Test extends ManyToOneTest {
  @Override
  protected ManyToOne newManyToOne() {
    return new ManyToOneBasicImpl();
  }

  @Override
  protected OneToMany newOneToMany() {
    return new OneToManySetImpl();
  }
}
