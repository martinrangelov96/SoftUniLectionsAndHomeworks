package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.softuni.productshop.domain.entities.Order;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.entities.User;
import org.softuni.productshop.domain.models.service.OrderServiceModel;
import org.softuni.productshop.domain.models.service.UserServiceModel;
import org.softuni.productshop.repository.OrderRepository;
import org.softuni.productshop.repository.ProductRepository;
import org.softuni.productshop.validation.ProductValidationService;
import org.softuni.productshop.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final UserValidationService userValidationService;
    private final ProductValidationService productValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserService userService,
                            ProductRepository productRepository,
                            UserValidationService userValidationService,
                            ProductValidationService productValidationService, ModelMapper modelMapper
    ) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.userValidationService = userValidationService;
        this.productValidationService = productValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(String productId, String name) throws Exception {
        UserServiceModel userServiceModel = this.userService.findUserByUsername(name);
        if (!this.userValidationService.isValid(userServiceModel)) {
            throw new Exception();
        }
        Product product = this.productRepository.findById(productId)
                .filter(this.productValidationService::isValid)
                .orElseThrow(Exception::new);

        User user = new User();
        user.setId(userServiceModel.getId());

        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);

        this.orderRepository.save(order);
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        List<OrderServiceModel> orders = this.orderRepository.findAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());

        return orders;
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String name) {
        List<OrderServiceModel> orders = this.orderRepository.findAllByUser_Username(name)
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());

        return orders;
    }

}
