package de.adrodoc55.bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

import org.junit.Test;

public abstract class OneToOneTest {
  protected abstract OneToOne newUnderTest() throws Exception;

  protected abstract OneToOne newInverse() throws Exception;

  /**
   * Liefert {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}.
   *
   * @return {@code true} wenn die zu testende Implementierung reflexiv ist, sonst {@code false}
   */
  protected boolean isReflexive() {
    return true;
  }

  @Test
  public void test_setOne() throws Exception {
    // given:
    OneToOne underTest = newUnderTest();
    OneToOne inverse = newInverse();

    // when:
    boolean result = underTest.setOne(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isSameAs(inverse);
    assertThat(inverse.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_setOne__Bereits_verbunden() throws Exception {
    // given:
    OneToOne underTest = newUnderTest();
    OneToOne inverse = newInverse();
    underTest.setOne(inverse);

    // when:
    boolean result = underTest.setOne(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getOne()).isSameAs(inverse);
    assertThat(inverse.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_setOne__Ersetzt() throws Exception {
    // given:
    OneToOne underTest = newUnderTest();
    OneToOne inverse1 = newInverse();
    OneToOne inverse2 = newInverse();
    underTest.setOne(inverse1);

    // when:
    boolean result = underTest.setOne(inverse2);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isSameAs(inverse2);
    assertThat(inverse1.getOne()).isNull();
    assertThat(inverse2.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_setOne__Trennt() throws Exception {
    // given:
    OneToOne underTest = newUnderTest();
    OneToOne inverse = newInverse();
    underTest.setOne(inverse);

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isNull();
    assertThat(inverse.getOne()).isNull();
  }

  @Test
  public void test_setOne__Mit_null() throws Exception {
    // given:
    OneToOne underTest = newUnderTest();

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getOne()).isNull();
  }

  @Test
  public void test_setOne__Mit_sich_selbst() throws Exception {
    assumeTrue(isReflexive() && newUnderTest().getClass().equals(newInverse().getClass()));

    // given:
    OneToOne underTest = newUnderTest();

    // when:
    boolean result = underTest.setOne(underTest);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_setOne__Sich_selbst_entfernen() throws Exception {
    assumeTrue(isReflexive() && newUnderTest().getClass().equals(newInverse().getClass()));

    // given:
    OneToOne underTest = newUnderTest();
    underTest.setOne(underTest);

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isNull();
  }

}
