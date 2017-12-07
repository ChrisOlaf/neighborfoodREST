package main.neighbourfood;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RequirementRepository requirementRepository;

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

    @PostMapping ("/addorderwithreqs")
    public void orderWithReqs(@RequestBody Orders order){
        List<Requirement> requirements = order.getRequirements();
        System.out.println(order);
        Orders uusi = orderRepository.save(order);
        System.out.println(uusi);
        int j = uusi.getId();
        System.out.println(j);
        for (int i = 0; i < requirements.size(); i++) {
            requirements.get(i).setOrder(uusi);
            Requirement req = requirementRepository.save(requirements.get(i));
        }
    }

}
