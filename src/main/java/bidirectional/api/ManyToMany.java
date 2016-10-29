package bidirectional.api;

import java.util.Collection;

public interface ManyToMany {
  Collection<ManyToMany> getManys();

  boolean addMany(ManyToMany many);

  boolean removeMany(ManyToMany many);
}
