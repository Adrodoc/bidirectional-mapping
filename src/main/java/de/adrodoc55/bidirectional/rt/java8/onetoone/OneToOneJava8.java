package de.adrodoc55.bidirectional.rt.java8.onetoone;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.adrodoc55.bidirectional.impl.java8.onetoone.OneToOneJava8Impl;

/**
 * {@link OneToOneJava8} ist eine Laufzeit Helfer Klasse für die {@link OneToOneJava8Impl}.
 *
 * @author uffmanna
 */
public class OneToOneJava8 {
  public static <S, O> boolean setOne(S self, O oldOne, O newOne, Consumer<? super O> selfSet,
      BiConsumer<? super O, ? super S> otherSet) {
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
