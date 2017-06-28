package de.adrodoc55.bidirectional.rt.java8.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8ListContainsImpl;

/**
 * {@link OneToManyJava8ListContains} ist eine Laufzeit Helfer Klasse für die
 * {@link OneToManyJava8ListContainsImpl}.
 *
 * @author uffmanna
 */
public class OneToManyJava8ListContains {
  public static <S, M> boolean addMany(S self, M many, Collection<? super M> manys,
      BiConsumer<? super M, ? super S> otherSet) {
    return addMany(self, many, manys::contains, manys::add, otherSet);
  }

  public static <S, M> boolean addMany(S self, M many, Function<? super M, Boolean> selfContains,
      Consumer<? super M> selfAdd, BiConsumer<? super M, ? super S> otherSet) {
    checkNotNull(many, "many == null!");
    if (!selfContains.apply(many)) {
      selfAdd.accept(many);
      otherSet.accept(many, self);
      return true;
    }
    return false;
  }

  public static <S, M> boolean removeMany(S self, M many, Collection<? super M> manys,
      BiConsumer<? super M, ? super S> otherSet) {
    return removeMany(self, many, manys::contains, manys::remove, otherSet);
  }

  public static <S, M> boolean removeMany(S self, M many, Function<? super M, Boolean> selfContains,
      Consumer<? super M> selfRemove, BiConsumer<? super M, ? super S> otherSet) {
    checkNotNull(many, "many == null!");
    if (selfContains.apply(many)) {
      selfRemove.accept(many);
      otherSet.accept(many, null);
      return true;
    }
    return false;
  }
}
