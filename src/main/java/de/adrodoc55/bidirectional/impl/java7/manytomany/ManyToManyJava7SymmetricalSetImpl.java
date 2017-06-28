package de.adrodoc55.bidirectional.impl.java7.manytomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.persistence.annotations.Customizer;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.RequiresIdentityHashSet;
import de.adrodoc55.bidirectional.api.ManyToMany;
import de.adrodoc55.bidirectional.identityhashset.IdentityHashSet;
import de.adrodoc55.bidirectional.identityhashset.LazyIdentityHashSetEnabler;

/**
 * Die {@link ManyToManyJava7SymmetricalSetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link ManyToMany} Relation.
 *
 * Dies ist eine vereinfachte Variante der {@link ManyToManyJava7SetImpl}, die verwendet werden kann,
 * wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 *
 * @author uffmanna
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class ManyToManyJava7SymmetricalSetImpl implements ManyToMany {
  private Collection<ManyToMany> manys = new IdentityHashSet<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (manys.add(many)) {
      many.addMany(this);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (manys.remove(many)) {
      many.removeMany(this);
      return true;
    }
    return false;
  }

}
