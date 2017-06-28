package de.adrodoc55.bidirectional.impl.java7.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.persistence.annotations.Customizer;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.RequiresIdentityHashSet;
import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;
import de.adrodoc55.bidirectional.identityhashset.IdentityHashSet;
import de.adrodoc55.bidirectional.identityhashset.LazyIdentityHashSetEnabler;

/**
 * Die {@link OneToManyJava7SetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link OneToMany} Relation.
 *
 * Dies ist eine schnellere Variante der {@link OneToManyJava7ListContainsImpl}, die verwendet
 * werden kann, wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToOne} Implementierungen.
 *
 * @author uffmanna
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class OneToManyJava7SetImpl implements OneToMany {
  private Collection<ManyToOne> manys = new IdentityHashSet<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (manys.add(many)) {
      many.setOne(this);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (manys.remove(many)) {
      many.setOne(null);
      return true;
    }
    return false;
  }
}
