package de.adrodoc55.bidirectional.impl.java8.onetomany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.rt.java8.onetomany.OneToManyJava8ListContains;

/**
 * Die {@link OneToManyJava8ListContainsImpl} ist eine {@link List} basierte Implementierung einer
 * {@link OneToMany} Relation mit Nutzung von Java 8 Features.
 *
 * Dies ist eine vereinfachte Variante der {@link OneToManyJava8LazyListImpl}, die verwendet werden
 * kann, wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToOne} Implementierungen.
 *
 * @author uffmanna
 */
public class OneToManyJava8ListContainsImpl implements OneToMany {
  private Collection<ManyToOne> manys = new ArrayList<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    return OneToManyJava8ListContains.addMany(this, many, manys, ManyToOne::setOne);
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    return OneToManyJava8ListContains.removeMany(this, many, manys, ManyToOne::setOne);
  }
}
