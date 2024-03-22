package ch.hftm.stankovic.repositoy;

import ch.hftm.stankovic.module.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public Order findByProductId(Long productId){
        return find("productId", productId).firstResult();
    }
}