package bidirectional.api;

import javax.annotation.Nullable;


public interface OneToOne {
  @Nullable
  OneToOne getOne();

  boolean setOne(@Nullable OneToOne one);
}
