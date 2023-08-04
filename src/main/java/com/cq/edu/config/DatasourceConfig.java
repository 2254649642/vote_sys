package com.cq.edu.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.cq.edu.pojo.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    /**
     * 使druid配置生效
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruid() {
        return new DruidDataSource();
    }


    /**
     * durid监控 http://localhost:8080/druid/index.html
     * @return
     */
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
//        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),  "/druid/*");
//        registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
//        registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
//        registrationBean.addInitParameter("loginUsername", "root");
//        registrationBean.addInitParameter("loginPassword", "123456");
//        registrationBean.addInitParameter("resetEnable", "false");
//        return registrationBean;
//    }

    @Bean
    public Result getResult() {
        return new Result();
    }
}
