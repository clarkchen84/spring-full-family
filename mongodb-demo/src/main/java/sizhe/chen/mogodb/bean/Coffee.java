package sizhe.chen.mogodb.bean;

import lombok.*;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName : bean
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-05 10:25
 */
@Document
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Coffee {
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
