package de.adrodoc55.bidirectional.rt.java8.onetomany;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import de.adrodoc55.bidirectional.impl.java8.onetomany.OneToManyJava8LazyListImpl;
import de.adrodoc55.bidirectional.rt.RecursionPrevention;

/**
 * {@link OneToManyJava8LazyList} ist eine Laufzeit Helfer Klasse für die
 * {@link OneToManyJava8LazyListImpl}.
 *
 * @author Adrodoc55
 */
public class OneToManyJava8LazyList {
  public static <S extends RecursionPrevention, M> boolean addMany(S self, M many, S other,
      Collection<? super M> manys, BiConsumer<? super M, ? super S> otherSet) {
    return addMany(self, many, other, manys::add, otherSet);
  }

  public static <S extends RecursionPrevention, M> boolean addMany(S self, M many, S other,
      Consumer<? super M> selfAdd, BiConsumer<? super M, ? super S> otherSet) {
    if (self == other)
      return false;
    self.runNonRecursive(new Runnable() {
      @Override
      public void run() {
        selfAdd.accept(many);
        otherSet.accept(many, self);
      }
    });
    return true;
  }

  public static <S extends RecursionPrevention, M> boolean removeMany(S self, M many, S other,
      Collection<? super M> manys, BiConsumer<? super M, ? super S> otherSet) {
    return removeMany(self, many, other, manys::remove, otherSet);
  }

  public static <S extends RecursionPrevention, M> boolean removeMany(S self, M many, S other,
      Consumer<? super M> selfRemove, BiConsumer<? super M, ? super S> otherSet) {
    if (self != other)
      return false;
    self.runNonRecursive(new Runnable() {
      @Override
      public void run() {
        selfRemove.accept(many);
        otherSet.accept(many, null);
      }
    });
    return true;
  }
}
