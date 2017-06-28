package de.adrodoc55.bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;

public abstract class OneToManyTest {
  protected abstract OneToMany newUnderTest() throws Exception;

  protected abstract ManyToOne newInverse() throws Exception;

  @Test
  public void test_addMany() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();
    ManyToOne inverse = newInverse();

    // when:
    boolean result = underTest.addMany(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(inverse.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_addMany__Bereits_enthalten() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();
    ManyToOne inverse = newInverse();
    underTest.addMany(inverse);

    // when:
    boolean result = underTest.addMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(inverse.getOne()).isSameAs(underTest);
  }

  @Test
  public void test_addMany__Ersetzt() throws Exception {
    // given:
    OneToMany underTest1 = newUnderTest();
    OneToMany underTest2 = newUnderTest();
    ManyToOne inverse = newInverse();
    underTest1.addMany(inverse);

    // when:
    boolean result = underTest2.addMany(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest1.getManys()).isEmpty();
    assertThat(underTest2.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(inverse.getOne()).isSameAs(underTest2);
  }

  @Test
  public void test_addMany__Mit_null() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();

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
  public void test_removeMany() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();
    ManyToOne inverse = newInverse();
    underTest.addMany(inverse);

    // when:
    boolean result = underTest.removeMany(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getOne()).isNull();
  }

  @Test
  public void test_removeMany__Nicht_enthalten() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();
    ManyToOne inverse = newInverse();

    // when:
    boolean result = underTest.removeMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getOne()).isNull();
  }

  @Test
  public void test_removeMany__In_Anderem_enthalten() throws Exception {
    // given:
    OneToMany underTest1 = newUnderTest();
    OneToMany underTest2 = newUnderTest();
    ManyToOne inverse = newInverse();
    underTest1.addMany(inverse);

    // when:
    boolean result = underTest2.removeMany(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest1.getManys()).containsExactlyInAnyOrder(inverse);
    assertThat(underTest2.getManys()).isEmpty();
    assertThat(inverse.getOne()).isSameAs(underTest1);
  }

  @Test
  public void test_removeMany__Mit_null() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();

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
  public void test_getManys__Liefert_unmodifiable_Collection() throws Exception {
    // given:
    OneToMany underTest = newUnderTest();
    ManyToOne inverse = newInverse();

    // when:
    Collection<ManyToOne> manys = underTest.getManys();

    UnsupportedOperationException actual = null;
    try {
      manys.add(inverse);
    } catch (UnsupportedOperationException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(UnsupportedOperationException.class);
    assertThat(underTest.getManys()).isEmpty();
    assertThat(inverse.getOne()).isNull();
  }

}
