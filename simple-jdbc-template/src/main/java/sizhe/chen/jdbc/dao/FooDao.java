package sizhe.chen.jdbc.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sizhe.chen.jdbc.bean.Foo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName : FooDao
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 20:18
 */
@Repository
@Slf4j
public class FooDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData(){
        Arrays.asList("a","b").forEach(bar ->jdbcTemplate.update("INSERT INTO FOO (BAR) VALUES (?)",bar));
        HashMap<String, String> row = new HashMap<>();
        row.put("BAR", "d");
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("ID of d: {}", id.longValue());
    }

    public void listData() {
        log.info("Count: {}",
                jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO", Long.class));

        List<String> list = jdbcTemplate.queryForList("SELECT BAR FROM FOO", String.class);
        list.forEach(s -> log.info("Bar: {}", s));

        List<Foo> fooList = jdbcTemplate.query("SELECT * FROM FOO",(rs, rowNum) -> {
            return Foo.builder()
                .id(rs.getLong(1))
                .bar(rs.getString(2))
                .build();});
        fooList.forEach(f -> log.info("Foo: {}", f));
    }
}
