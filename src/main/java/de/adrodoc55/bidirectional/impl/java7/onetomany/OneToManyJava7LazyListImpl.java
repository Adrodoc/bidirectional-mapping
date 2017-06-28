package de.adrodoc55.bidirectional.impl.java7.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.impl.Superclass;

/**
 * Die {@link OneToManyJava7LazyListImpl} ist eine {@link List} basierte Implementierung einer
 * {@link OneToMany} Relation.
 * <p>
 * Diese Implementierung unterstützt {@link LazyInstatiation}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToOne} Implementierungen.
 *
 * @author Adrodoc55
 */
@LazyInstatiation
public class OneToManyJava7LazyListImpl extends Superclass implements OneToMany {
  private Collection<ManyToOne> manys = new ArrayList<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (this == many.getOne())
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        manys.add(many);
        many.setOne(OneToManyJava7LazyListImpl.this);
      }
    });
    return true;
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (this != many.getOne())
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        manys.remove(many);
        many.setOne(null);
      }
    });
    return true;
  }
}
