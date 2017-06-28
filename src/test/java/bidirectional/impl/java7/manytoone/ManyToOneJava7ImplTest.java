package bidirectional.impl.java7.manytoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import bidirectional.api.OneToMany;
import bidirectional.api.ParameterizedManyToOneTest;
import bidirectional.impl.java7.manytoone.ManyToOneJava7Impl;
import bidirectional.impl.java7.onetomany.OneToManyListContainsImpl;
import bidirectional.impl.java7.onetomany.OneToManyListImpl;
import bidirectional.impl.java7.onetomany.OneToManySetImpl;

public class ManyToOneJava7ImplTest extends ParameterizedManyToOneTest<ManyToOneJava7Impl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToManyListContainsImpl.class, //
        OneToManyListImpl.class, //
        OneToManySetImpl.class//
    );
  }
}
