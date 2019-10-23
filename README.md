# What's this?

Spring boot 2.2+ improved DB health checking feature.
But it may cause the issue when there's only AbstractDataSources.

This repository includes the reproducing code.

This issue was noted on spring-boot's repository.

https://github.com/spring-projects/spring-boot/issues/18661

The log when run this project is here:

```
2019-10-23 12:21:12.587 ERROR 5266 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.context.ApplicationContextException: Unable to start web server; nested exception is org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:156) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:544) ~[spring-context-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:141) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:747) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:397) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:315) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1226) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1215) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at com.example.boot22issue18661.Boot22Issue18661Application.main(Boot22Issue18661Application.java:18) ~[main/:na]
Caused by: org.springframework.boot.web.server.WebServerException: Unable to start embedded Tomcat
	at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize(TomcatWebServer.java:126) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.<init>(TomcatWebServer.java:88) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getTomcatWebServer(TomcatServletWebServerFactory.java:438) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory.getWebServer(TomcatServletWebServerFactory.java:191) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.createWebServer(ServletWebServerApplicationContext.java:180) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.onRefresh(ServletWebServerApplicationContext.java:153) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	... 8 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'servletEndpointRegistrar' defined in class path resource [org/springframework/boot/actuate/autoconfigure/endpoint/web/ServletEndpointManagementContextConfiguration$WebMvcServletEndpointManagementContextConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.endpoint.web.ServletEndpointRegistrar]: Factory method 'servletEndpointRegistrar' threw exception; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthEndpoint' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthEndpoint' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthContributorRegistry' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthContributorRegistry' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dbHealthContributor' defined in class path resource [org/springframework/boot/actuate/autoconfigure/jdbc/DataSourceHealthContributorAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:645) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:625) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:207) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.boot.web.servlet.ServletContextInitializerBeans.getOrderedBeansOfType(ServletContextInitializerBeans.java:211) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.ServletContextInitializerBeans.getOrderedBeansOfType(ServletContextInitializerBeans.java:202) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.ServletContextInitializerBeans.addServletContextInitializerBeans(ServletContextInitializerBeans.java:96) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.ServletContextInitializerBeans.<init>(ServletContextInitializerBeans.java:85) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.getServletContextInitializerBeans(ServletWebServerApplicationContext.java:253) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.selfInitialize(ServletWebServerApplicationContext.java:227) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.web.embedded.tomcat.TomcatStarter.onStartup(TomcatStarter.java:53) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5135) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:140) ~[na:na]
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:841) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1384) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1374) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at org.apache.tomcat.util.threads.InlineExecutorService.execute(InlineExecutorService.java:75) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:140) ~[na:na]
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:909) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.StandardEngine.startInternal(StandardEngine.java:262) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.StandardService.startInternal(StandardService.java:421) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.core.StandardServer.startInternal(StandardServer.java:930) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:183) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.apache.catalina.startup.Tomcat.start(Tomcat.java:459) ~[tomcat-embed-core-9.0.27.jar:9.0.27]
	at org.springframework.boot.web.embedded.tomcat.TomcatWebServer.initialize(TomcatWebServer.java:107) ~[spring-boot-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	... 13 common frames omitted
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.endpoint.web.ServletEndpointRegistrar]: Factory method 'servletEndpointRegistrar' threw exception; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthEndpoint' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthEndpoint' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthContributorRegistry' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthContributorRegistry' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dbHealthContributor' defined in class path resource [org/springframework/boot/actuate/autoconfigure/jdbc/DataSourceHealthContributorAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:185) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:640) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 53 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthEndpoint' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthEndpoint' parameter 0; nested exception is org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthContributorRegistry' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthContributorRegistry' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dbHealthContributor' defined in class path resource [org/springframework/boot/actuate/autoconfigure/jdbc/DataSourceHealthContributorAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:787) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:528) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1108) ~[spring-context-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.boot.actuate.endpoint.annotation.EndpointDiscoverer.createEndpointBean(EndpointDiscoverer.java:143) ~[spring-boot-actuator-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.actuate.endpoint.annotation.EndpointDiscoverer.createEndpointBeans(EndpointDiscoverer.java:133) ~[spring-boot-actuator-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.actuate.endpoint.annotation.EndpointDiscoverer.discoverEndpoints(EndpointDiscoverer.java:122) ~[spring-boot-actuator-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.actuate.endpoint.annotation.EndpointDiscoverer.getEndpoints(EndpointDiscoverer.java:116) ~[spring-boot-actuator-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.actuate.autoconfigure.endpoint.web.ServletEndpointManagementContextConfiguration$WebMvcServletEndpointManagementContextConfiguration.servletEndpointRegistrar(ServletEndpointManagementContextConfiguration.java:65) ~[spring-boot-actuator-autoconfigure-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 54 common frames omitted
Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'healthContributorRegistry' defined in class path resource [org/springframework/boot/actuate/autoconfigure/health/HealthEndpointConfiguration.class]: Unsatisfied dependency expressed through method 'healthContributorRegistry' parameter 0; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dbHealthContributor' defined in class path resource [org/springframework/boot/actuate/autoconfigure/jdbc/DataSourceHealthContributorAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:787) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:528) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:276) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1287) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1207) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:874) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:778) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 74 common frames omitted
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dbHealthContributor' defined in class path resource [org/springframework/boot/actuate/autoconfigure/jdbc/DataSourceHealthContributorAutoConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:645) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(ConstructorResolver.java:625) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateUsingFactoryMethod(AbstractAutowireCapableBeanFactory.java:1338) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1177) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:557) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:517) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:323) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:222) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:321) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:202) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.config.DependencyDescriptor.resolveCandidate(DependencyDescriptor.java:276) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.addCandidateEntry(DefaultListableBeanFactory.java:1503) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.findAutowireCandidates(DefaultListableBeanFactory.java:1467) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveMultipleBeans(DefaultListableBeanFactory.java:1386) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1245) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1207) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:874) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:778) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 88 common frames omitted
Caused by: org.springframework.beans.BeanInstantiationException: Failed to instantiate [org.springframework.boot.actuate.health.HealthContributor]: Factory method 'dbHealthContributor' threw exception; nested exception is java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:185) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.beans.factory.support.ConstructorResolver.instantiate(ConstructorResolver.java:640) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 105 common frames omitted
Caused by: java.lang.IllegalArgumentException: Beans must not be empty
	at org.springframework.util.Assert.notEmpty(Assert.java:549) ~[spring-core-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	at org.springframework.boot.actuate.autoconfigure.health.AbstractCompositeHealthContributorConfiguration.createContributor(AbstractCompositeHealthContributorConfiguration.java:52) ~[spring-boot-actuator-autoconfigure-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration.dbHealthContributor(DataSourceHealthContributorAutoConfiguration.java:82) ~[spring-boot-actuator-autoconfigure-2.2.0.RELEASE.jar:2.2.0.RELEASE]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.beans.factory.support.SimpleInstantiationStrategy.instantiate(SimpleInstantiationStrategy.java:154) ~[spring-beans-5.2.0.RELEASE.jar:5.2.0.RELEASE]
	... 106 common frames omitted
```