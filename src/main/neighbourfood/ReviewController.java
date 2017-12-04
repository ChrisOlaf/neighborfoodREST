package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/allreviews")
    public Iterable<Review> naytaKaikki(){
        return reviewRepository.findAll();
    }

    @PostMapping("/addreview")
    public void lisaaYksi(@RequestBody Review review){
        reviewRepository.save(review);
    }

    @PostMapping("/removereview")
    public void poistaYksi(@RequestBody Review review){
        reviewRepository.delete(review);
    }

}
