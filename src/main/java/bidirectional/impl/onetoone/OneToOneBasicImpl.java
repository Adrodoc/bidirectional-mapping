package bidirectional.impl.onetoone;

import javax.annotation.Nullable;

import bidirectional.api.OneToOne;

/**
 * Die {@link OneToOneBasicImpl} ist einfache Implementierung einer {@link OneToOne} Relation.
 * <p>
 * Diese Implementierung ist symmetrisch, d.h. beide Seiten einer Relation können diese
 * Implementierung nutzen.
 * <p>
 * Diese Implementierung ist reflexiv, d.h. diese Implementierung unterstützt Selbstreferenzierung
 * eines Objektes.
 *
 * @author uffmanna
 */
public class OneToOneBasicImpl implements OneToOne {
  private @Nullable OneToOne one;

  @Override
  public @Nullable OneToOne getOne() {
    return one;
  }

  @Override
  public boolean setOne(@Nullable OneToOne otherOne) {
    if (this.one == otherOne)
      return false;
    OneToOne oldOne = this.one;
    this.one = otherOne;
    if (oldOne != null)
      oldOne.setOne(null);
    if (otherOne != null)
      otherOne.setOne(this);
    return true;
  }
}
