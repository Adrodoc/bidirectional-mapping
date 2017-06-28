package bidirectional.impl.java7.manytoone;

import javax.annotation.Nullable;

import bidirectional.api.ManyToOne;
import bidirectional.api.OneToMany;

/**
 * Die {@link ManyToOneJava7Impl} ist einfache Implementierung einer {@link ManyToOne} Relation.
 * <p>
 * Diese Implementierung unterstützt alle bekannten {@link OneToMany} Implementierungen.
 *
 * @author uffmanna
 */
public class ManyToOneJava7Impl implements ManyToOne {
  private @Nullable OneToMany one;

  @Override
  public @Nullable OneToMany getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToMany otherOne) {
    if (this.one == otherOne)
      return false;
    if (this.one != null)
      this.one.removeMany(this);
    if (otherOne != null)
      otherOne.addMany(this);
    this.one = otherOne;
    return true;
  }
}
