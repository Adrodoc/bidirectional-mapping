package de.adrodoc55.bidirectional.impl.java7.onetomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.ParameterizedOneToManyTest;
import de.adrodoc55.bidirectional.impl.java7.manytoone.ManyToOneJava7Impl;
import de.adrodoc55.bidirectional.impl.java7.onetomany.OneToManyListContainsImpl;

public class OneToManyListContainsImplTest
    extends ParameterizedOneToManyTest<OneToManyListContainsImpl> {
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
