package de.adrodoc55.bidirectional.impl.java8.onetoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.OneToOne;
import de.adrodoc55.bidirectional.api.ParameterizedOneToOneTest;
import de.adrodoc55.bidirectional.impl.java7.onetoone.OneToOneJava7Impl;

public class OneToOneJava8ImplTest extends ParameterizedOneToOneTest<OneToOneJava8Impl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToOneJava7Impl.class, //
        OneToOneJava8Impl.class //
    );
  }

  @Override
  protected boolean isReflexive() {
    return true;
  }
}
