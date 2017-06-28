package de.adrodoc55.bidirectional.impl.java7.onetomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.ParameterizedOneToManyTest;
import de.adrodoc55.bidirectional.impl.java7.manytoone.ManyToOneJava7Impl;
import de.adrodoc55.bidirectional.impl.java8.manytoone.ManyToOneJava8Impl;

public class OneToManyJava7LazyListImplTest
    extends ParameterizedOneToManyTest<OneToManyJava7LazyListImpl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends ManyToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        ManyToOneJava7Impl.class, //
        ManyToOneJava8Impl.class //
    );
  }
}
