package bidirectional.impl.list;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import bidirectional.api.ManyToMany;

public class ManyToManyListImpl extends Superclass implements ManyToMany {
  private Collection<ManyToMany> manys = new ArrayList<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (many.getManys().contains(this)) return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.addMany(ManyToManyListImpl.this);
        manys.add(many);
      }
    });
    return true;
  }

  @Override
  public boolean removeMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (!many.getManys().contains(this)) return false;
    runNonRecursive(new Runnable() {
      @Override
      public void run() {
        many.removeMany(ManyToManyListImpl.this);
        manys.remove(many);
      }
    });
    return true;
  }

}
