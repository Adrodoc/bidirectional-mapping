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
 * Die {@link ManyToManyJava7AsymmetricalSetImpl} ist eine {@link Set} basierte Implementierung
 * einer {@link ManyToMany} Relation.
 *
 * Dies ist eine vereinfachte Variante der {@link ManyToManyJava7SetImpl}, die verwendet werden
 * kann, wenn {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung ist asymmetrisch, d.h. es können nicht beide Seiten einer Relation diese
 * Implementierung nutzen.
 * <p>
 * Da diese Implementierung asymmetrisch ist, ist sie nicht reflexiv, d.h. diese Implementierung
 * unterstützt keine Selbstreferenzierung eines Objektes.
 * <p>
 * Diese Implementierung unterstützt selbst keine {@link LazyInstatiation}, kann aber als Gegenstück
 * zur {@link ManyToManyJava7LazyListImpl} eingesetzt werden.
 *
 * @author Adrodoc55
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class ManyToManyJava7AsymmetricalSetImpl implements ManyToMany {
  private Collection<ManyToMany> manys = new IdentityHashSet<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (!manys.contains(many)) {
      many.addMany(this);
      manys.add(many);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (manys.contains(many)) {
      many.removeMany(this);
      manys.remove(many);
      return true;
    }
    return false;
  }
}
