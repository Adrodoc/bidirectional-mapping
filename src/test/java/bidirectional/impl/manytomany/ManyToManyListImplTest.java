package bidirectional.impl.manytomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToMany;
import bidirectional.api.ParameterizedManyToManyTest;
import bidirectional.impl.manytomany.ManyToManyAsymmetricalSetImpl;
import bidirectional.impl.manytomany.ManyToManyListImpl;
import bidirectional.impl.manytomany.ManyToManySetImpl;

public class ManyToManyListImplTest extends ParameterizedManyToManyTest<ManyToManyListImpl> {
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToManyAsymmetricalSetImpl.class, //
        ManyToManySetImpl.class //
    );
  }
}
