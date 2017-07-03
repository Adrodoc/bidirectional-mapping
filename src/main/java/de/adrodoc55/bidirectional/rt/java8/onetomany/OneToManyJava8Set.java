package de.adrodoc55.bidirectional.rt.java8.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8SetImpl;

/**
 * {@link OneToManyJava8Set} ist eine Laufzeit Helfer Klasse für die {@link OneToManyJava8SetImpl}.
 *
 * @author Adrodoc55
 */
public class OneToManyJava8Set {
  public static <S, M> boolean addMany(S self, M many, Collection<? super M> manys,
      BiConsumer<? super M, ? super S> otherSet) {
    return addMany(self, many, manys::add, otherSet);
  }

  public static <S, M> boolean addMany(S self, M many, Function<? super M, Boolean> selfAdd,
      BiConsumer<? super M, ? super S> otherSet) {
    checkNotNull(many, "many == null!");
    if (selfAdd.apply(many)) {
      otherSet.accept(many, self);
      return true;
    }
    return false;
  }

  public static <S, M> boolean removeMany(S self, M many, Collection<? super M> manys,
      BiConsumer<? super M, ? super S> otherSet) {
    return removeMany(self, many, manys::remove, otherSet);
  }

  public static <S, M> boolean removeMany(S self, M many, Function<? super M, Boolean> selfRemove,
      BiConsumer<? super M, ? super S> otherSet) {
    checkNotNull(many, "many == null!");
    if (selfRemove.apply(many)) {
      otherSet.accept(many, null);
      return true;
    }
    return false;
  }
}
