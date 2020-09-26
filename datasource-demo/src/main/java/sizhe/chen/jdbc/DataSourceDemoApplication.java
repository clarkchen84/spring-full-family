package sizhe.chen.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName : DatasouceDemoApplication
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 13:27
 */
@SpringBootApplication
@Slf4j
public class DataSourceDemoApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... args) throws Exception {
        showData();
    }

    private void showData(){
        jdbcTemplate.queryForList("select * from foo").forEach(
                row ->log.info(row.toString())
        );
    }



    public static void main(String[] args) {
        SpringApplication.run(DataSourceDemoApplication.class,args);
    }
}
