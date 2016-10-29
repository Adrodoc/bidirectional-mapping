package bidirectional.api;

import java.util.Collection;

public interface OneToMany {
  Collection<ManyToOne> getManys();

  boolean addMany(ManyToOne many);

  boolean removeMany(ManyToOne many);
}
