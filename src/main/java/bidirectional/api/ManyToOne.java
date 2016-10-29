package bidirectional.api;

import javax.annotation.Nullable;

public interface ManyToOne {
  @Nullable
  OneToMany getOne();

  boolean setOne(@Nullable OneToMany one);
}
