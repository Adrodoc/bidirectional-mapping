package bidirectional.impl.onetoone;

import javax.annotation.Nullable;

import bidirectional.api.OneToOne;

public class OneToOneBasicImpl implements OneToOne {
  private @Nullable OneToOne one;

  @Override
  public @Nullable OneToOne getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToOne otherOne) {
    if (this.one == otherOne) return false;
    OneToOne oldOne = this.one;
    this.one = otherOne;
    if (oldOne != null) oldOne.setOne(null);
    if (otherOne != null) otherOne.setOne(this);
    return true;
  }
}
