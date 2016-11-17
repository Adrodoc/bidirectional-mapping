package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;

public abstract class OneToOneTest {
  protected abstract OneToOne newA();

  protected abstract OneToOne newB();

  /**
   * Liefert {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}.
   * 
   * @return {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}
   */
  protected boolean isReflexive() {
    return true;
  }

  @Test
  public void test_setOne() {
    // given:
    OneToOne a = newA();
    OneToOne b = newB();

    // when:
    boolean result = a.setOne(b);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isSameAs(b);
    assertThat(b.getOne()).isSameAs(a);
  }

  @Test
  public void test_setOne__Bereits_verbunden() {
    // given:
    OneToOne a = newA();
    OneToOne b = newB();
    a.setOne(b);

    // when:
    boolean result = a.setOne(b);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getOne()).isSameAs(b);
    assertThat(b.getOne()).isSameAs(a);
  }

  @Test
  public void test_setOne__Ersetzt() {
    // given:
    OneToOne a = newA();
    OneToOne b = newB();
    OneToOne c = newB();
    a.setOne(b);

    // when:
    boolean result = a.setOne(c);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isSameAs(c);
    assertThat(b.getOne()).isNull();
    assertThat(c.getOne()).isSameAs(a);
  }

  @Test
  public void test_setOne__Trennt() {
    // given:
    OneToOne a = newA();
    OneToOne b = newB();
    a.setOne(b);

    // when:
    boolean result = a.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isNull();
    assertThat(b.getOne()).isNull();
  }

  @Test
  public void test_setOne__Mit_null() {
    // given:
    OneToOne a = newA();

    // when:
    boolean result = a.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getOne()).isNull();
  }

  @Test
  public void test_setOne__Mit_sich_selbst() {
    assumeTrue(isReflexive() && newA().getClass().equals(newB().getClass()));

    // given:
    OneToOne a = newA();

    // when:
    boolean result = a.setOne(a);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isSameAs(a);
  }

  @Test
  public void test_setOne__Sich_selbst_entfernen() {
    assumeTrue(isReflexive() && newA().getClass().equals(newB().getClass()));

    // given:
    OneToOne a = newA();
    a.setOne(a);

    // when:
    boolean result = a.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isNull();
  }

}
