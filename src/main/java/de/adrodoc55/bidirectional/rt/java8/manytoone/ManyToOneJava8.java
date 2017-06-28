package de.adrodoc55.bidirectional.rt.java8.manytoone;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.impl.java8.manytoone.ManyToOneJava8Impl;

/**
 * {@link ManyToOneJava8} ist eine Laufzeit Helfer Klasse für die {@link ManyToOneJava8Impl}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link OneToMany} Implementierungen.
 *
 * @author uffmanna
 */
public class ManyToOneJava8 {
  public static <S, O> boolean setOne(S self, O oldOne, O newOne, Consumer<O> selfSet,
      BiConsumer<O, S> otherAdd, BiConsumer<O, S> otherRemove) {
    if (oldOne == newOne)
      return false;
    if (oldOne != null)
      otherRemove.accept(oldOne, self);
    if (newOne != null)
      otherAdd.accept(newOne, self);
    selfSet.accept(newOne);
    return true;
  }
}
