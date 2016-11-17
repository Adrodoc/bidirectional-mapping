package bidirectional.impl.onetoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.OneToOne;
import bidirectional.api.ParameterizedOneToOneTest;

public class OneToOneBasicImplTest extends ParameterizedOneToOneTest<OneToOneBasicImpl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToOneBasicImpl.class//
    );
  }
}
