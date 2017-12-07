package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequirementController {

    @Autowired
    RequirementRepository requirementRepository;

    @GetMapping("/allreqs")
    public Iterable<Requirement> naytaKaikki(){
        return requirementRepository.findAll();
    }

    @PostMapping("/addreq")
    public void lisaaYksi(@RequestBody Requirement req){
        requirementRepository.save(req);
    }

    @PostMapping("/removereq")
    public void poistaYksi(@RequestBody Requirement req){
        requirementRepository.delete(req);
    }




}
