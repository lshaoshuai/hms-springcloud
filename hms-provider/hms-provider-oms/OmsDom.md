项目中遇到的问题：

- SpringBoot扫描包规则问题
    问题原因，OmsActionApplication 放在了com.hms.provider里，而module的包结构为com.hms.core
    ,从而导致Application扫描不到core里的子包。
    根据SpringApplication 扫描当前包级以及所有的子包，
    
- getWriter() has already been called for this response 错误问题
    
- @autowired 和 @resource 的不同之处

@Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖
对象必须存在，如果允许null值，可以设置它的required属性为false。如果
我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用

`@Resource默认按照ByName自动注入，由J2EE提供，需要导入包javax.annotation.Resource。@Resource有两个重要的属性：name和type，而Spring将@Resource注解的name属性解析为bean的名字，而type属性则解析为bean的类型。所以，如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略`

- 为什么rabbitmq直接传输int类型会有乱码

    rabbitTemplate模板类接收参数是object类型

