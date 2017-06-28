package de.adrodoc55.bidirectional;

import java.lang.annotation.Annotation;
import java.util.HashSet;

import org.eclipse.persistence.annotations.Customizer;
import org.eclipse.persistence.indirection.IndirectSet;

import de.adrodoc55.bidirectional.identityhashset.IdentityHashSet;
import de.adrodoc55.bidirectional.identityhashset.LazyIdentityHashSetEnabler;

/**
 * {@link Annotation} für Implementierungen, die ein {@link IdentityHashSet} benötigen. <br>
 * Wenn die {@link Object#hashCode()} und {@link Object#equals(Object)} Methoden der Klasse auf
 * {@link System#identityHashCode(Object)} basieren (also wenn hashCode und equals nicht
 * überschrieben sind) kann in diesen Implementierungen auch ein normales {@link HashSet} verwendet
 * werden. <br>
 * In der Regel ist es jedoch erwünscht, das hashCode und equals auf ID basis implementiert sind,
 * insbesondere um Test einfacher schreiben zu können. Da in diesem Fall die Relation unabhängig von
 * der hashCode und equals Implementierung funktionieren muss, muss ein {@link IdentityHashSet}
 * verwendet werden.
 * <p>
 * Bei der Verwendung eines {@link IdentityHashSet}s ist es wichtig, dass die nutzende Klasse mit
 * {@link LazyIdentityHashSetEnabler} als {@link Customizer} annotiert ist:
 *
 * <pre>
 * {@code @Customizer(LazyIdentityHashSetEnabler.class)}
 * </pre>
 *
 * Ist dies nicht der Fall, wird von Eclipselink bei Lazy Loading ein {@link IndirectSet} verwendet,
 * welches bei Zugriff ein {@link HashSet} verwendet. Da sich bei einer ID basierte hashCode und
 * equals Implementierung der Hashcode ändern kann (zum Beispiel beim Setzen der ID während eines DB
 * insterts) eignet sich ein {@link HashSet} jedoch nicht um die Relation abzubilden.
 *
 * @author uffmanna
 */
public @interface RequiresIdentityHashSet {
}
