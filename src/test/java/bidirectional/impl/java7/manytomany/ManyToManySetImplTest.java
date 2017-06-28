package bidirectional.impl.java7.manytomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToMany;
import bidirectional.api.ParameterizedManyToManyTest;
import bidirectional.impl.java7.manytomany.ManyToManyAsymmetricalSetImpl;
import bidirectional.impl.java7.manytomany.ManyToManyListImpl;
import bidirectional.impl.java7.manytomany.ManyToManySetImpl;
import bidirectional.impl.java7.manytomany.ManyToManySymmetricalSetImpl;

public class ManyToManySetImplTest extends ParameterizedManyToManyTest<ManyToManySetImpl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToManyAsymmetricalSetImpl.class, //
        ManyToManyListImpl.class, //
        ManyToManySetImpl.class, //
        ManyToManySymmetricalSetImpl.class//
    );
  }
}
