package bidirectional.impl.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.persistence.annotations.Customizer;

import bidirectional.LazyInstatiation;
import bidirectional.RequiresIdentityHashSet;
import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;
import bidirectional.identityhashset.IdentityHashSet;
import bidirectional.identityhashset.LazyIdentityHashSetEnabler;

/**
 * Die {@link OneToManySetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link OneToMany} Relation.
 *
 * Dies ist eine schnellere Variante der {@link OneToManyListContainsImpl}, die verwendet werden
 * kann, wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 *
 * @author uffmanna
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class OneToManySetImpl implements OneToMany {
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
