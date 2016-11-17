package bidirectional.impl.onetomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToOne;
import bidirectional.api.ParameterizedOneToManyTest;
import bidirectional.impl.manytoone.ManyToOneBasicImpl;
import bidirectional.impl.onetomany.OneToManySetImpl;

public class OneToManySetImplTest extends ParameterizedOneToManyTest<OneToManySetImpl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToOneBasicImpl.class//
    );
  }
}
