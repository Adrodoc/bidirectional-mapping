package de.adrodoc55.bidirectional.impl.java8.onetomany;

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
import de.adrodoc55.bidirectional.impl.Superclass;
import de.adrodoc55.bidirectional.rt.java8.onetomany.OneToManyJava8Set;

/**
 * Die {@link OneToManyJava8SetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link OneToMany} Relation.
 *
 * Dies ist eine schnellere Variante der {@link OneToManyJava8ListContainsImpl}, die verwendet
 * werden kann, wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToOne} Implementierungen.
 *
 * @author Adrodoc55
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class OneToManyJava8SetImpl //
    extends Superclass // Eigentlich nicht notwendig, siehe OneToMany Javadoc
    implements OneToMany {
  private Collection<ManyToOne> manys = new IdentityHashSet<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    return OneToManyJava8Set.addMany(this, many, manys, ManyToOne::setOne);
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    return OneToManyJava8Set.removeMany(this, many, manys, ManyToOne::setOne);
  }
}
