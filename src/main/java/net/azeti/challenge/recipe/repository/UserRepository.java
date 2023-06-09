package net.azeti.challenge.recipe.repository;

import net.azeti.challenge.recipe.model.Recipe;
import net.azeti.challenge.recipe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, String> {

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String userEmail);
}
