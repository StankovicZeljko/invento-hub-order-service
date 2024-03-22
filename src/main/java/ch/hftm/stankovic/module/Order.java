package ch.hftm.stankovic.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_order_id_seq")
    @SequenceGenerator(name = "product_order_id_seq", sequenceName = "product_order_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "product_id", nullable = false)
    @Setter
    private Long productId;

    @Column(name = "quantity", nullable = false)
    @Setter
    private Integer quantity;

    @Column(name = "state")
    @Setter
    private OrderState orderState;
}
