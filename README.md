# Bidirectional Mapping
Dieses Projekt dient als Kopiervorlage für die Implementierung von bidirektionalem Mapping von Relationen zwischen JPA-Beans.

In den Subpackages von `bidirectional.impl` können die verschiedenen Implementierungen eingesehen und erweitert werden.

Die verschiedenen Implementierungen bieten zum Beispiel Vorteile bezüglich Performance. So sind Set basierte Implementierungen schneller als List basierte Implementierungen, da bei jedem adden oder removen `contains` aufgerufen werden muss, was bei mehreren Elementen Unterschiede verursachen kann, da Sets hier "constant time performance" also O(1) anbieten, während diese Zugriffe in Listen "linear time" also O(n) benötigen. Dies macht insbesondere beim Aufbau von mittleren bis großen Testsetups einen Unterschied.
List basierte Implementierungen die [Lazy Instatiation] unterstützen sind von dieser Regel ausgenommen. Da sie keine `contains` Aufrufe nutzen bieten sie wie Set basierte Implementierungen "constant time performance" also O(1).

Set basierte Implementierungen benötigen in der Regel ein IdentityHashSet, dies erfordert, dass die nutzende Klasse mit LazyIdentityHashSetEnabler als Customizer annotiert ist. [Mehr Details](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/RequiresIdentityHashSet.java).

Dies sind die empfohlenen Implementierungen, die aktuell die beste Performance bieten:

## OneToOne
[OneToOneBasicImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/impl/java7/onetoone/OneToOneJava7Impl.java)

## OneToMany
[OneToManyListImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/impl/java7/onetomany/OneToManyJava7ListImpl.java)

## ManyToOne
[ManyToOneBasicImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/impl/java7/manytoone/ManyToOneJava7Impl.java)

## ManyToMany
Auf der größeren Seite der Relation (In der Klasse, von der viele Instanzen erwartet werden) [ManyToManySetImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/impl/java7/manytomany/ManyToManyJava7SetImpl.java) und auf der kleineren Seite der Relation (In der Klasse, von der wenige Instanzen erwartet werden) [ManyToManyListImpl](https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/impl/java7/manytomany/ManyToManyJava7ListImpl.java)

[Lazy Instatiation]: https://github.com/Adrodoc55/bidirectional-mapping/blob/master/src/main/java/de/adrodoc55/bidirectional/LazyInstatiation.java
