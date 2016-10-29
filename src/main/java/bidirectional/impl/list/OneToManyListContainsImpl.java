package bidirectional.impl.list;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;

public class OneToManyListContainsImpl implements OneToMany {
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
