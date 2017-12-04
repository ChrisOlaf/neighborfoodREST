package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/allorders")
    public Iterable<Orders> naytaKaikki(){
        return orderRepository.findAll();
    }

    @PostMapping("/addorder")
    public void lisaaYksi(@RequestBody Orders order){
        orderRepository.save(order);
    }

    @PostMapping("/removeorder")
    public void poistaYksi(@RequestBody Orders order){
        orderRepository.delete(order);
    }

}
