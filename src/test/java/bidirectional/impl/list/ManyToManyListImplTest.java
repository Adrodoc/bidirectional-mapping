package bidirectional.impl.list;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToMany;
import bidirectional.api.ParameterizedManyToManyTest;
import bidirectional.impl.set.ManyToManyAsymmetricalSetImpl;
import bidirectional.impl.set.ManyToManySetImpl;

public class ManyToManyListImplTest extends ParameterizedManyToManyTest<ManyToManyListImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToManyAsymmetricalSetImpl.class, //
        ManyToManySetImpl.class //
    );
  }
}
