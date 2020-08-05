#依赖注入和依赖查找的依赖来源是否相同？
 不是，依赖查找的来源仅限于Spring BeanDefinition以及单例对象，而依赖注入的来源还包括Resolvable Dependency以及@Value所标注的外部化配置
#单例对象能在Ioc容器启动后注册么？
可以的，单例对象的注册与BeanDefinition不同，BeanDefinition会被ConfigurableListableBeanFactory # freezeConfiguration()方法影响，从而冻结注册，单例对象则没有这个限制。
#Spring依赖注入来源有哪些？
答：Spring BeanDefinition,单例对象，Resolvable Dependency,@Value 外部化配置