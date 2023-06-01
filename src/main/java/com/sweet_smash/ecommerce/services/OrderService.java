package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.CreateOrderDto;
import com.sweet_smash.ecommerce.dtos.ProductInOrderDto;
import com.sweet_smash.ecommerce.entities.*;
import com.sweet_smash.ecommerce.repositories.OrderRepository;
import com.sweet_smash.ecommerce.repositories.ProductInOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserService userService;
    private final PaymentDetailsService detailsService;
    private final ProductInOrderRepository productInOrderRepository;
    private final ProductService productService;
    private final StockService stockService;
    private final OrderRepository orderRepository;

    public OrderService(UserService userService,
                        PaymentDetailsService detailsService,
                        ProductInOrderRepository productInOrderRepository,
                        ProductService productService,
                        StockService stockService,
                        OrderRepository orderRepository) {
        this.userService = userService;
        this.detailsService = detailsService;
        this.productInOrderRepository = productInOrderRepository;
        this.productService = productService;
        this.stockService = stockService;
        this.orderRepository = orderRepository;
    }

    public Order registerOrder(CreateOrderDto createOrderDto) {
        User user = userService.findById(createOrderDto.getUserId());
        double totalAmount = 0;

        for (ProductInOrderDto dto : createOrderDto.getProductsInOrder()) {
            Product product = productService.getProductDetailById(dto.getProductId());
            totalAmount += product.getUnitPrice() * dto.getQuantity();
        }

        PaymentDetails paymentDetails = this.detailsService.createPaymentDetail(
                totalAmount,
                true,
                createOrderDto.getPaymentTypeId()
        );

        Order order = new Order(user, paymentDetails);

        List<ProductInOrderDto> productsInOrderIds = createOrderDto.getProductsInOrder();
        Order savedOrder = this.orderRepository.save(order);

        for (ProductInOrderDto dto : productsInOrderIds) {
            Product product = productService.getProductDetailById(dto.getProductId());

            // reducir el stock del producto
            Stock stock = stockService.getStockById(product.getStock().getId());
            this.stockService.reduceStock(stock, dto.getQuantity());

            ProductInOrder productInOrder = new ProductInOrder(
                    dto.getQuantity(),
                    savedOrder,
                    product
            );
            productInOrderRepository.save(productInOrder);
        }

        return savedOrder;
    }
}
