package sizhe.chen.redis.mode;

import lombok.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @ClassName : Coffee
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 11:13
 */
@Entity
@Table(name = "T_COFFEE")
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends  BaseEntity implements Serializable {

    private String name;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
      parameters = {@Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

}
