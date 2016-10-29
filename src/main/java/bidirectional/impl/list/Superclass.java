package bidirectional.impl.list;

public abstract class Superclass {
  private boolean pendingRelation;

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
