#依赖注入和依赖查找的依赖来源是否相同？
 不是，依赖查找的来源仅限于Spring BeanDefinition以及单例对象，而依赖注入的来源还包括Resolvable Dependency以及@Value所标注的外部化配置
#单例对象能在Ioc容器启动后注册么？
可以的，单例对象的注册与BeanDefinition不同，BeanDefinition会被ConfigurableListableBeanFactory # freezeConfiguration()方法影响，从而冻结注册，单例对象则没有这个限制。
#Spring依赖注入来源有哪些？
答：Spring BeanDefinition,单例对象，Resolvable Dependency,@Value 外部化配置

| 来源 | 说明 |
|-----| -----|
|singleton|默认Spring Bean作用域，一个BeanFactory有且仅有一个实例
|prototype|原型作用域，每次依赖查找和依赖注入生成新Bean对象
|request|将Spring Bean存储在ServletRequest上下文中
|session|将Spring Bean存储在HttpSession中
|application|将Spring Bean存储在ServletContext中