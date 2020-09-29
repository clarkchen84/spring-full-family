package sizhe.chen.jdbc.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName : Foo
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-09-26 20:08
 */
@Data
@Builder
public class Foo {
    private  Long id;
    private String bar;
}
