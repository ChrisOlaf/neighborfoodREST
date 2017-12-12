package main.neighbourfood;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ResponseRepository responseRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allorders")
    public Iterable<Orders> naytaKaikki(){
        return orderRepository.findAll();
    }

    @PostMapping("/addorder")
    public void lisaaYksi(@RequestBody Orders order){
        Set<Requirement> lista = order.getRequirements();
        Orders o = orderRepository.save(order);
        for (Requirement req: lista) {
            if(req.getRequirement()!=null) {
                req.setOrder(o);
                requirementRepository.save(req);
                System.out.println(req);
            }
        }
        order.setRequirements(lista);

    }

    @PostMapping("/removeorder")
    public void poistaYksi(@RequestBody Orders order){
        orderRepository.delete(order);
    }

    @PostMapping ("/addorderwithreqs")
    public void orderWithReqs(@RequestBody Orders order){
        orderRepository.save(order);
    }

    @GetMapping("/order/{id}/responses")
    public List<Response> responsesForOrder(@PathVariable(name = "id")int order_id) {
        Orders o = orderRepository.findOne(order_id);
        return orderRepository.findAllResponsesForOrder(o);
    }
    @PostMapping("/order/{id}/responses")
    public void addResponse(@PathVariable(name = "id")int order_id, @RequestBody Response response){
        Orders o = orderRepository.findOne(order_id);
        response.setOrder_id(o);
        responseRepository.save(response);
    }
    @GetMapping("/order/{id}/requirements")
    public List<Requirement> requirementsForOrder(@PathVariable(name = "id")int order_id) {
        Orders o = orderRepository.findOne(order_id);
        return orderRepository.findAllRequirementsForOrder(o);
    }

}
