package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public abstract class ManyToOneTest {
  protected abstract ManyToOne newManyToOne();

  protected abstract OneToMany newOneToMany();

  @Test
  public void test_setOne() {
    // given:
    ManyToOne many = newManyToOne();
    OneToMany one = newOneToMany();

    // when:
    boolean result = many.setOne(one);

    // then:
    assertThat(result).isTrue();
    assertThat(many.getOne()).isSameAs(one);
    assertThat(one.getManys()).containsExactlyInAnyOrder(many);
  }

  @Test
  public void test_setOne__Bereits_verbunden() {
    // given:
    ManyToOne many = newManyToOne();
    OneToMany one = newOneToMany();
    many.setOne(one);

    // when:
    boolean result = many.setOne(one);

    // then:
    assertThat(result).isFalse();
    assertThat(many.getOne()).isSameAs(one);
    assertThat(one.getManys()).containsExactlyInAnyOrder(many);
  }

  @Test
  public void test_setOne__Ersetzt() {
    // given:
    ManyToOne many = newManyToOne();
    OneToMany oldOne = newOneToMany();
    OneToMany newOne = newOneToMany();
    many.setOne(oldOne);

    // when:
    boolean result = many.setOne(newOne);

    // then:
    assertThat(result).isTrue();
    assertThat(many.getOne()).isSameAs(newOne);
    assertThat(oldOne.getManys()).isEmpty();
    assertThat(newOne.getManys()).containsExactlyInAnyOrder(many);
  }

  @Test
  public void test_setOne__Trennt() {
    // given:
    ManyToOne many = newManyToOne();
    OneToMany one = newOneToMany();
    many.setOne(one);

    // when:
    boolean result = many.setOne(null);

    // then:
    assertThat(result).isTrue();
    assertThat(many.getOne()).isNull();
    assertThat(one.getManys()).isEmpty();
  }

  @Test
  public void test_setOne__Mit_null() {
    // given:
    ManyToOne many = newManyToOne();

    // when:
    boolean result = many.setOne(null);

    // then:
    assertThat(result).isFalse();
    assertThat(many.getOne()).isNull();
  }

}
