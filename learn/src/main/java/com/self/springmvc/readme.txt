springMVC框架原理分析与手动实现------http://www.365yg.com/item/6471126931629670926/

1.产生SpringMVC的前世今生

 Servlet 网络 dao 视图---》柔和在一起，非常的痛苦，且重复工作

 Spring简化了开发难度，衍生于servlet WEB层框架

 Servlet容器：Tomcat JBoss jetty等，Tomcat建立socket连接，加载Servlet类

2.分析springMVC的组织架构和原理及加载流程【见springmvc.jpg】

 HttpServletBean--->init

 由ContextLoaderListener通过配置的context-param加载spring配置，初始化除了web层之外的bean（service,dao等）
 由DispatcherServlet初始化Servlet配置，加载spring-mvc.xml配置，初始化web层bean，并设置父级bean

3.web.xml加载顺序【由Servlet容器加载，如tomcat等】

  context-param--》listener--》filter--》Servlet