package de.adrodoc55.bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class ManyToOneTest {
  protected abstract ManyToOne newUnderTest() throws Exception;

  protected abstract OneToMany newInverse() throws Exception;

  @Test
  public void test_setOne() throws Exception {
    // given:
    ManyToOne underTest = newUnderTest();
    OneToMany inverse = newInverse();

    // when:
    boolean result = underTest.setOne(inverse);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isSameAs(inverse);
    assertThat(inverse.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_setOne__Bereits_verbunden() throws Exception {
    // given:
    ManyToOne underTest = newUnderTest();
    OneToMany inverse = newInverse();
    underTest.setOne(inverse);

    // when:
    boolean result = underTest.setOne(inverse);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getOne()).isSameAs(inverse);
    assertThat(inverse.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_setOne__Ersetzt() throws Exception {
    // given:
    ManyToOne underTest = newUnderTest();
    OneToMany inverse1 = newInverse();
    OneToMany inverse2 = newInverse();
    underTest.setOne(inverse1);

    // when:
    boolean result = underTest.setOne(inverse2);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isSameAs(inverse2);
    assertThat(inverse1.getManys()).isEmpty();
    assertThat(inverse2.getManys()).containsExactlyInAnyOrder(underTest);
  }

  @Test
  public void test_setOne__Trennt() throws Exception {
    // given:
    ManyToOne underTest = newUnderTest();
    OneToMany inverse = newInverse();
    underTest.setOne(inverse);

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(underTest.getOne()).isNull();
    assertThat(inverse.getManys()).isEmpty();
  }

  @Test
  public void test_setOne__Mit_null() throws Exception {
    // given:
    ManyToOne underTest = newUnderTest();

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getOne()).isNull();
  }

}
