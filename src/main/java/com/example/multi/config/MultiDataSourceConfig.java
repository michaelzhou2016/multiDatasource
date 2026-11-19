package com.example.multi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.multi.mapper.portal", sqlSessionFactoryRef = "portalSqlSessionFactory")
@MapperScan(basePackages = "com.example.multi.mapper.dm", sqlSessionFactoryRef = "dmSqlSessionFactory")
public class MultiDataSourceConfig {

    @Primary
    @Bean("portalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.portal") //读取yml文件中的配置的数据源参数
    public DataSource getPortalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean("portalSqlSessionFactory")
    public SqlSessionFactory portalSqlSessionFactory(@Qualifier("portalDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // mapper的xml形式文件位置必须要配置，不然将报错：no statement （这种错误也可能是mapper的xml中，namespace与项目的路径不一致导致）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/portal/*.xml"));
        bean.setConfigLocation(new ClassPathResource("mapper/sql-mapper-config.xml"));
        return bean.getObject();
    }

    // 配置事务
    @Bean(name = "portalTransactionManager")
    public DataSourceTransactionManager portalTransactionManager(@Qualifier("portalDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        dataSourceTransactionManager.setRollbackOnCommitFailure(true);
        dataSourceTransactionManager.setGlobalRollbackOnParticipationFailure(true);
        return dataSourceTransactionManager;
    }

    @Primary
    @Bean("portalSqlSessionTemplate")
    public SqlSessionTemplate portalSqlSessionTemplate(@Qualifier("portalSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("dmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dm")
    public DataSource getDmDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dmSqlSessionFactory")
    public SqlSessionFactory dmSqlSessionFactory(@Qualifier("dmDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/dm/*.xml"));
        bean.setConfigLocation(new ClassPathResource("mapper/sql-mapper-config.xml"));
        return bean.getObject();
    }

    @Bean(name = "dmTransactionManager")
    public DataSourceTransactionManager dmTransactionManager(@Qualifier("dmDataSource") DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        dataSourceTransactionManager.setRollbackOnCommitFailure(true);
        dataSourceTransactionManager.setGlobalRollbackOnParticipationFailure(true);
        return dataSourceTransactionManager;
    }

    @Bean("dmSqlSessionTemplate")
    public SqlSessionTemplate dmSqlSessionTemplate(@Qualifier("dmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

