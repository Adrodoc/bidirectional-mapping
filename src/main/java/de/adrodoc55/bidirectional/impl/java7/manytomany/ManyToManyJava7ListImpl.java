package de.adrodoc55.bidirectional.impl.java7.manytomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.api.ManyToMany;
import de.adrodoc55.bidirectional.impl.java7.Superclass;

/**
 * Die {@link ManyToManyJava7ListImpl} ist eine {@link List} basierte Implementierung einer
 * {@link ManyToMany} Relation.
 * <p>
 * Diese Implementierung ist zwar symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung theoretisch nutzen, in diesem Fall wird aber keine {@link LazyInstatiation}
 * unterstützt.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 * <p>
 * Diese Implementierung unterstützt {@link LazyInstatiation}, wenn nicht beide Seiten einer
 * Relation diese Implementierung nutzen.
 *
 * @author uffmanna
 */
@LazyInstatiation
public class ManyToManyJava7ListImpl extends Superclass implements ManyToMany {
  private Collection<ManyToMany> manys = new ArrayList<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (many.getManys().contains(this))
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.addMany(ManyToManyJava7ListImpl.this);
        manys.add(many);
      }
    });
    return true;
  }

  @Override
  public boolean removeMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (!many.getManys().contains(this))
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.removeMany(ManyToManyJava7ListImpl.this);
        manys.remove(many);
      }
    });
    return true;
  }

}
