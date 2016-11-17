package bidirectional.impl.manytomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.persistence.annotations.Customizer;

import bidirectional.LazyInstatiation;
import bidirectional.RequiresIdentityHashSet;
import bidirectional.api.ManyToMany;
import bidirectional.identityhashset.IdentityHashSet;
import bidirectional.identityhashset.LazyIdentityHashSetEnabler;

/**
 * Die {@link ManyToManyAsymmetricalSetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link ManyToMany} Relation.
 *
 * Dies ist eine vereinfachte Variante der {@link ManyToManySetImpl}, die verwendet werden kann,
 * wenn {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung ist asymmetrisch, d.h. es können nicht beide Seiten einer Relation diese
 * Implementierung nutzen.
 * <p>
 * Da diese Implementierung asymmetrisch ist, ist sie nicht reflexiv, d.h. diese Implementierung
 * unterstützt keine Selbstreferenzierung eines Objektes.
 * <p>
 * Diese Implementierung unterstützt selbst keine {@link LazyInstatiation}, kann aber als Gegenstück
 * zur {@link ManyToManyListImpl} eingesetzt werden.
 *
 * @author uffmanna
 */
@Customizer(LazyIdentityHashSetEnabler.class)
public class ManyToManyAsymmetricalSetImpl implements ManyToMany, RequiresIdentityHashSet {
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
