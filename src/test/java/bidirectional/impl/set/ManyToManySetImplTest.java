package bidirectional.impl.set;

import bidirectional.api.ManyToMany;
import bidirectional.api.ManyToManyTest;

public class ManyToManySetImplTest extends ManyToManyTest {
  @Override
  protected ManyToMany newManyToMany() {
    return new ManyToManySetImpl();
  }
}
