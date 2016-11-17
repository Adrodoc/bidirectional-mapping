package bidirectional.identityhashset;

import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;

public class IdentityHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
  private static final long serialVersionUID = 1;

  private IdentityHashMap<E, Object> m; // The backing map
  private transient Set<E> s; // Its keySet

  // Dummy value to associate with an Object in the backing Map
  private static final Object PRESENT = new Object();

  public IdentityHashSet() {
    m = new IdentityHashMap<>();
    s = m.keySet();
  }

  public IdentityHashSet(Collection<? extends E> elements) {
    this();
    addAll(elements);
  }

  @Override
  @SuppressWarnings("unchecked")
  protected Object clone() throws CloneNotSupportedException {
    IdentityHashSet<E> result = (IdentityHashSet<E>) super.clone();
    result.m = (IdentityHashMap<E, Object>) m.clone();
    result.s = result.m.keySet();
    return result;
  }

  @Override
  public void clear() {
    s.clear();
  }

  @Override
  public int size() {
    return s.size();
  }

  @Override
  public boolean isEmpty() {
    return s.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return s.contains(o);
  }

  @Override
  public boolean remove(Object o) {
    return s.remove(o);
  }

  @Override
  public boolean add(E e) {
    return m.put(e, PRESENT) == null;
  }

  @Override
  public Iterator<E> iterator() {
    return s.iterator();
  }

  @Override
  public Object[] toArray() {
    return s.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return s.toArray(a);
  }

  @Override
  public String toString() {
    return s.toString();
  }

  @Override
  public int hashCode() {
    return s.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o == this || s.equals(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return s.containsAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return s.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return s.retainAll(c);
  }
  // addAll is the only inherited implementation

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return super.addAll(c);
  }

  private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
    stream.defaultReadObject();
    s = m.keySet();
  }
}
