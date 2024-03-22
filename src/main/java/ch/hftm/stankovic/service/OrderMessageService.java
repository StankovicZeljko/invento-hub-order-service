package ch.hftm.stankovic.service;

import ch.hftm.stankovic.module.Order;
import ch.hftm.stankovic.repositoy.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class OrderMessageService {

    @Inject
    OrderRepository repository;

    @Inject
    @Channel("order-create")
    Emitter<Order> orderCreateEmitter;

    @Incoming("order-update")
    @Transactional
    public void updateOrderState(Order order){

        Order existingOrder = repository.findById(order.getId());
        existingOrder.setOrderState(order.getOrderState());
        repository.persist(existingOrder);

    }

    public void sendOrderCreate(Order order){
        this.orderCreateEmitter.send(order);
    }

}
