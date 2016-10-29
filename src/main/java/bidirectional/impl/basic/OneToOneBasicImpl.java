package bidirectional.impl.basic;

import javax.annotation.Nullable;

import bidirectional.api.OneToOne;

public class OneToOneBasicImpl implements OneToOne {
  private @Nullable OneToOne one;

  @Override
  public @Nullable OneToOne getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToOne one) {
    if (this.one == one)
      return false;
    OneToOne oldOne = this.one;
    this.one = one;
    if (oldOne != null) {
      oldOne.setOne(null);
    }
    if (one != null) {
      one.setOne(this);
    }
    return true;
  }
}
