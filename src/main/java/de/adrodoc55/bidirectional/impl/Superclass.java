package de.adrodoc55.bidirectional.impl;

import javax.persistence.Transient;

import de.adrodoc55.bidirectional.rt.RecursionPrevention;

/**
 * Implementierung von {@link RecursionPrevention}
 *
 * @author Adrodoc55
 */
public abstract class Superclass implements RecursionPrevention {
  @Transient
  private boolean pendingRelation;

  @Override
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
