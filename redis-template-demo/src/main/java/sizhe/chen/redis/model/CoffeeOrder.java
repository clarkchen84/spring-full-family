package sizhe.chen.redis.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName : CoffeeOrder
 * @Description :
 * @Author : Clark Chen
 * @Date: 2020-10-06 09:47
 */
@Entity
@Table(name = "T_ORDER")
@ToString(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder  extends  BaseEntity implements Serializable {
    private String customer;
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;
    @Enumerated
    @Column(nullable = false)
    private OrderState state;

}
