package bidirectional.impl.manytomany;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import bidirectional.api.ManyToMany;
import bidirectional.impl.Superclass;

public class ManyToManySetImpl extends Superclass implements ManyToMany {
  private Collection<ManyToMany> manys = new HashSet<>();

  @Override
  public Collection<ManyToMany> getManys() {
    return Collections.unmodifiableCollection(manys);
  }

  @Override
  public boolean addMany(ManyToMany many) {
    checkNotNull(many, "many == null!");
    if (manys.contains(many)) return false;
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
    if (!manys.contains(many)) return false;
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
