package bidirectional.impl.set;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToOne;
import bidirectional.api.ParameterizedOneToManyTest;
import bidirectional.impl.basic.ManyToOneBasicImpl;

public class OneToManySetImplTest extends ParameterizedOneToManyTest<OneToManySetImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToOneBasicImpl.class//
    );
  }
}
