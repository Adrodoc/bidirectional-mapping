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
  public boolean setOne(@Nullable OneToMany one) {
    if (this.one == one)
      return false;
    if (this.one != null) {
      this.one.removeMany(this);
    }
    if (one != null) {
      one.addMany(this);
    }
    this.one = one;
    return true;
  }
}
