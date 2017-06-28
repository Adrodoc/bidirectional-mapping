package de.adrodoc55.bidirectional.rt;

/**
 * Die Methode {@link #runNonRecursive(Runnable)} wird von einigen Implementierungen benötigt, es
 * ist ausreichend diese Methode in einer gemeinsamen Superklasse zu implementieren.
 *
 * @author Adrodoc55
 */
public interface RecursionPrevention {
  /**
   * Führt das angegebene {@link Runnable} aus, falls es sich hierbei nicht um einen rekursiven
   * Aufruf handelt.
   *
   * @param runnable die auszuführende Aktion
   */
  void runNonRecursive(Runnable runnable);
}
