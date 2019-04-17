spring-boot-admin
==============================
##简介
Spring Boot Admin 是一个管理和监控Spring Boot 应用程序的开源软件。每个应用都认为是一个客户端，通过HTTP或者使用 Eureka注册到admin server中进行展示，Spring Boot Admin UI部分使用AngularJs将数据展示在前端。

Spring Boot Admin 是一个针对spring-boot的actuator接口进行UI美化封装的监控工具。他可以：在列表中浏览所有被监控spring-boot项目的基本信息，详细的Health信息、内存信息、JVM信息、垃圾回收信息、各种配置信息（比如数据源、缓存列表和命中率）等，还可以直接修改logger的level。

这篇文章给大家介绍如何使用Spring Boot Admin对Spring Boot应用进行监控

##Spring Boot Admin 是由服务端和客户端组成

在 Spring Boot 项目中，Spring Boot Admin 作为 Server 端，其他的要被监控的应用作为 Client 端，基于这种的配置如下步骤
#### Server
* 添加相关依赖
```
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.0.1-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

* 启动类添加注解，开启监控
```
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
```

#### Client
* 添加相关依赖
```
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.0.0</version>
</dependency>
```
* 配置文件
```
spring.boot.admin.client.url: admin对应的路径 
management.endpoints.web.exposure.include: "*"
```


##spring-cloud 配置

## 推荐内容

- [官网文档](http://codecentric.github.io/spring-boot-admin/2.0.0/)：spring-boot-admin 2.0.0
- [GitHub](https://github.com/codecentric/spring-boot-admin): GitHub地址
