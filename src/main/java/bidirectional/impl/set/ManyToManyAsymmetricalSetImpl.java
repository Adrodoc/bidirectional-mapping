package bidirectional.impl.set;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import bidirectional.api.ManyToMany;

public class ManyToManyAsymmetricalSetImpl implements ManyToMany {
  private Collection<ManyToMany> manys = new HashSet<>();

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
