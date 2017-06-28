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
import de.adrodoc55.bidirectional.impl.java7.Superclass;

/**
 * Die {@link ManyToManyJava7SetImpl} ist eine {@link Set} basierte Implementierung einer
 * {@link ManyToMany} Relation.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 * <p>
 * Diese Implementierung unterstützt selbst keine {@link LazyInstatiation}, kann aber als Gegenstück
 * zur {@link ManyToManyJava7LazyListImpl} eingesetzt werden.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link ManyToMany} Implementierungen.
 *
 * @author uffmanna
 */
@RequiresIdentityHashSet
@Customizer(LazyIdentityHashSetEnabler.class)
public class ManyToManyJava7SetImpl extends Superclass implements ManyToMany {
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
        many.addMany(ManyToManyJava7SetImpl.this);
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
        many.removeMany(ManyToManyJava7SetImpl.this);
        manys.remove(many);
      }
    });
    return true;
  }
}
