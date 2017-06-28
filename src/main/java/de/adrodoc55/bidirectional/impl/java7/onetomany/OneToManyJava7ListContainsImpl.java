package de.adrodoc55.bidirectional.impl.java7.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.adrodoc55.bidirectional.LazyInstatiation;
import de.adrodoc55.bidirectional.api.ManyToOne;
import de.adrodoc55.bidirectional.api.OneToMany;

/**
 * Die {@link OneToManyJava7ListContainsImpl} ist eine {@link List} basierte Implementierung einer
 * {@link OneToMany} Relation.
 *
 * Dies ist eine vereinfachte Variante der {@link OneToManyJava7ListImpl}, die verwendet werden kann,
 * wenn keine {@link LazyInstatiation} benötigt wird.
 * <p>
 * Diese Implementierung unterstützt keine {@link LazyInstatiation}.
 *
 * @author uffmanna
 */
public class OneToManyJava7ListContainsImpl implements OneToMany {
  private Collection<ManyToOne> manys = new ArrayList<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (!manys.contains(many)) {
      manys.add(many);
      many.setOne(this);
      return true;
    }
    return false;
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (manys.contains(many)) {
      manys.remove(many);
      many.setOne(null);
      return true;
    }
    return false;
  }

}
