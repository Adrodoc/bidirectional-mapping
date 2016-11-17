package bidirectional.impl.manytomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import bidirectional.api.ManyToMany;

public class ManyToManySymmetricalSetImpl implements ManyToMany {
  private Collection<ManyToMany> manys = new HashSet<>();

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
