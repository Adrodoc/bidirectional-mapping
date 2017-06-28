package de.adrodoc55.bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import java.util.Collection;

import org.junit.Test;

import de.adrodoc55.bidirectional.api.ManyToMany;

public abstract class ManyToManyTest {
  protected abstract ManyToMany newUnderTest();

  protected abstract ManyToMany newInverse();

  /**
   * Liefert {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}.
   *
   * @return {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}
   */
  protected boolean isReflexive() {
    return true;
  }

  @Test
  public void test_addMany() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse = newInverse();

    // when:
    boolean result = underTest.addMany(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(inverse.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_addMany__Bereits_enthalten() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse = newInverse();
    underTest.addMany(inverse);

    // when:
    boolean result = underTest.addMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(inverse.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_addMany__Fuegt_hinzu() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse1 = newInverse();
    ManyToMany inverse2 = newInverse();
    underTest.addMany(inverse1);

    // when:
    boolean result = underTest.addMany(inverse2);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(inverse1, inverse2);
    assertThat(inverse1.getManys()).containsExactlyInAnyOrder(underTest);
    assertThat(inverse2.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_addMany__Mit_null() {
    // given:
    ManyToMany underTest = newUnderTest();

    // when:
    NullPointerException actual = null;
    try {
      underTest.addMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(underTest.getManys()).isEmpty();
  }

  @Test
  public void test_addMany__Mit_sich_selbst() {
    assumeTrue(isReflexive() && newUnderTest().getClass().equals(newInverse().getClass()));

    // given:
    ManyToMany underTest = newUnderTest();

    // when:
    boolean result = underTest.addMany(underTest);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_removeMany() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse = newInverse();
    underTest.addMany(inverse);

    // when:
    boolean result = underTest.removeMany(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__Nicht_enthalten() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse = newInverse();

    // when:
    boolean result = underTest.removeMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__In_Anderem_enthalten() {
    // given:
    ManyToMany underTest1 = newUnderTest();
    ManyToMany underTest2 = newUnderTest();
    ManyToMany inverse = newInverse();
    underTest1.addMany(inverse);

    // when:
    boolean result = underTest2.removeMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest1.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(underTest2.getManys()).isEmpty();
    assertThat(inverse.getManys()).containsExactlyInAnyOrder(underTest1);
  }

  @Test
  public void test_removeMany__Mit_null() {
    // given:
    ManyToMany underTest = newUnderTest();

    // when:
    NullPointerException actual = null;
    try {
      underTest.removeMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(underTest.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany__Mit_sich_selbst() {
    assumeTrue(isReflexive() && newUnderTest().getClass().equals(newInverse().getClass()));

    // given:
    ManyToMany underTest = newUnderTest();
    underTest.addMany(underTest);

    // when:
    boolean result = underTest.removeMany(underTest);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).isEmpty();
  }

  @Test
  public void test_getManys__Liefert_unmodifiable_Collection() {
    // given:
    ManyToMany underTest = newUnderTest();
    ManyToMany inverse = newInverse();

    // when:
    Collection<ManyToMany> manys = underTest.getManys();

    UnsupportedOperationException actual = null;
    try {
      manys.add(inverse);
    } catch (UnsupportedOperationException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(UnsupportedOperationException.class);
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getManys()).isEmpty();
  }

}
