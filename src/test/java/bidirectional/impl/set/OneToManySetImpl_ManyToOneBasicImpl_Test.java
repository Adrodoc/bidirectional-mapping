package bidirectional.impl.set;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;
import bidirectional.api.OneToManyTest;
import bidirectional.impl.basic.ManyToOneBasicImpl;

public class OneToManySetImpl_ManyToOneBasicImpl_Test extends OneToManyTest {
  @Override
  protected OneToMany newOneToMany() {
    return new OneToManySetImpl();
  }

  @Override
  protected ManyToOne newManyToOne() {
    return new ManyToOneBasicImpl();
  }
}
