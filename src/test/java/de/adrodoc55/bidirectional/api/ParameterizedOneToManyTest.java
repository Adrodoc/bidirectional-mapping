package de.adrodoc55.bidirectional.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public abstract class ParameterizedOneToManyTest<O extends OneToMany> extends OneToManyTest
    implements GenericTest<O> {
  @Parameter
  public Class<? extends ManyToOne> inverse;

  @Override
  protected OneToMany newUnderTest() throws Exception {
    return getClassUnderTest().getConstructor().newInstance();
  }

  @Override
  protected ManyToOne newInverse() throws Exception {
    return inverse.getConstructor().newInstance();
  }

  /**
   * Ensure there is no "copy-paste-not-modified" error and we did not forget to change the type
   * parameter when creating the sub class of this class.
   */
  @Test
  public void test_name_of_test_class_equals_name_of_class_under_test_with_suffix_Test() {
    String nameOfTestClass = getClass().getSimpleName();
    String expectedNameOfClassUnderTest = nameOfTestClass.replaceFirst("Test$", "");
    String expectedPackageName = getClass().getPackage().getName();
    assertThat(getClassUnderTest().getName())
        .isEqualTo(expectedPackageName + '.' + expectedNameOfClassUnderTest);
  }
}
