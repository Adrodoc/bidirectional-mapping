package de.adrodoc55.bidirectional.impl.java8.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.impl.Superclass;
import de.adrodoc55.bidirectional.rt.java8.onetomany.OneToManyJava8LazyList;

/**
 * Die {@link OneToManyJava8LazyListImpl} ist eine {@link List} basierte Implementierung einer
 * {@link OneToMany} Relation mit Nutzung von Java 8 Features.
 * <p>
 * Diese Implementierung unterstützt {@link LazyInstatiation}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToOne} Implementierungen.
 *
 * @author Adrodoc55
 */
@LazyInstatiation
public class OneToManyJava8LazyListImpl extends Superclass implements OneToMany {
  private Collection<ManyToOne> manys = new ArrayList<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    return OneToManyJava8LazyList.addMany(this, many, many.getOne(), manys, ManyToOne::setOne);
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    return OneToManyJava8LazyList.removeMany(this, many, many.getOne(), manys, ManyToOne::setOne);
  }
}
