package sizhe.chen.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName : MultyDataSourceApplicatioin
 * @Description : '
 * @Author : Clark Chen
 * @Date: 2020-09-26 14:01
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class
})
@Slf4j
public class MultyDataSourceApplicatioin implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MultyDataSourceApplicatioin.class,args);
    }

    @Autowired
    PlatformTransactionManager barTxManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(barTxManager);
    }

    @Bean
    @ConfigurationProperties("foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean
    @ConfigurationProperties("bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Autowired
    public DataSource fooDataSource(DataSourceProperties fooDataSourceProperties){


        log.info(fooDataSourceProperties.getUrl());
        return fooDataSourceProperties.initializeDataSourceBuilder().build();

    }

    @Bean
    @Autowired
    public DataSource barDataSource(DataSourceProperties barDataSourceProperties){
        log.info(barDataSourceProperties.getUrl());
        return barDataSourceProperties.initializeDataSourceBuilder().build();

    }


    @Bean
    @Autowired
    public PlatformTransactionManager barTxManager(DataSource barDataSource) {
        return new DataSourceTransactionManager(barDataSource);
    }

    @Bean
    @Autowired
    public PlatformTransactionManager fooTxManager(DataSource fooDataSource) {
        return new DataSourceTransactionManager(fooDataSource);
    }

}
