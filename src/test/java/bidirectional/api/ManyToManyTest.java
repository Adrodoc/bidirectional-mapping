package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;

public abstract class ManyToManyTest {
  protected abstract ManyToMany newManyToMany();

  @Test
  public void test_addMany() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();

    // when:
    boolean result = a.addMany(b);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getManys()).containsExactlyInAnyOrder(b);
    assertThat(b.getManys()).containsExactlyInAnyOrder(a);
  }

  @Test
  public void test_addMany__Bereits_enthalten() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();
    a.addMany(b);

    // when:
    boolean result = a.addMany(b);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getManys()).containsExactlyInAnyOrder(b);
    assertThat(b.getManys()).containsExactlyInAnyOrder(a);
  }

  @Test
  public void test_addMany__Fuegt_hinzu() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();
    ManyToMany c = newManyToMany();
    a.addMany(b);

    // when:
    boolean result = a.addMany(c);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getManys()).containsExactlyInAnyOrder(b, c);
    assertThat(b.getManys()).containsExactlyInAnyOrder(a);
    assertThat(c.getManys()).containsExactlyInAnyOrder(a);
  }

  @Test
  public void test_addMany__Mit_null() {
    // given:
    ManyToMany many = newManyToMany();

    // when:
    NullPointerException actual = null;
    try {
      many.addMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(many.getManys()).isEmpty();
  }

  @Test
  public void test_addMany__Mit_sich_selbst() {
    // given:
    ManyToMany a = newManyToMany();

    // when:
    boolean result = a.addMany(a);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getManys()).containsExactlyInAnyOrder(a);
  }

  @Test
  public void test_removeMany() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();
    a.addMany(b);

    // when:
    boolean result = a.removeMany(b);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getManys()).isEmpty();
    assertThat(b.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__Nicht_enthalten() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();

    // when:
    boolean result = a.removeMany(b);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getManys()).isEmpty();
    assertThat(b.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__In_Anderem_enthalten() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();
    ManyToMany c = newManyToMany();
    b.addMany(c);

    // when:
    boolean result = a.removeMany(c);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getManys()).isEmpty();
    assertThat(b.getManys()).containsExactlyInAnyOrder(c);
    assertThat(c.getManys()).containsExactlyInAnyOrder(b);
  }

  @Test
  public void test_removeMany__Mit_null() {
    // given:
    ManyToMany many = newManyToMany();

    // when:
    NullPointerException actual = null;
    try {
      many.removeMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(many.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__Mit_sich_selbst() {
    // given:
    ManyToMany a = newManyToMany();
    a.addMany(a);

    // when:
    boolean result = a.removeMany(a);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getManys()).isEmpty();
  }

  @Test
  public void test_getManys__Liefert_unmodifiable_Collection() {
    // given:
    ManyToMany a = newManyToMany();
    ManyToMany b = newManyToMany();

    // when:
    Collection<ManyToMany> manys = a.getManys();

    UnsupportedOperationException actual = null;
    try {
      manys.add(b);
    } catch (UnsupportedOperationException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(UnsupportedOperationException.class);
    assertThat(a.getManys()).isEmpty();
    assertThat(b.getManys()).isEmpty();
  }

}
