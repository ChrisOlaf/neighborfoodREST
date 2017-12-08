package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @GetMapping("/allsales")
    public Iterable<Sale> naytaKaikki(){
        return saleRepository.findAll();
    }

    @PostMapping("/addsale")
    public void lisaaYksi(@RequestBody Sale sale){
        saleRepository.save(sale);
        System.out.println(sale);
    }

    @PostMapping("/removesale")
    public void poistaYksi(@RequestBody Sale sale){
        saleRepository.delete(sale);
    }
}
