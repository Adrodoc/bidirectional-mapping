package de.adrodoc55.bidirectional;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.persistence.indirection.IndirectList;
import org.eclipse.persistence.indirection.IndirectSet;
import org.eclipse.persistence.mappings.CollectionMapping;
import org.eclipse.persistence.sessions.SessionEventListener;

import de.adrodoc55.bidirectional.api.ManyToMany;
import de.adrodoc55.bidirectional.api.OneToMany;

/**
 * {@link Annotation} für Implementierungen, die
 * <a href="https://en.wikipedia.org/wiki/Lazy_initialization">Lazy Instatiation</a> unterstützen.
 * Lazy Instatiation kann von Eclipselink als Performance Optimierung bei <a href=
 * "http://www.eclipse.org/eclipselink/documentation/2.6/concepts/mappingintro002.htm#CEGGCCGA">
 * Transparent Indirection</a> in {@link OneToMany} und {@link ManyToMany} Relationen eingesetzt
 * werden, {@link CollectionMapping#setUseLazyInstantiationForIndirectCollection(Boolean) dies ist
 * jedoch nur bei IndirectList der default} : <blockquote> {@link IndirectList} and
 * {@link IndirectSet} can be configured not to instantiate the list from the database when you add
 * and remove from them. IndirectList defaults to this behavior. When Set to true, the collection
 * associated with this TransparentIndirection will be setup so as not to instantiate for adds and
 * removes. The weakness of this setting for an IndirectSet is that when the set is not
 * instantiated, if a duplicate element is added, it will not be detected until commit time.
 * </blockquote> Daher sollten Implementierungen die Lazy Instatiation unterstützen möchten keine
 * {@link Set}s verwenden, da sonst ein {@link SessionEventListener} verwendet werden muss.
 * <p>
 * Damit eine Implementierung Lazy Instatiation von Eclipselink unterstützen kann, darf sie in den
 * add und remove Methoden folgende Methoden der {@link Collection} NICHT nutzen:
 * <ul>
 * <li>{@link List#add(int, Object)}
 * <li>{@link List#addAll(int, Collection)}
 * <li>{@link Collection#clear()}
 * <li>{@link Collection#contains(Object)}
 * <li>{@link Collection#containsAll(Collection)}
 * <li>{@link Vector#capacity()}
 * <li>{@link Vector#elementAt(int)}
 * <li>{@link Vector#elements()}
 * <li>{@link Vector#ensureCapacity(int)}
 * <li>{@link Object#equals(Object)}
 * <li>{@link Vector#firstElement()}
 * <li>{@link Vector#get(int)}
 * <li>{@link Object#hashCode()}
 * <li>{@link Vector#indexOf(Object)}
 * <li>{@link Vector#indexOf(Object, int)}
 * <li>{@link Vector#insertElementAt(Object, int)}
 * <li>{@link Vector#isEmpty()}
 * <li>{@link Vector#iterator()}
 * <li>{@link Vector#lastElement()}
 * <li>{@link Vector#lastIndexOf(Object)}
 * <li>{@link Vector#lastIndexOf(Object, int)}
 * <li>{@link List#listIterator()}
 * <li>{@link List#listIterator(int)}
 * <li>{@link List#remove(int)}
 * <li>{@link Vector#removeAllElements()}
 * <li>{@link Vector#removeElementAt(int)}
 * <li>{@link Collection#retainAll(Collection)}
 * <li>{@link List#set(int, Object)}
 * <li>{@link Vector#setElementAt(Object, int)}
 * <li>{@link Vector#setSize(int)}
 * <li>{@link Collection#size()}
 * <li>{@link List#subList(int, int)}
 * <li>{@link Collection#toArray()}
 * <li>{@link Collection#toArray(Object[])}
 * <li>{@link Vector#trimToSize()}
 * </ul>
 *
 * @author Adrodoc55
 */
public @interface LazyInstatiation {
}
