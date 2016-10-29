package bidirectional.impl.list;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;

public class OneToManyListImpl extends Superclass implements OneToMany {
  private Collection<ManyToOne> manys = new ArrayList<>();

  @Override
  public Collection<ManyToOne> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (this == many.getOne())
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.setOne(OneToManyListImpl.this);
        manys.add(many);
      }
    });
    return true;
  }

  @Override
  public boolean removeMany(ManyToOne many) {
    checkNotNull(many, "many == null!");
    if (this != many.getOne())
      return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.setOne(null);
        manys.remove(many);
      }
    });
    return true;
  }

}
