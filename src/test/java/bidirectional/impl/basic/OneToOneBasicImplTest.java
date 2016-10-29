package bidirectional.impl.basic;

import bidirectional.api.OneToOne;
import bidirectional.api.OneToOneTest;

public class OneToOneBasicImplTest extends OneToOneTest {
  @Override
  protected OneToOne newOneToOne() {
    return new OneToOneBasicImpl();
  }
}
