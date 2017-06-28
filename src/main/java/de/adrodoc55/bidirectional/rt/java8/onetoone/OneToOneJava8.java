package de.adrodoc55.bidirectional.rt.java8.onetoone;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.adrodoc55.bidirectional.impl.java8.onetoone.OneToOneJava8Impl;

/**
 * {@link OneToOneJava8} ist eine Laufzeit Helfer Klasse für die {@link OneToOneJava8Impl}.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 *
 * @author uffmanna
 */
public class OneToOneJava8 {
  public static <S, O> boolean setOne(S self, O oldOne, O newOne, Consumer<O> selfSet,
      BiConsumer<O, S> otherSet) {
    if (oldOne == newOne)
      return false;
    selfSet.accept(newOne);
    if (oldOne != null)
      otherSet.accept(oldOne, null);
    if (newOne != null)
      otherSet.accept(newOne, self);
    return true;
  }
}
