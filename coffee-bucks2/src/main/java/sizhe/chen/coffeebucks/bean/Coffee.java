package sizhe.chen.coffeebucks.bean;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Table;

@Data
@Table("T_COFFEE")
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private String name;
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;
}
