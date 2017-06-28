package de.adrodoc55.bidirectional.api;

import java.util.Collection;

import de.adrodoc55.bidirectional.rt.RecursionPrevention;

/**
 * Die Schnittstellen-Definition aller {@link OneToMany}-Implementierungen.
 * <p>
 * Eigentlich gehört {@link RecursionPrevention} nicht zur Schnittstelle. In den generischen
 * Kopiervorlagen ist es jedoch erforderlich dieses Interface zu erweitern, da sonst jede
 * Implementierung ihren inversen Typ kennen müsste. Dies ist zwar in der Praxis der Fall, aber die
 * Kopiervorlagen sollen mehrere inverse Typen unterstützen um einfach parametrisierte generische
 * Tests zu ermöglichen.
 *
 * @author Adrodoc55
 */
public interface OneToMany extends RecursionPrevention {
  Collection<ManyToOne> getManys();

  boolean addMany(ManyToOne many);

  boolean removeMany(ManyToOne many);
}
