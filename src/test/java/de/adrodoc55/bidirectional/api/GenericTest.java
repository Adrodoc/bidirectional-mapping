package de.adrodoc55.bidirectional.api;

import com.google.common.reflect.TypeToken;

public interface GenericTest<T> {
  public default Class<T> getClassUnderTest() {
    @SuppressWarnings("serial")
    TypeToken<T> typeToken = new TypeToken<T>(getClass()) {};
    @SuppressWarnings("unchecked")
    Class<T> classUnderTest = (Class<T>) typeToken.getRawType();
    return classUnderTest;
  }
}
