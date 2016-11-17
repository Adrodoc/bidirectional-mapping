# Bidirectional Mapping
Dieses Projekt dient als Kopiervorlage für die Implementierung von bidirektionalem Mapping von Relationen zwischen JPA-Beans.

In den Subpackages von `bidirectional.impl` können die verschiedenen Implementierungen eingesehen und erweitert werden.

Die verschiedenen Implementierungen bieten zum Beispiel Vorteile bezüglich Performance. So sind Set basierte Implementierungen schneller als List basierte Implementierungen, da bei jedem adden oder removen `contains` aufgerufen werden muss, was bei mehreren Elementen Unterschiede verursachen kann, da Sets hier "constant time performance" also O(1) anbieten, während diese Zugriffe in Listen "linear time" also O(n) benötigen. Dies macht insbesondere beim Aufbau von mittleren bis großen Testsetups einen Unterschied.
List basierte Implementierungen die [Lazy Instatiation] unterstützen sind von dieser Regel ausgenommen. Da sie keine `contains` Aufrufe nutzen bieten sie wie Set basierte Implementierungen "constant time performance" also O(1).

Set basierte Implementierungen benötigen in der Regel ein IdentityHashSet, dies erfordert, dass die nutzende Klasse mit LazyIdentityHashSetEnabler als Customizer annotiert ist. [Mehr Details](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/RequiresIdentityHashSet.java).

Dies sind die empfohlenen Implementierungen, die aktuell die beste Performance bieten:

## OneToOne
[OneToOneBasicImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/impl/onetoone/OneToOneBasicImpl.java)

## OneToMany
[OneToManyListImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/impl/onetomany/OneToManyListImpl.java)

## ManyToOne
[ManyToOneBasicImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/impl/manytoone/ManyToOneBasicImpl.java)

## ManyToMany
Auf der größeren Seite der Relation (Die Seite, auf der viele Instanzen erwartet werden) [ManyToManySetImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/impl/manytomany/ManyToManySetImpl.java) und auf der kleineren Seite der Relation (Die Seite, auf der wenige Instanzen erwartet werden) [ManyToManyListImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/impl/manytomany/ManyToManyListImpl.java)

[Lazy Instatiation]: https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/bidirectional/LazyInstatiation.java