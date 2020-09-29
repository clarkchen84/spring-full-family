package sizhe.chen.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sizhe.chen.jdbc.dao.BatchFooDao;
import sizhe.chen.jdbc.dao.FooDao;

import javax.sql.DataSource;

/**
 * @ClassName : JdbcTemplateApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 20:07
 */
@SpringBootApplication
public class JdbcTemplateApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApplication.class,args);
    }

    @Autowired
    private FooDao fooDao;
    @Autowired
    private BatchFooDao batchFooDao;


    @Bean
    @Autowired
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("FOO").usingGeneratedKeyColumns("ID");
    }

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        fooDao.insertData();
        batchFooDao.batchInsert();
        fooDao.listData();
    }
}
