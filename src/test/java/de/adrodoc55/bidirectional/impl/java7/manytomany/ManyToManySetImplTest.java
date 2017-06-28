package de.adrodoc55.bidirectional.impl.java7.manytomany;

import java.util.Arrays;

import org.junit.runners.Parameterized.Parameters;

import de.adrodoc55.bidirectional.api.ManyToMany;
import de.adrodoc55.bidirectional.api.ParameterizedManyToManyTest;

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
