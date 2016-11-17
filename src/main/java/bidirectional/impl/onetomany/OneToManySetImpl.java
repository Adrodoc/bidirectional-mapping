package bidirectional.impl.onetomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;

public class OneToManySetImpl implements OneToMany {
  private Collection<ManyToOne> manys = new HashSet<>();

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
