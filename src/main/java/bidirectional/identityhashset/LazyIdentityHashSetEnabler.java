package bidirectional.identityhashset;

import java.lang.reflect.Field;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.CollectionMapping;

/**
 * Eclipselink Customizer für Jpa-Beans, der sicherstellt, dass {@link IdentityHashSet}s für
 * Relationen auch im LAZY-Mode verwendet werden können.
 */
public class LazyIdentityHashSetEnabler implements DescriptorCustomizer {

  @Override
  public void customize(ClassDescriptor descriptor) {
    useIndirectIdentityHashSetForRelations(descriptor);
  }

  /**
   * Legt fest, dass Eclipselink für alle {@link IdentityHashSet}-basierten Relationen des (durch
   * den Descriptor) gegebenen JPA-Beans ein {@link IndirectIdentityHashSet} verwenden soll.
   *
   * @param descriptor
   */
  private void useIndirectIdentityHashSetForRelations(ClassDescriptor descriptor) {
    Object jpaBean = descriptor.getInstantiationPolicy().buildNewInstance();
    Iterable<String> relationFieldNames =
        getIdentityHashSetRelations((Class<?>) descriptor.getJavaClass(), jpaBean);
    for (String name : relationFieldNames) {
      CollectionMapping x = ((CollectionMapping) descriptor.getMappingForAttributeName(name));
      x.useTransparentCollection();
      x.useCollectionClass(IndirectIdentityHashSet.class);
    }
  }

  public static Iterable<String> getIdentityHashSetRelations(Class<?> jpaBeanClass,
      Object jpaBean) {
    Set<String> result = new HashSet<String>();
    Set<Field> fields = getFields(jpaBeanClass);
    for (Field field : fields) {
      if (field.getAnnotation(OneToMany.class) != null
          || field.getAnnotation(ManyToMany.class) != null) {
        Object value = getValue(jpaBean, field);
        if (value instanceof IdentityHashSet) {
          result.add(field.getName());
        }
      }
    }
    return result;
  }

  private static Object getValue(Object jpaBean, Field field) {
    field.setAccessible(true);
    try {
      return field.get(jpaBean);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new UndeclaredThrowableException(e);
    }
  }

  private static Set<Field> getFields(Class<?> cls) {
    Set<Field> result = new HashSet<>();
    while (cls != Object.class) {
      result.addAll(Arrays.asList(cls.getDeclaredFields()));
      cls = cls.getSuperclass();
    }
    return result;
  }
}
