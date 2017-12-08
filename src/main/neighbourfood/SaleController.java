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
        List<Requirement> requirements = sale.getRequirements();
        System.out.println(sale);
        Sale uusi = saleRepository.save(sale);
        System.out.println(uusi);
        int j = uusi.getId();
        for (int i = 0; i < requirements.size(); i++) {
            requirements.get(i).setSale(uusi);
            Requirement requ = requirementRepository.save(requirements.get(i));
        }
        saleRepository.save(sale);
        System.out.println(sale);
    }

    @PostMapping("/removesale")
    public void poistaYksi(@RequestBody Sale sale){
        saleRepository.delete(sale);
    }
}
