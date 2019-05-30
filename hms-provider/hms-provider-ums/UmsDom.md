# UMS 用户子系统文档说明

- 文件结构：

    hms-provider-rms 用户子系统服务模块

    命名规范: hms(酒店管理系统)-provider-**(子系统的名称)

    创建java package

    命名规范：com(company).hms(酒店管理系统)

- 包的子文件结构

    config:  Spring MVC 配置
    
    dao(Data acess object): 数据库操作层
    
    dto(Data Transfer Object): 用户发出请求时匹配
    
    exception: 异常错误处理
    
    provider: 
    
        controller:
        
            处理用户请求
        
        service:
        
            业务逻辑处理
    
    vo(view Object): 视图对象，返回给用户
    
    Application: 服务入口启动类
    
resources: 资源文件
    
    springboot使用以application命名的配置文件作为默认的全局配置文件
    在同一目录下, properties优先级大于yaml配置的优先级
    bootstrap.yml 用于应用程序上下文的引导阶段。
    bootstrap.yml 由父Spring ApplicationContext加载。(比application先加载)
    bootstrap.yml 可以理解成系统级别的一些参数配置，这些参数一般是不会变动的。
    application.yml 可以用来定义应用级别的，如果搭配 spring-cloud-config 使用 application.yml 里面定义的文件可以实现动态替换。



