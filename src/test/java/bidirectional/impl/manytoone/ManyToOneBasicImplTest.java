package bidirectional.impl.manytoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.OneToMany;
import bidirectional.api.ParameterizedManyToOneTest;
import bidirectional.impl.manytoone.ManyToOneBasicImpl;
import bidirectional.impl.onetomany.OneToManyListContainsImpl;
import bidirectional.impl.onetomany.OneToManyListImpl;
import bidirectional.impl.onetomany.OneToManySetImpl;

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
