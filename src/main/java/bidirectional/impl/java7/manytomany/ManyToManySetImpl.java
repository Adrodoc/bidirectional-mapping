package bidirectional.impl.java7.manytomany;

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
import bidirectional.impl.java7.Superclass;

/**
 * Die {@link ManyToManySetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link ManyToMany} Relation.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 * <p>
 * Diese Implementierung unterstützt selbst keine {@link LazyInstatiation}, kann aber als Gegenstück
 * zur {@link ManyToManyListImpl} eingesetzt werden.
 *
 * @author uffmanna
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class ManyToManySetImpl extends Superclass implements ManyToMany {
  private Collection<ManyToMany> manys = new IdentityHashSet<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (manys.contains(many))
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.addMany(ManyToManySetImpl.this);
        manys.add(many);
      }
    });
    return true;
  }

  @Override
  public boolean removeMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (!manys.contains(many))
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.removeMany(ManyToManySetImpl.this);
        manys.remove(many);
      }
    });
    return true;
  }

}
