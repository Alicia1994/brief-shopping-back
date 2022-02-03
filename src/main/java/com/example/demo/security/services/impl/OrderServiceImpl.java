package com.example.demo.security.services.impl;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    private OrderDto orderDto;

    private ModelMapper modelMapper = new ModelMapper();

    public Iterable<Order> getAllUser(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrder (Long id) {
        return orderRepository.findById(id);
    }

    public User SaveOrder (Long id, OrderDto orderDto){
        Optional<User> userOptional = userRepository.findById(id);
        Order order = modelMapper.map(orderDto, Order.class);
        User user = null;

        if (userOptional.isPresent() && orderDto.getUserId() == userOptional.get().getId()){
            user =userOptional.get();
            user.getOrders().add(order);
            userRepository.save(user);
        }
        return user;
    }

//    public Order currentStock(Long id, ProductDto productDto){
//        Optional<ProductDto>
//    }

    public void adminDeleteOrder(Long id){
        Order orderOptional = orderRepository.findById(id).get();
        User userOptional = userRepository.findById(orderOptional.getUserId()).get();
        userOptional.getOrders().remove(orderOptional);
        userRepository.save(userOptional);

    }

    public Order adminUpdateOrder(OrderDto orderDto){
        Order project = modelMapper.map(orderDto, Order.class);
        return orderRepository.save(project);
    }


}
