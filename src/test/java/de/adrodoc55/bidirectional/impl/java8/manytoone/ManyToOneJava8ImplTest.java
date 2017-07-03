package de.adrodoc55.bidirectional.impl.java8.manytoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.api.ParameterizedManyToOneTest;
import de.adrodoc55.bidirectional.impl.java7.onetomany.OneToManyJava7LazyListImpl;
import de.adrodoc55.bidirectional.impl.java7.onetomany.OneToManyJava7ListContainsImpl;
import de.adrodoc55.bidirectional.impl.java7.onetomany.OneToManyJava7SetImpl;
import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8LazyListImpl;
import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8ListContainsImpl;
import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8SetImpl;

public class ManyToOneJava8ImplTest extends ParameterizedManyToOneTest<ManyToOneJava8Impl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToMany>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToManyJava7ListContainsImpl.class, //
        OneToManyJava7LazyListImpl.class, //
        OneToManyJava7SetImpl.class, //
        OneToManyJava8ListContainsImpl.class, //
        OneToManyJava8LazyListImpl.class, //
        OneToManyJava8SetImpl.class //
    );
  }
}
