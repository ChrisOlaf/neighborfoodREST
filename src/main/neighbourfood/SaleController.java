package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/allsales")
    public Iterable<Sale> naytaKaikki(){
        return saleRepository.findAll();
    }

    @PostMapping("/addsale")
    public void lisaaYksi(@RequestBody Sale sale){
        saleRepository.save(sale);
    }

    @PostMapping("/removesale")
    public void poistaYksi(@RequestBody Sale sale){
        saleRepository.delete(sale);
    }


}
