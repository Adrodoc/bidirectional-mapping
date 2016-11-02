package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class OneToOneTest {
  protected abstract OneToOne newOneToOne();

  @Test
  public void test_setOne() {
    // given:
    OneToOne a = newOneToOne();
    OneToOne b = newOneToOne();

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
    OneToOne a = newOneToOne();
    OneToOne b = newOneToOne();
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
    OneToOne a = newOneToOne();
    OneToOne b = newOneToOne();
    OneToOne c = newOneToOne();
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
    OneToOne a = newOneToOne();
    OneToOne b = newOneToOne();
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
    OneToOne a = newOneToOne();

    // when:
    boolean result = a.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(a.getOne()).isNull();
  }

  @Test
  public void test_setOne__Mit_sich_selbst() {
    // given:
    OneToOne a = newOneToOne();

    // when:
    boolean result = a.setOne(a);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isSameAs(a);
  }

  @Test
  public void test_setOne__Sich_selbst_entfernen() {
    // given:
    OneToOne a = newOneToOne();
    a.setOne(a);

    // when:
    boolean result = a.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(a.getOne()).isNull();
  }

}
