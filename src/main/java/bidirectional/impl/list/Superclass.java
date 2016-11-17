package bidirectional.impl.list;

public abstract class Superclass {
  private boolean pendingRelation;

  /**
   * Führt das angegebene {@link Runnable} aus, falls es sich hierbei nicht um einen rekursiven Aufruf handelt.
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
