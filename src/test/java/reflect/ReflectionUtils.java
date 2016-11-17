package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

public class ReflectionUtils {
  public static <T> T newInstance(Class<T> cls) {
    try {
      Constructor<T> constructor = cls.getConstructor();
      return constructor.newInstance();
    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
        | InvocationTargetException ex) {
      throw new UndeclaredThrowableException(ex);
    }
  }
}
