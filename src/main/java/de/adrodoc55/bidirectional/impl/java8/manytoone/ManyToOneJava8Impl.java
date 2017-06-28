package de.adrodoc55.bidirectional.impl.java8.manytoone;

import javax.annotation.Nullable;

import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.rt.java8.manytoone.ManyToOneJava8;

/**
 * Die {@link ManyToOneJava8Impl} ist einfache Implementierung einer {@link ManyToOne} Relation mit
 * Nutzung von Java 8 Features.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link OneToMany} Implementierungen.
 *
 * @author uffmanna
 */
public class ManyToOneJava8Impl implements ManyToOne {
  private @Nullable OneToMany one;

  @Override
  public @Nullable OneToMany getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToMany one) {
    return ManyToOneJava8.setOne(this, this.one, one, o -> this.one = o, OneToMany::addMany,
        OneToMany::removeMany);
  }
}
