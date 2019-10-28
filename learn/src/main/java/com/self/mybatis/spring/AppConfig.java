package com.self.mybatis.spring;

import com.self.mybatis.spring.dao.PlayDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author shichen
 * @create 2019-10-28
 * @desc
 */
@Configuration
@ComponentScan(basePackages = "com.self.mybatis.spring")
@MapperScan(basePackages = "com.self.mybatis.spring.dao")
public class AppConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://10.208.2.21:5001/dramaking?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("Acy!@#qwe123");
        return dataSource;
    }


    /**
     * 使用javaconfig方式产生Dao对象  当dao多时，需要程序员多次定义  复杂  不实用
     */
    /*@Bean
    public PlayDao playDao() throws Exception {
        SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
        return sqlSessionFactory.openSession().getMapper(PlayDao.class);
    }*/

}
