package bidirectional.impl.basic;

import javax.annotation.Nullable;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;

public class ManyToOneBasicImpl implements ManyToOne {
  private @Nullable OneToMany one;

  @Override
  public @Nullable OneToMany getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToMany otherOne) {
    if (this.one == otherOne) return false;
    if (this.one != null) this.one.removeMany(this);
    if (otherOne != null) otherOne.addMany(this);
    this.one = otherOne;
    return true;
  }
}
