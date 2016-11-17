package bidirectional.impl.onetoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.OneToOne;
import bidirectional.api.ParameterizedOneToOneTest;
import bidirectional.impl.onetoone.OneToOneBasicImpl;

public class OneToOneBasicImplTest extends ParameterizedOneToOneTest<OneToOneBasicImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToOneBasicImpl.class//
    );
  }
}
