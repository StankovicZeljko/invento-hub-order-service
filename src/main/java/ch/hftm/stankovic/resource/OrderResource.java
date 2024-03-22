package ch.hftm.stankovic.resource;

import ch.hftm.stankovic.module.Order;
import ch.hftm.stankovic.repositoy.OrderRepository;
import ch.hftm.stankovic.service.OrderMessageService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Inject
    OrderRepository repository;
    @Inject
    OrderMessageService orderMessageService;

    @GET
    public List<Order> getAllOrders(){
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Order getOrderByProductId(@PathParam("id") Long id){
        return repository.findById(id);
    }

    @POST()
    @Transactional
    public Response createOrder(Order order) {
        repository.persist(order);
        orderMessageService.sendOrderCreate(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, Order order){

        Order existingOrder = repository.findById(id);
        if (existingOrder != null){
            existingOrder.setProductId(order.getProductId());
            existingOrder.setQuantity(order.getQuantity());
            existingOrder.setOrderState(order.getOrderState());
        }

        return Response.status(Response.Status.OK).entity(existingOrder).build();

    }



}
