package bidirectional.impl.list;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToOne;
import bidirectional.api.ParameterizedOneToManyTest;
import bidirectional.impl.basic.ManyToOneBasicImpl;

public class OneToManyListImplTest extends ParameterizedOneToManyTest<OneToManyListImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToOneBasicImpl.class//
    );
  }
}
