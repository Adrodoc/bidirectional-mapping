package de.adrodoc55.bidirectional.impl.java7;

import javax.persistence.Transient;

/**
 * Die Methode {@link #runNonRecursive(Runnable)} wird von einigen Implementierungen benötigt, es
 * ist ausreichend diese Methode in einer gemeinsamen Superklasse zu implementieren.
 *
 * @author Adrodoc55
 */
public abstract class Superclass {
  @Transient
  private boolean pendingRelation;

  /**
   * Führt das angegebene {@link Runnable} aus, falls es sich hierbei nicht um einen rekursiven
   * Aufruf handelt.
   *
   * @param runnable die auszuführende Aktion
   */
  public void runNonRecursive(Runnable runnable) {
    if (pendingRelation)
      return;
    try {
      pendingRelation = true;
      runnable.run();
    } finally {
      pendingRelation = false;
    }
  }
}
