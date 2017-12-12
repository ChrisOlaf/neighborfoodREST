package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Autowired
    ResponseRepository responseRepository;

    @GetMapping("/allsales")
    public Iterable<Sale> naytaKaikki() {
        return saleRepository.findAll();
    }

    @PostMapping("/addsale")
    public void lisaaYksi(@RequestBody Sale sale) {
        List<Requirement> lista = sale.getRequirements();
        Sale o = saleRepository.save(sale);
        int j = lista.size();
        for (int i = 0; i < j; i++) {
            if(lista.get(i).getRequirement()!=null) {
                lista.get(i).setSale(o);
                requirementRepository.save(lista.get(i));
            }
        }
        sale.setRequirements(lista);

    }

    @PostMapping("/removesale")
    public void poistaYksi(@RequestBody Sale sale) {
        saleRepository.delete(sale);
    }

    @GetMapping("/sale/{id}/responses")
    public List<Response> responsesForOrder(@PathVariable(name = "id") int sale_id) {
        Sale s = saleRepository.findOne(sale_id);
        return saleRepository.findAllResponsesForSale(s);
    }

    @PostMapping("/sale/{id}/responses")
    public void addResponse(@PathVariable(name = "id") int sale_id, @RequestBody Response response) {
        Sale s = saleRepository.findOne(sale_id);
        response.setSale_id(s);
        responseRepository.save(response);
    }
    @GetMapping("/sale/{id}/requirements")
    public List<Requirement> requirementsForSale(@PathVariable(name = "id")int sale_id) {
        Sale s = saleRepository.findOne(sale_id);
        return saleRepository.findAllRequirementsForSale(s);
    }
}