package de.adrodoc55.bidirectional.impl.java8.onetoone;

import javax.annotation.Nullable;

import de.adrodoc55.bidirectional.api.OneToOne;
import de.adrodoc55.bidirectional.rt.java8.onetoone.OneToOneJava8;

/**
 * Die {@link OneToOneJava8Impl} ist einfache Implementierung einer {@link OneToOne} Relation mit
 * Nutzung von Java 8 Features.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link OneToOne} Implementierungen.
 *
 * @author Adrodoc55
 */
public class OneToOneJava8Impl implements OneToOne {
  private @Nullable OneToOne one;

  @Override
  public @Nullable OneToOne getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToOne one) {
    return OneToOneJava8.setOne(this, this.one, one, o -> this.one = o, OneToOne::setOne);
  }
}
