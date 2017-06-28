package de.adrodoc55.bidirectional.identityhashset;

import java.util.Collection;

import org.eclipse.persistence.indirection.IndirectSet;
import org.eclipse.persistence.indirection.ValueHolder;

public class IndirectIdentityHashSet<T> extends IndirectSet<T> {
  private static final long serialVersionUID = 1L;

  public IndirectIdentityHashSet() {
    super();
    init();
  }

  public IndirectIdentityHashSet(Collection<? extends T> c) {
    super(c);
    init();
  }

  public IndirectIdentityHashSet(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
    init();
  }

  public IndirectIdentityHashSet(int initialCapacity) {
    super(initialCapacity);
    init();
  }

  @SuppressWarnings("rawtypes")
  private void init() {
    setValueHolder(new ValueHolder(new IdentityHashSet()));
    setUseLazyInstantiation(false);
  }

  @Override
  public void setUseLazyInstantiation(boolean useLazyInstantiation) {
    if (useLazyInstantiation) {
      throw new UnsupportedOperationException("This set does not support lazy instantiation");
    }
    super.setUseLazyInstantiation(useLazyInstantiation);
  }
}
