package sizhe.chen.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName : DruidApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 17:05
 */
@SpringBootApplication
public class DruidApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static void main(String[] args) {
        SpringApplication.run(DruidApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dataSource.toString());
    }
}
