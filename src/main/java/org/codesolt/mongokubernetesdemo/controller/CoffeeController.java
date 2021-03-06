package org.codesolt.mongokubernetesdemo.controller;

import lombok.extern.java.Log;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.codesolt.mongokubernetesdemo.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/api/coffee-service")
@Log
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/coffee")
    public List<Coffee> getMenu() {
        log.info("GET CoffeeController getMenu");
        return coffeeService.getMenu();
    }

    @GetMapping("/coffee/{name}")
    public Coffee getCoffeeInfo(@PathVariable("name") @NotBlank String coffeeName) {
        log.info(String.format("GET CoffeeController getCoffeeInfo with coffeeName: %s", coffeeName));
        return coffeeService.getCoffeeInfo(coffeeName);
    }

    @GetMapping("/order/{order}")
    public Order getOrderInfo(@PathVariable("order") @NotBlank String orderId) {
        log.info(String.format("GET CoffeeController getOrderInfo with coffeeName: %s", orderId));
        return coffeeService.getOrder(orderId);
    }

    @PostMapping("/order/{name}/{quantity}")
    public ResponseEntity<Order> createOrder(@PathVariable("name") @NotBlank String coffeeName, @PathVariable("quantity") @Min(1) int quantity) {
        log.info(String.format("POST CoffeeController createOrder with coffeeName: %s, and quantity: %d", coffeeName, quantity));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(coffeeService.createOrder(coffeeName, quantity));
    }

    @PutMapping("/order/{orderId}/{quantity}")
    public Order updateOrder(@PathVariable("orderId") @NotBlank String orderId, @PathVariable("quantity") @Min(1) int quantity) {
        log.info(String.format("PUT CoffeeController updateOrder with orderId: %s, and quantity: %d", orderId, quantity));
        return coffeeService.updateOrder(orderId, quantity);
    }

    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") @NotBlank String orderId) {
        log.info(String.format("DELETE CoffeeController deleteOrder with orderId: %s", orderId));
        return coffeeService.deleteOrder(orderId);
    }
}
