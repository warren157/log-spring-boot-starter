package com.verymro.config;

import com.verymro.db.LogSave;
import com.verymro.intercepter.JYLogIntercepter;
import com.verymro.prop.LogDbProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * @author rui.wang
 * @version 1.0
 * @description: TODO
 * @date 2021/6/23 8:30
 */
@Configuration
@EnableConfigurationProperties(LogDbProperty.class)
public class JYLogConfig implements WebMvcConfigurer {

    @Autowired LogDbProperty dbProperty;

    @Bean
    public JYLogIntercepter jYLogIntercepter(){
        return new JYLogIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jYLogIntercepter()).excludePathPatterns("*.html","*.css","*.htm");
    }

    @Autowired
    JdbcTemplate template;

    @Bean
    public LogSave logSave(){
        return new LogSave(template,dbProperty);
    }

}
