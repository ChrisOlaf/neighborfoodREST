package main.neighbourfood;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  UserRepository extends CrudRepository<User, Integer> {

    @Query("select r from Review r where r.target = :id")
    public List<Review> findAllReviewsForUser(@Param("id")User target);

    @Modifying
    @Query("update User u set u.presentation = ?1 where u.id = ?2")
    public void modifyUser(String presentation, Integer id);
}
