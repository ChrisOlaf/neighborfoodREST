package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    @Autowired
    ResponseRepository responseRepository;

    @GetMapping("/allresponses")
    public Iterable<Response> allResponses(){
        return responseRepository.findAll();
    }

    @PostMapping("/addresponse")
    public void addResponse(@RequestBody Response response){
        responseRepository.save(response);
    }

    @PostMapping("/removeresponse")
    public void poistaYksi(@RequestBody Response response){
        responseRepository.delete(response);
    }
}
