package bidirectional.impl.java7.onetomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.ManyToOne;
import bidirectional.api.ParameterizedOneToManyTest;
import bidirectional.impl.java7.manytoone.ManyToOneJava7Impl;
import bidirectional.impl.java7.onetomany.OneToManyListImpl;

public class OneToManyListImplTest extends ParameterizedOneToManyTest<OneToManyListImpl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToOneJava7Impl.class//
    );
  }
}
