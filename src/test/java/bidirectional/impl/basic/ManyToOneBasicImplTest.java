package bidirectional.impl.basic;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.OneToMany;
import bidirectional.api.ParameterizedManyToOneTest;
import bidirectional.impl.list.OneToManyListContainsImpl;
import bidirectional.impl.list.OneToManyListImpl;
import bidirectional.impl.set.OneToManySetImpl;

public class ManyToOneBasicImplTest extends ParameterizedManyToOneTest<ManyToOneBasicImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToManyListContainsImpl.class, //
        OneToManyListImpl.class, //
        OneToManySetImpl.class//
    );
  }
}
