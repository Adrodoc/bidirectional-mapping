package bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;

public abstract class OneToManyTest {
  protected abstract OneToMany newOneToMany();

  protected abstract ManyToOne newManyToOne();

  @Test
  public void test_addMany() {
    // given:
    OneToMany one = newOneToMany();
    ManyToOne many = newManyToOne();

    // when:
    boolean result = one.addMany(many);

    // then:
    assertThat(result).isTrue();
    assertThat(one.getManys()).containsExactly(many).hasSize(1);
    assertThat(many.getOne()).isSameAs(one);
  }

  @Test
  public void test_addMany__Bereits_enthalten() {
    // given:
    OneToMany one = newOneToMany();
    ManyToOne many = newManyToOne();
    one.addMany(many);

    // when:
    boolean result = one.addMany(many);

    // then:
    assertThat(result).isFalse();
    assertThat(one.getManys()).containsExactly(many).hasSize(1);
    assertThat(many.getOne()).isSameAs(one);
  }

  @Test
  public void test_addMany__Ersetzt() {
    // given:
    OneToMany oldOne = newOneToMany();
    OneToMany newOne = newOneToMany();
    ManyToOne many = newManyToOne();
    oldOne.addMany(many);

    // when:
    boolean result = newOne.addMany(many);

    // then:
    assertThat(result).isTrue();
    assertThat(oldOne.getManys()).isEmpty();
    assertThat(newOne.getManys()).containsExactly(many).hasSize(1);
    assertThat(many.getOne()).isSameAs(newOne);
  }

  @Test
  public void test_addMany__Mit_null() {
    // given:
    OneToMany one = newOneToMany();

    // when:
    NullPointerException actual = null;
    try {
      one.addMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(one.getManys()).isEmpty();
  }

  @Test
  public void test_removeMany() {
    // given:
    OneToMany one = newOneToMany();
    ManyToOne many = newManyToOne();
    one.addMany(many);

    // when:
    boolean result = one.removeMany(many);

    // then:
    assertThat(result).isTrue();
    assertThat(one.getManys()).isEmpty();
    assertThat(many.getOne()).isNull();
  }

  @Test
  public void test_removeMany__Nicht_enthalten() {
    // given:
    OneToMany one = newOneToMany();
    ManyToOne many = newManyToOne();

    // when:
    boolean result = one.removeMany(many);

    // then:
    assertThat(result).isFalse();
    assertThat(one.getManys()).isEmpty();
    assertThat(many.getOne()).isNull();
  }

  @Test
  public void test_removeMany__In_Anderem_enthalten() {
    // given:
    OneToMany one = newOneToMany();
    OneToMany other = newOneToMany();
    ManyToOne many = newManyToOne();
    other.addMany(many);

    // when:
    boolean result = one.removeMany(many);

    // then:
    assertThat(result).isFalse();
    assertThat(one.getManys()).isEmpty();
    assertThat(other.getManys()).containsExactly(many).hasSize(1);
    assertThat(many.getOne()).isSameAs(other);
  }

  @Test
  public void test_removeMany__Mit_null() {
    // given:
    OneToMany one = newOneToMany();

    // when:
    NullPointerException actual = null;
    try {
      one.removeMany(null);
    } catch (NullPointerException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(NullPointerException.class).hasMessage("many == null!");
    assertThat(one.getManys()).isEmpty();
  }

  @Test
  public void test_getManys__Liefert_unmodifiable_Collection() {
    // given:
    OneToMany one = newOneToMany();
    ManyToOne many = newManyToOne();

    // when:
    Collection<ManyToOne> manys = one.getManys();

    UnsupportedOperationException actual = null;
    try {
      manys.add(many);
    } catch (UnsupportedOperationException ex) {
      actual = ex;
    }

    // then:
    assertThat(actual).isExactlyInstanceOf(UnsupportedOperationException.class);
    assertThat(one.getManys()).isEmpty();
    assertThat(many.getOne()).isNull();
  }

}
