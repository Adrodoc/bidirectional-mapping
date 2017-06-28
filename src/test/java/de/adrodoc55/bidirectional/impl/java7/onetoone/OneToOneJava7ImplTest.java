package de.adrodoc55.bidirectional.impl.java7.onetoone;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.OneToOne;
import de.adrodoc55.bidirectional.api.ParameterizedOneToOneTest;
import de.adrodoc55.bidirectional.impl.java7.onetoone.OneToOneJava7Impl;

public class OneToOneJava7ImplTest extends ParameterizedOneToOneTest<OneToOneJava7Impl> {
  /**
   * Liefert alle Klassen, die als Gegenstück zu der unter Test stehenden Implementierung
   * unterstützt werden.
   *
   * @return alle unterstützten Gegenklassen
   */
  @Parameters(name = "{0}")
  public static Iterable<Class<? extends OneToOne>> supportedInverseClasses() {
    return Arrays.asList(//
        OneToOneJava7Impl.class//
    );
  }
}
