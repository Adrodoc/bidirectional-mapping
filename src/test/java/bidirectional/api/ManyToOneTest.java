package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class ManyToOneTest {
  protected abstract ManyToOne newUnderTest();

  protected abstract OneToMany newInverse();

  @Test
  public void test_setOne() {
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
  public void test_setOne__Bereits_verbunden() {
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
  public void test_setOne__Ersetzt() {
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
  public void test_setOne__Trennt() {
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
  public void test_setOne__Mit_null() {
    // given:
    ManyToOne underTest = newUnderTest();

    // when:
    boolean result = underTest.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(underTest.getOne()).isNull();
  }

}
